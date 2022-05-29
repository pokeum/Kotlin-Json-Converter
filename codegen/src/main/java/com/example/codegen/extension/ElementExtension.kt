package com.example.codegen.extension

import com.example.codegen.ProcessingEnvUtils
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier
import javax.lang.model.element.PackageElement
import javax.lang.model.type.DeclaredType

fun Element.isAccessible(): Boolean {
    val modifiers: Set<Modifier> = this.modifiers
    return !modifiers.contains(Modifier.PRIVATE) && !modifiers.contains(Modifier.STATIC)
}
fun Element.isNotAccessible() = !isAccessible()

fun Element.getPackage(): PackageElement {
    var element = this
    while (element !is PackageElement) {
        element = element.enclosingElement
    }
    return element
}

fun Element.getEnclosedFieldElements() = enclosedElements.filter { it.kind.isField }

fun <T> Element.isExtendedBy(`class`: Class<T>): Boolean {
    val superTypes = ProcessingEnvUtils.typeUtils.directSupertypes(asType()).toMutableList()
    superTypes.add(asType())
    for (superType in superTypes) {
        // https://docs.oracle.com/javase/8/docs/api/javax/lang/model/type/DeclaredType.html
        val declaredType = superType as DeclaredType
        val element = declaredType.asElement()
        when (element.toString()) {
            `class`.canonicalName -> return true
        }
    }
    return false
}