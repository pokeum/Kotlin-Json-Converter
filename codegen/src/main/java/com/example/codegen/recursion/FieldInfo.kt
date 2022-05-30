package com.example.codegen.recursion

import com.example.codegen.extension.isExtendedBy
import com.example.codegen.extension.isNullablePrimitive
import com.example.codegen.model.FieldBinding
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

class FieldInfo(
    val binding: FieldBinding,
    val currentType: TypeMirror,
    private var objectID: Int = 0
) {
    val isFirst: Boolean = (objectID == 0)

    var valueObject: String = binding.getFieldName()
    var putObject: String = "obj"
    var keyName: String? = binding.getKeyName()

    fun generateObjectName() = "obj_${objectID ++}"
    fun getObjectID() = objectID

    fun isOuterTypeMap() = (keyName != null)

    fun isDefault() = currentType.kind.isPrimitive ||
            currentType.isNullablePrimitive() ||
            currentType.isExtendedBy(CharSequence::class.java)

    fun isDeclared() = currentType.kind.equals(TypeKind.DECLARED)

    fun next(nestedType: TypeMirror, isOuterTypeMap: Boolean) : FieldInfo {
        val nextNestedInfo = FieldInfo(binding, nestedType, objectID)
        if (isOuterTypeMap) {
            nextNestedInfo.valueObject = "obj_${objectID - 1}.value"
            nextNestedInfo.keyName = "obj_${objectID - 1}.key"
        } else {
            nextNestedInfo.valueObject = "obj_${objectID - 1}"
            nextNestedInfo.keyName = null
        }
        nextNestedInfo.putObject = "obj_${objectID - 2}"
        return nextNestedInfo
    }
}