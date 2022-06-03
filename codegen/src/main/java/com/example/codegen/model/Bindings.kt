package com.example.codegen.model

import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.example.annotation.debug.Debug
import com.example.codegen.ProcessingEnvUtils
import com.example.codegen.commons.Logger
import com.example.codegen.extension.getEnclosedFieldElements
import com.example.codegen.extension.getPackage
import com.example.codegen.extension.isNotAccessible
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

class Bindings {

    private val packageBindingInfo = mutableMapOf<PackageElement, PackageBinding>()

    fun build(env: RoundEnvironment) {
        val classElements = env.getElementsAnnotatedWith(Serializable::class.java)
        for (classElement in classElements) {
            if (classElement.isNotAccessible()) {
                ProcessingEnvUtils.printMessage(Diagnostic.Kind.ERROR,
                    "Class is not accessible, it cannot be private or static to bind")
            }
            val packageElement = classElement.getPackage()
            val packageBinding = packageBindingInfo.computeIfAbsent(packageElement) { PackageBinding(packageElement) }
            val classBinding = packageBinding.getClassBinding(classElement as TypeElement)
            if (classElement.getAnnotation(Debug::class.java) != null) {
                Logger.log(TAG, "serializable class name : ${classBinding.getSimpleName()}")
            }
            val fieldElements = classElement.getEnclosedFieldElements()
            for (fieldElement in fieldElements) {
                val annotation = fieldElement.getAnnotation(SerialName::class.java) ?: continue
                if (fieldElement.isNotAccessible()) {
                    ProcessingEnvUtils.printMessage(Diagnostic.Kind.ERROR,
                        "Field is not accessible, it cannot be private or static to bind",
                        fieldElement)
                }
                val fieldBinding = FieldBinding(fieldElement, annotation)
                classBinding.addFieldBinding(fieldBinding)
                if (fieldElement.getAnnotation(Debug::class.java) != null) {
                    Logger.log(TAG, "serialName field key : ${fieldBinding.getKeyName()} (${fieldBinding.getType()})")
                }
            }
        }
    }

    fun getPackageBindings(): Collection<PackageBinding> {
        return packageBindingInfo.values
    }

    companion object {
        private const val TAG = "Bindings"
    }
}