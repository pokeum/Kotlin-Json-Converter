package com.example.kotlinjsonconverter.sample

import com.example.annotation.SerialName
import com.example.annotation.Serializable

@Serializable
data class Hobby(
    @JvmField
    @SerialName("type")
    val type: String,
    @JvmField
    @SerialName("time")
    val time: Int
)