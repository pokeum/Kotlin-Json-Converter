package com.example.codegen.recursion

import com.example.codegen.extension.isExtendedBy
import com.example.codegen.extension.isNullablePrimitive
import com.example.codegen.model.FieldBinding
import javax.lang.model.type.TypeMirror

class FieldInfo(
    val binding: FieldBinding,
    val currentType: TypeMirror,
    val iterableObject: String,
    val parentJsonObject: String,
    val key: String?,
    val isFirst: Boolean,
    private var objectID: Int
) {
    constructor(binding: FieldBinding) : this(binding, binding.getType(), binding.getFieldName(),
        "obj", binding.getKeyName(), true, 0)

    fun generateObjectName() = "obj_${objectID ++}"
    fun getObjectID() = objectID

    fun isOuterTypeCollection() = (key == null)

    fun isPrimitiveOrCharSequence() = currentType.kind.isPrimitive ||
            currentType.isNullablePrimitive() ||
            currentType.isExtendedBy(CharSequence::class.java)

    fun next(nestedType: TypeMirror, isOuterTypeCollection: Boolean) : FieldInfo {
        var iterableObject: String
        var key: String?
        val parentJsonObject = "obj_${objectID - 2}"
        if (isOuterTypeCollection) {
            iterableObject = "obj_${objectID - 1}"
            key = null
        } else {
            iterableObject = "obj_${objectID - 1}.value"
            key = "obj_${objectID - 1}.key"
        }
        return FieldInfo(binding, nestedType, iterableObject, parentJsonObject, key, false, objectID)
    }
}