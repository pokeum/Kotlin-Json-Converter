package com.example.codegen

import com.example.annotation.Serializable
import com.example.codegen.commons.Primitive
import com.example.codegen.extension.isExtendedBy
import com.example.codegen.extension.isNullablePrimitive
import com.example.codegen.model.ClassBinding
import com.example.codegen.model.FieldBinding
import com.example.codegen.recursion.FieldInfo
import com.squareup.kotlinpoet.*
import org.json.JSONArray
import org.json.JSONObject
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror
import javax.tools.Diagnostic

object CodeGen {

    private const val SERIALIZE_NULL = true

    private val JSON_OBJECT_CLASS_NAME = JSONObject::class.asClassName()
    private val JSON_ARRAY_CLASS_NAME = JSONArray::class.asClassName()
    private const val TO_JSON_OBJECT_FUN_NAME = "toJSONObject"
    private const val JSON_OBJECT_NULL = "JSONObject.NULL"
    private const val SERIALIZE_NULL_TO_STRING_FUN_NAME = "serializeNullToString"

    fun createFileSpec(packageName: String, binding: ClassBinding): FileSpec {
        val builder = FileSpec.builder(packageName, "${binding.getSimpleName()}Stub")
            .addImport(JSON_OBJECT_CLASS_NAME.packageName, JSON_OBJECT_CLASS_NAME.simpleName)
            .addImport(JSON_ARRAY_CLASS_NAME.packageName, JSON_ARRAY_CLASS_NAME.simpleName)
            .addFunction(createToJSONObjectFunc(binding))
            //.addFunction(createToClassObjectFunc(binding))
        return builder.build()
    }

    private fun createToJSONObjectFunc(binding: ClassBinding): FunSpec {
        val builder = FunSpec.builder(TO_JSON_OBJECT_FUN_NAME)
            .addModifiers(KModifier.INTERNAL)
            .receiver(binding.getClassName())
            .returns(JSONObject::class)
            .addStatement("val obj = %L()", JSON_OBJECT_CLASS_NAME.simpleName)
        binding.getFieldBindings().forEach {
            builder.addCode(createToJSONObjectCodeBlock(FieldInfo(it)))
        }
        builder.addStatement("return obj")
        return builder.build()
    }

    private fun createToJSONObjectCodeBlock(fieldInfo: FieldInfo): CodeBlock {
        val builder = CodeBlock.builder()
        if (fieldInfo.isPrimitiveOrCharSequence()) {
            builder.add(createToJSONObjectRecursionCodeBlock(fieldInfo))
        } else if (fieldInfo.currentType.kind.equals(TypeKind.DECLARED)) {
            val declaredType = fieldInfo.currentType as DeclaredType
            when {
                fieldInfo.currentType.isExtendedBy(Collection::class.java) -> {
                    val elementType = declaredType.typeArguments[0]
                    builder.add(createToJSONObjectRecursionCodeBlock(fieldInfo, elementType, true))
                }
                fieldInfo.currentType.isExtendedBy(Map::class.java) -> {
                    val keyType = declaredType.typeArguments[0]
                    if (!keyType.isExtendedBy(String::class.java)) {
                        ProcessingEnvUtils.printMessage(
                            Diagnostic.Kind.ERROR,
                            "JSON field Map support String key type only",
                            fieldInfo.binding.fieldElement
                        )
                    }
                    val valueType = declaredType.typeArguments[1]
                    builder.add(createToJSONObjectRecursionCodeBlock(fieldInfo, valueType, false))
                }
                else -> {
                    val valueTypeElement = declaredType.asElement()
                    val valueAnnotation = valueTypeElement.getAnnotation(Serializable::class.java)
                    if (valueAnnotation == null) {
                        ProcessingEnvUtils.printMessage(
                            Diagnostic.Kind.ERROR,
                            "Nested class <${valueTypeElement.simpleName}> doesn't support @Serializable annotation",
                            fieldInfo.binding.fieldElement
                        )
                    } else { builder.add(createToJSONObjectRecursionCodeBlock(fieldInfo)) }
                }
            }
        }
        return builder.build()
    }

