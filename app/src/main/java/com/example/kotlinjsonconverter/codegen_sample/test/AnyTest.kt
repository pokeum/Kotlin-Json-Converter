package com.example.kotlinjsonconverter.codegen_sample.test

//@Debug @Serializable
data class AnyTest(
    @JvmField
    //@Debug @SerialName("any")
    val any: Any,
    @JvmField
    //@Debug @SerialName("any_nullable")
    val anyNullable: Any?,
)