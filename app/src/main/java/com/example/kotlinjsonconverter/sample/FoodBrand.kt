package com.example.kotlinjsonconverter.sample

import com.example.annotation.SerialName
import com.example.annotation.Serializable

@Serializable
data class FoodBrand(
    @JvmField
    @SerialName("company")
    val company: String,
    @JvmField
    @SerialName("owner")
    val owner: String?,
    @JvmField
    @SerialName("founding year")
    val year: Int?
) {
    override fun toString(): String {
        return "{company: $company, owner: $owner, year: $year}"
    }
}
