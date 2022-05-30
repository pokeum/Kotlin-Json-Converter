package com.example.codegen

import com.example.annotation.Serializable
import com.example.codegen.commons.Logger
import com.example.codegen.commons.Primitive
import com.example.codegen.extension.isExtendedBy
import com.example.codegen.model.ClassBinding
import com.example.codegen.model.FieldBinding
import com.example.codegen.recursion.FieldInfo
import com.squareup.kotlinpoet.*
import org.json.JSONArray
import org.json.JSONObject
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror
import javax.tools.Diagnostic

object CodeGen {

    private val JSON_OBJECT_CLASS_NAME = JSONObject::class.asClassName()
    private val JSON_ARRAY_CLASS_NAME = JSONArray::class.asClassName()
    private const val TO_JSON_OBJECT_FUN_NAME = "toJSONObject"

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
            builder.addCode(createToJSONObjectCodeBlock(FieldInfo(it, it.getType())))
        }
        builder.addStatement("return obj")
        return builder.build()
    }

    private fun createToJSONObjectCodeBlock(fieldInfo: FieldInfo): CodeBlock {
        val builder = CodeBlock.builder()
        if (fieldInfo.isDefault()) {
            builder.add(createToJSONObjectDefaultAndSerializableCodeBlock(fieldInfo))
        } else if (fieldInfo.isDeclared()) {
            val declaredType = fieldInfo.currentType as DeclaredType
            when {
                fieldInfo.currentType.isExtendedBy(Collection::class.java) -> {
                    val elementType = declaredType.typeArguments[0]
                    builder.add(createToJSONObjectMapAndCollectionCodeBlock(fieldInfo, elementType, false))
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
                    builder.add(createToJSONObjectMapAndCollectionCodeBlock(fieldInfo, valueType, true))
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
                    }
                    builder.add(createToJSONObjectDefaultAndSerializableCodeBlock(fieldInfo))
                }
            }
        }
        return builder.build()
    }

    private fun createToJSONObjectMapAndCollectionCodeBlock(
        fieldInfo: FieldInfo, nestedType: TypeMirror, isOuterTypeMap: Boolean): CodeBlock {
        val builder = CodeBlock.builder()
        val objectName = fieldInfo.generateObjectName()
        val innerObjectName = fieldInfo.generateObjectName()
        builder
            .beginControlFlow("if (%L != null)", fieldInfo.valueObject)
            .addStatement("val %L = %L()", objectName,
                if (isOuterTypeMap) JSON_OBJECT_CLASS_NAME.simpleName
                else JSON_ARRAY_CLASS_NAME.simpleName)
            .beginControlFlow("for (%L in %L!!)", innerObjectName, fieldInfo.valueObject)
            .add(createToJSONObjectCodeBlock(fieldInfo.next(nestedType, isOuterTypeMap)))
            .endControlFlow()
        if (fieldInfo.keyName != null) {
            builder.addStatement("%L.put(${if (fieldInfo.isFirst) "%S" else "%L"}, %L)",
                fieldInfo.putObject, fieldInfo.keyName, objectName)
        } else { builder.addStatement("%L.put(%L)", fieldInfo.putObject, objectName) }
        builder.endControlFlow()
        return builder.build()
    }

    private fun createToJSONObjectDefaultAndSerializableCodeBlock(fieldInfo: FieldInfo): CodeBlock {
        val builder = CodeBlock.builder()
        val formatSB = StringBuilder()
        if (fieldInfo.isFirst) {
            formatSB.append("obj.put(%S, %L")
            if (!fieldInfo.isDefault()) { formatSB.append(".${TO_JSON_OBJECT_FUN_NAME}()") }
            formatSB.append(")")
            builder.addStatement(formatSB.toString(), fieldInfo.binding.getKeyName(), fieldInfo.binding.getFieldName())
        } else {
            val objectName = "obj_${fieldInfo.getObjectID() - 2}"
            val innerObjectName = "obj_${fieldInfo.getObjectID() - 1}"
            if (fieldInfo.isOuterTypeMap()) {
                formatSB.append("%L.put(%L.key, %L.value")
                if (!fieldInfo.isDefault()) { formatSB.append("?.${TO_JSON_OBJECT_FUN_NAME}()") }
                formatSB.append(")")
                builder.addStatement(formatSB.toString(), objectName, innerObjectName, innerObjectName)
            } else {
                formatSB.append("%L.put(%L")
                if (!fieldInfo.isDefault()) { formatSB.append("?.${TO_JSON_OBJECT_FUN_NAME}()") }
                formatSB.append(")")
                builder.addStatement(formatSB.toString(), objectName, innerObjectName)
            }
        }
        return builder.build()
    }

    private fun createToClassObjectFunc(binding: ClassBinding): FunSpec {
        val builder = FunSpec.builder("to${binding.getSimpleName()}Object")
            .addModifiers(KModifier.INTERNAL)
            .receiver(String::class)
            .returns(binding.getClassName())
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
                builder.addStatement("obj.opt(%S).toString().${Primitive(binding.getType()).toTypeFunc(Primitive.Kind.PRIMITIVE)},", binding.getKeyName())
            }
            binding.isNullablePrimitive() -> {
                builder.addStatement("obj.opt(%S)?.toString()?.${Primitive(binding.getType()).toTypeFunc(Primitive.Kind.NULLABLE)},", binding.getKeyName())
            }
            binding.isDeclared() -> {
                val declaredType = binding.getType() as DeclaredType
                when {
                    binding.isExtendedBy(CharSequence::class.java) -> {
                        builder.addStatement("obj.optString(%S, null),", binding.getKeyName())  // String
                    }
                    binding.isExtendedBy(Collection::class.java) -> {

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
}