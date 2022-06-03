package com.example.kotlinjsonconverter.sample.test

import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.example.annotation.debug.Debug

//@Debug
//@Serializable
data class AnyTest(
    @JvmField
    //@Debug
    //@SerialName("any")
    val any: Any,
    @JvmField
    //@Debug
    //@SerialName("any_nullable")
    val anyNullable: Any?,
)