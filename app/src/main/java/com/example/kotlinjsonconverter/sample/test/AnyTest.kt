package com.example.kotlinjsonconverter.sample.test

import co.ab180.abson.JsonName
import com.google.gson.annotations.SerializedName

//@Serializable @Debug
data class AnyTest(
    //@JvmField @SerialName("any") @Debug
    @JsonName("any")
    @SerializedName("any")
    val any: Any,
    //@JvmField @SerialName("any_nullable") @Debug
    @JsonName("any_nullable")
    @SerializedName("any_nullable")
    val anyNullable: Any?,
)