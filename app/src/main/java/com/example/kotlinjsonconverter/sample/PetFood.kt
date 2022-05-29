package com.example.kotlinjsonconverter.sample

import com.example.annotation.SerialName
import com.example.annotation.Serializable

@Serializable
data class PetFood(
    @JvmField
    @SerialName("brand")
    val brand: FoodBrand,
    @JvmField
    @SerialName("label")
    val label: String,
    @JvmField
    @SerialName("price")
    val price: Float
) {
    override fun toString(): String {
        return "{brand: $brand, label: $label, price: $price}"
    }
}
