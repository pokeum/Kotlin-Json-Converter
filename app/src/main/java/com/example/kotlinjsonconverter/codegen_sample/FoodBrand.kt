package com.example.kotlinjsonconverter.codegen_sample

import co.ab180.abson.JsonName
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class FoodBrand(
    @JvmField
    @SerialName("company")      // codegen
    @JsonName("company")        // abson
    @SerializedName("company")  // gson
    val company: String,
    @JvmField
    @SerialName("owner")
    @JsonName("owner")
    @SerializedName("owner")
    val owner: String?,
    @JvmField
    @SerialName("founding year")
    @JsonName("founding year")
    @SerializedName("founding year")
    val year: Int?
) { override fun toString() = "{company: $company, owner: $owner, year: $year}" }
