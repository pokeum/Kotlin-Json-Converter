package com.example.codegen.model

import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement

class PackageBinding(private val packageElement: PackageElement) {

    // https://docs.oracle.com/javase/8/docs/api/javax/lang/model/element/TypeElement.html
    private val classBindingInfo = mutableMapOf<TypeElement, ClassBinding>()

    fun getPackageName() = packageElement.qualifiedName.toString()

    fun getClassBinding(element: TypeElement): ClassBinding {
        return classBindingInfo.computeIfAbsent(element) { ClassBinding(element) }
    }

    fun getClassBindings(): Collection<ClassBinding> {
        return classBindingInfo.values
    }
}