    /** [create code block] ToJSONObject : Map and Collection */
    private fun createToJSONObjectRecursionCodeBlock(
        fieldInfo: FieldInfo, nestedType: TypeMirror, isOuterTypeCollection: Boolean): CodeBlock {
        val builder = CodeBlock.builder()
        val objectName = fieldInfo.generateObjectName()
        val innerObjectName = fieldInfo.generateObjectName()
        builder
            .beginControlFlow("if (%L != null)", fieldInfo.iterableObject)
            .addStatement("val %L = %L()", objectName,
                if (isOuterTypeCollection) JSON_ARRAY_CLASS_NAME.simpleName
                else JSON_OBJECT_CLASS_NAME.simpleName)
            .beginControlFlow("for (%L in %L!!)", innerObjectName, fieldInfo.iterableObject)
            .add(createToJSONObjectCodeBlock(fieldInfo.next(nestedType, isOuterTypeCollection)))
            .endControlFlow()
        if (fieldInfo.isOuterTypeCollection()) {
            builder.addStatement("%L.put(%L)", fieldInfo.parentJsonObject, objectName)
        } else {
            builder.addStatement("%L.put(${if (fieldInfo.isFirst) "%S" else "%L"}, %L)",
                fieldInfo.parentJsonObject, fieldInfo.key, objectName)
        }
        builder.endControlFlow()

        if (SERIALIZE_NULL || fieldInfo.isOuterTypeCollection()) {
            builder.beginControlFlow("else")
            if (fieldInfo.isOuterTypeCollection()) {
                builder.addStatement("%L.put(${JSON_OBJECT_NULL})", fieldInfo.parentJsonObject)
            } else {
                builder.addStatement("%L.put(${if (fieldInfo.isFirst) "%S" else "%L"}, ${JSON_OBJECT_NULL})",
                    fieldInfo.parentJsonObject, fieldInfo.key)
            }
            builder.endControlFlow()
        }

        return builder.build()
    }

    /** [create code block] ToJSONObject : Primitive, CharSequence and Serializable */
    private fun createToJSONObjectRecursionCodeBlock(fieldInfo: FieldInfo): CodeBlock {

        fun format(pre: String, post: String): String {
            val formatSB = StringBuilder()
            formatSB.append(pre)
            if (!fieldInfo.isPrimitiveOrCharSequence()) {
                formatSB.append("?.${TO_JSON_OBJECT_FUN_NAME}()")
            }
            if (SERIALIZE_NULL) { formatSB.append(" ?: $JSON_OBJECT_NULL") }
            formatSB.append(post)
            return formatSB.toString()
        }

        val builder = CodeBlock.builder()
        if (fieldInfo.isFirst) {
            builder.addStatement(format("obj.put(%S, %L", ")"),
                fieldInfo.binding.getKeyName(), fieldInfo.binding.getFieldName())
        } else {
            val objectName = "obj_${fieldInfo.getObjectID() - 2}"
            val innerObjectName = "obj_${fieldInfo.getObjectID() - 1}"
            if (fieldInfo.isOuterTypeCollection()) {
                builder.addStatement(format("%L.put(%L", ")"), objectName, innerObjectName)
            } else {
                builder.addStatement(format("%L.put(%L.key, %L.value", ")"),
                    objectName, innerObjectName, innerObjectName)
            }
        }
        return builder.build()
    }

    private fun createToClassObjectFunc(binding: ClassBinding): FunSpec {
        val builder = FunSpec.builder("to${binding.getSimpleName()}Object")
            .addModifiers(KModifier.INTERNAL)
            .receiver(String::class)
            .returns(binding.getClassName())
            .addStatement(serializeNullToStringFuncStatement())
            .addStatement("val obj = %L(this)", JSON_OBJECT_CLASS_NAME.simpleName)
            .addStatement("return %L(", binding.getSimpleName())
        binding.getFieldBindings().forEach { builder.addCode(createToClassObjectCodeBlock(it)) }
        builder.addStatement(")")
        return builder.build()
    }

    private fun createToClassObjectCodeBlock(binding: FieldBinding): CodeBlock {
        val builder = CodeBlock.builder()
        when {
            binding.isPrimitive() -> {
                builder.addStatement("obj.opt(%S).toString().${Primitive(binding.getType()).toTypeFunc()},", binding.getKeyName())
            }
            binding.isNullablePrimitive() -> {
                builder.addStatement("obj.opt(%S)?.$SERIALIZE_NULL_TO_STRING_FUN_NAME()?.${Primitive(binding.getType()).toTypeFunc()},", binding.getKeyName())
            }
            binding.isDeclared() -> {
                val declaredType = binding.getType() as DeclaredType
                when {
                    binding.isExtendedBy(CharSequence::class.java) -> {
                        builder.addStatement("obj.optString(%S, null),", binding.getKeyName())  // String
                    }
                    binding.isExtendedBy(Collection::class.java) -> {
                        val elementType = declaredType.typeArguments[0]
                        // DEBUG
                        if (elementType.kind.isPrimitive || elementType.isNullablePrimitive()) {
                            println(Primitive(elementType).toType())
                        }
                    }
                    binding.isExtendedBy(Map::class.java) -> {

                    }
                    else -> {
                        val annotation = declaredType.asElement().getAnnotation(Serializable::class.java)
                        if (annotation == null) {
                            ProcessingEnvUtils.printMessage(Diagnostic.Kind.ERROR,
                                "Invalid JSON field type provided",
                                binding.fieldElement)
                        }
                    }
                }
            }
        }
        return builder.build()
    }

    private fun serializeNullToStringFuncStatement(): String {
        return "fun Any.$SERIALIZE_NULL_TO_STRING_FUN_NAME(): String? = if (this.equals(null)) null else this.toString()";
    }
}