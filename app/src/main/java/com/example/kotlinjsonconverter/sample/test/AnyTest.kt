package com.example.kotlinjsonconverter.sample.test

import com.beust.klaxon.Json
import com.google.gson.annotations.SerializedName

//@Serializable @Debug
data class AnyTest(
    //@JvmField @SerialName("any") @Debug
    @SerializedName("any")
    @Json(
        name = "any",
        serializeNull = false
    )
    val any: Any,
    //@JvmField @SerialName("any_nullable") @Debug
    @SerializedName("any_nullable")
    @Json(
        name = "any_nullable",
        serializeNull = false
    )
    val anyNullable: Any?,
)