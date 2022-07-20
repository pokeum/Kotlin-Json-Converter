package com.example.kotlinjsonconverter.sample

import com.beust.klaxon.Json
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class PetFood(
    @JvmField
    @SerialName("brand")        // codegen
    @SerializedName("brand")    // gson
    @Json(                      // klaxon
        name = "brand",
        serializeNull = false
    )
    val brand: FoodBrand?,
    //val brand: FoodBrand,
    @JvmField
    @SerialName("label")
    @SerializedName("label")
    @Json(
        name = "label",
        serializeNull = false
    )
    val label: String,
    @JvmField
    @SerialName("price")
    @SerializedName("price")
    @Json(
        name = "price",
        serializeNull = false
    )
    val price: Float
) { override fun toString() = "{brand: $brand, label: $label, price: $price}" }
