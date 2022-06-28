package com.example.kotlinjsonconverter.codegen_sample

import com.example.annotation.SerialName
import com.example.annotation.Serializable

@Serializable
data class Pet(
    @JvmField
    @SerialName("type")
    val type: String,
    @JvmField
    @SerialName("name")
    val name: String,
    @JvmField
    @SerialName("mine")
    val mine: Boolean?,
    @JvmField
    @SerialName("weight")
    val weight: Double?,
    @JvmField
    @SerialName("gender")
    val gender: Char?,
    @JvmField
    @SerialName("foods")
    val foods: Collection<Collection<String?>?>?
)
