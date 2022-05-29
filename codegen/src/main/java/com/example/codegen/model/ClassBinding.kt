package com.example.codegen.model

import javax.lang.model.element.TypeElement
import com.squareup.kotlinpoet.asClassName

class ClassBinding(private val classElement: TypeElement) {

    private val fieldBindingInfo = mutableMapOf<String, FieldBinding>()

    fun getSimpleName() = classElement.simpleName.toString()

    fun getClassName() = classElement.asClassName()

    fun addFieldBinding(fieldBinding: FieldBinding) {
        fieldBindingInfo.putIfAbsent(fieldBinding.getKeyName(), fieldBinding)
    }

    fun getFieldBindings(): Collection<FieldBinding> {
        return fieldBindingInfo.values
    }
}