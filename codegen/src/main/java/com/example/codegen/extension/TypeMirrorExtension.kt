package com.example.codegen.extension

import com.example.codegen.ProcessingEnvUtils
import com.example.codegen.commons.Primitive
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror

fun TypeMirror.isNullablePrimitive() = Primitive(this).isNullablePrimitive()

fun <T> TypeMirror.isExtendedBy(`class`: Class<T>): Boolean {
    val superTypes = ProcessingEnvUtils.typeUtils.directSupertypes(this).toMutableList()
    superTypes.add(this)
    for (superType in superTypes) {
        val declaredType = superType as DeclaredType
        val element = declaredType.asElement()
        when (element.toString()) {
            `class`.canonicalName -> return true
        }
    }
    return false
}