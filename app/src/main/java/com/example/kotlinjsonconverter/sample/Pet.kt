package com.example.kotlinjsonconverter.sample

import co.ab180.abson.JsonName
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class Pet(
    @JvmField
    @SerialName("type")         // codegen
    @JsonName("type")           // abson
    @SerializedName("type")     // gson
    val type: String,
    @JvmField
    @SerialName("name")
    @JsonName("name")
    @SerializedName("name")
    val name: String,
    @JvmField
    @SerialName("mine")
    @JsonName("mine")
    @SerializedName("mine")
    val mine: Boolean?,
    @JvmField
    @SerialName("weight")
    @JsonName("weight")
    @SerializedName("weight")
    val weight: Double?,
    @JvmField
    @SerialName("gender")
    @JsonName("gender")
    @SerializedName("gender")
    val gender: Char?,
    @JvmField
    @SerialName("foods")
    @JsonName("foods")
    @SerializedName("foods")
    val foods: Collection<Collection<String?>?>?
)
