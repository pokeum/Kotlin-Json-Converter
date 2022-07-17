package com.example.kotlinjsonconverter.sample

import com.beust.klaxon.Json
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class CrazyPet(
    @JvmField
    @SerialName("type")         // codegen
    @SerializedName("type")     // gson
    @Json(                      // klaxon
        name = "type",
        serializeNull = false
    )
    val type: String,
    @JvmField
    @SerialName("name")
    @SerializedName("name")
    @Json(
        name = "name",
        serializeNull = false
    )
    val name: String,
    @JvmField
    @SerialName("mine")
    @SerializedName("mine")
    @Json(
        name = "mine",
        serializeNull = false
    )
    val mine: Boolean?,
    @JvmField
    @SerialName("weight")
    @SerializedName("weight")
    @Json(
        name = "weight",
        serializeNull = false
    )
    val weight: Double?,

    /** Klaxon doesn't support 'Char' Serialization */
    /*
    @JvmField
    @SerialName("gender")
    @SerializedName("gender")
    @Json(
        name = "gender",
        serializeNull = false
    )
    val gender: Char?,
    */

    @JvmField
    @SerialName("foods")
    @SerializedName("foods")
    @Json(
        name = "foods",
        serializeNull = false
    )
    val foods: Map<String, Collection<Map<String, Collection<PetFood?>>>>?
    //val foods: Any?
)
