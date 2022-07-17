package com.example.kotlinjsonconverter.sample

import com.beust.klaxon.Json
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class Hobby(
    @JvmField
    @SerialName("type")         // codegen
    @SerializedName("type")     // gson
    @Json(                      // klaxon
        name = "type",
        serializeNull = false
    )
    val type: String,
    @JvmField
    @SerialName("time")
    @SerializedName("time")
    @Json(
        name = "time",
        serializeNull = false
    )
    val time: Int
)