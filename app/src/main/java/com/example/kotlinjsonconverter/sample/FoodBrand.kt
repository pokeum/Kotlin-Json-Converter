package com.example.kotlinjsonconverter.sample

import com.beust.klaxon.Json
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class FoodBrand(
    @JvmField
    @SerialName("company")      // codegen
    @SerializedName("company")  // gson
    @Json(                      // klaxon
        name = "company",
        serializeNull = false
    )
    val company: String,
    @JvmField
    @SerialName("owner")
    @SerializedName("owner")
    @Json(
        name = "owner",
        serializeNull = false
    )
    val owner: String?,
    @JvmField
    @SerialName("founding year")
    @SerializedName("founding year")
    @Json(
        name = "founding year",
        serializeNull = false
    )
    val year: Int?
) { override fun toString() = "{company: $company, owner: $owner, year: $year}" }
