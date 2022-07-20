package com.example.codegen

import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.example.codegen.model.Bindings
import com.google.auto.service.AutoService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class CodeGenProcessor : AbstractProcessor() {

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        ProcessingEnvUtils.init(processingEnv)
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val bindings = Bindings()
        bindings.build(roundEnv)
        generateBinderClasses(bindings)
        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(
            Serializable::class.java.canonicalName,
            SerialName::class.java.canonicalName
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    private fun generateBinderClasses(bindings: Bindings) {
        for (packageBinding in bindings.getPackageBindings()) {
            for (classBinding in packageBinding.getClassBindings()) {
                val fileSpec = CodeGen.createFileSpec(packageBinding.getPackageName(), classBinding)
                fileSpec.writeTo(ProcessingEnvUtils.filer)
            }
        }
    }
}