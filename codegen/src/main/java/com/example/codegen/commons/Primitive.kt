package com.example.codegen.commons

import com.example.codegen.extension.isNullablePrimitive
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

class Primitive(private val typeMirror: TypeMirror) {

    fun isNullablePrimitive(): Boolean =
        typeMirror.toString() == Type.BYTE.java ||
            typeMirror.toString() == Type.SHORT.java ||
            typeMirror.toString() == Type.INT.java ||
            typeMirror.toString() == Type.LONG.java ||
            typeMirror.toString() == Type.FLOAT.java ||
            typeMirror.toString() == Type.DOUBLE.java ||
            typeMirror.toString() == Type.BOOLEAN.java ||
            typeMirror.toString() == Type.CHAR.java

    fun toTypeFunc(): String {
        return if (typeMirror.isNullablePrimitive()) {
            if (nullableType() == "Char") { "get(0)" } else { "to${nullableType()}()" }
        } else {
            if (primitiveType() == "Char") { "get(0)" } else { "to${primitiveType()}()" }
        }
    }

    fun toType() = if (typeMirror.isNullablePrimitive()) { "${nullableType()}?" } else { primitiveType() }

    private fun primitiveType() = when (typeMirror.kind) {
        TypeKind.BYTE -> Type.BYTE.kotlin
        TypeKind.SHORT -> Type.SHORT.kotlin
        TypeKind.INT -> Type.INT.kotlin
        TypeKind.LONG -> Type.LONG.kotlin
        TypeKind.FLOAT -> Type.FLOAT.kotlin
        TypeKind.DOUBLE -> Type.DOUBLE.kotlin
        TypeKind.BOOLEAN -> Type.BOOLEAN.kotlin
        TypeKind.CHAR -> Type.CHAR.kotlin
        else -> throw RuntimeException("Invalid TypeKind: ${typeMirror.kind}")
    }

    private fun nullableType() = when (typeMirror.toString()) {
        Type.BYTE.java -> Type.BYTE.kotlin
        Type.SHORT.java -> Type.SHORT.kotlin
        Type.INT.java -> Type.INT.kotlin
        Type.LONG.java -> Type.LONG.kotlin
        Type.FLOAT.java -> Type.FLOAT.kotlin
        Type.DOUBLE.java -> Type.DOUBLE.kotlin
        Type.BOOLEAN.java -> Type.BOOLEAN.kotlin
        Type.CHAR.java -> Type.CHAR.kotlin
        else -> throw RuntimeException("Invalid Type: $typeMirror")
    }

    enum class Type(val kotlin: String, val java: String) {
        BYTE("Byte", "java.lang.Byte"),
        SHORT("Short", "java.lang.Short"),
        INT("Int", "java.lang.Integer"),
        LONG("Long", "java.lang.Long"),
        FLOAT("Float", "java.lang.Float"),
        DOUBLE("Double", "java.lang.Double"),
        BOOLEAN("Boolean", "java.lang.Boolean"),
        CHAR("Char", "java.lang.Character")
    }
}
