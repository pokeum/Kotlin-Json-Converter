package com.example.kotlinjsonconverter.codegen_sample

import co.ab180.abson.JsonName
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class PetFood(
    @JvmField
    @SerialName("brand")         // codegen
    @JsonName("brand")           // abson
    @SerializedName("brand")     // gson
    val brand: FoodBrand,
    @JvmField
    @SerialName("label")
    @JsonName("label")
    @SerializedName("label")
    val label: String,
    @JvmField
    @SerialName("price")
    @JsonName("price")
    @SerializedName("price")
    val price: Float
) { override fun toString() = "{brand: $brand, label: $label, price: $price}" }
