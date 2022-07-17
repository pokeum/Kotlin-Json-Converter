package com.example.kotlinjsonconverter.sample

import co.ab180.abson.JsonName
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class Hobby(
    @JvmField
    @SerialName("type")      // codegen
    @JsonName("type")        // abson
    @SerializedName("type")  // gson
    val type: String,
    @JvmField
    @SerialName("time")
    @JsonName("time")
    @SerializedName("time")
    val time: Int
)