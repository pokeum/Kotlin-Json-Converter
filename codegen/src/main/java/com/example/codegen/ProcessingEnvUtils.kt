package com.example.codegen

import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

/** ProcessingEnvironment Wrapper */
object ProcessingEnvUtils {

    // [Backing fields] https://kotlinlang.org/docs/properties.html#backing-fields
    private lateinit var _messager: Messager
    val messager: Messager get() = _messager

    private lateinit var _typeUtils: Types
    val typeUtils: Types get() = _typeUtils

    private lateinit var _elementUtils: Elements
    val elementUtils: Elements get() = _elementUtils

    private lateinit var _filer: Filer
    val filer: Filer get() = _filer

    fun init(processingEnv: ProcessingEnvironment) {
        _messager = processingEnv.messager
        _typeUtils = processingEnv.typeUtils
        _elementUtils = processingEnv.elementUtils
        _filer = processingEnv.filer
    }

    fun printMessage(kind: Diagnostic.Kind, message: CharSequence) {
        _messager.printMessage(kind, message)
    }

    fun printMessage(kind: Diagnostic.Kind, message: CharSequence, element: Element) {
        _messager.printMessage(kind, message, element)
    }
}