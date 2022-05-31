package com.example.kotlinjsonconverter.sample

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
    //val foods: Map<String, PetFood?>?
    //val foods: Collection<PetFood?>?
    //val foods: Map<String, Map<String, PetFood?>?>?
    //val foods: Map<String, Map<String, String?>?>?
    //val foods: Map<String, Collection<PetFood?>?>?
    //val foods: Map<String, Collection<String?>?>?
    //val foods: Collection<Map<String, PetFood?>?>?
    //val foods: Collection<Map<String, String?>?>?
    //val foods: Collection<Collection<PetFood?>?>?
    //val foods: Collection<Collection<String?>?>?
    val foods: Collection<Collection<Collection<PetFood?>?>?>?
)
