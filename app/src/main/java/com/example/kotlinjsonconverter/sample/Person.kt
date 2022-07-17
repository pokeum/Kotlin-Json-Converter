package com.example.kotlinjsonconverter.sample

import com.beust.klaxon.Json
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class Person(
    @JvmField
    @SerialName("name")         // codegen
    @SerializedName("name")     // gson
    @Json(                      // klaxon
        name = "name",
        serializeNull = false
    )
    val name: String,
    @JvmField
    @SerialName("nickname")
    @SerializedName("nickname")
    @Json(
        name = "nickname",
        serializeNull = false
    )
    val nickname: String?,
    @JvmField
    @SerialName("age")
    @SerializedName("age")
    @Json(
        name = "age",
        serializeNull = false
    )
    val age: Int,
    @JvmField
    @SerialName("simple_hobbies")
    @SerializedName("simple_hobbies")
    @Json(
        name = "simple_hobbies",
        serializeNull = false
    )
    val simpleHobbies: Collection<String>,
    @JvmField
    @SerialName("hobbies")
    @SerializedName("hobbies")
    @Json(
        name = "hobbies",
        serializeNull = false
    )
    val hobbies: Collection<Hobby>,
    @JvmField
    @SerialName("simple_pets")
    @SerializedName("simple_pets")
    @Json(
        name = "simple_pets",
        serializeNull = false
    )
    val simplePets: Map<String, String?>,
    @JvmField
    @SerialName("pets")
    @SerializedName("pets")
    @Json(
        name = "pets",
        serializeNull = false
    )
    val pets: Map<String, Pet?>,
    @JvmField
    @SerialName("card deck")
    @SerializedName("card deck")
    @Json(
        name = "card deck",
        serializeNull = false
    )
    val cardDeck: Collection<Int?>
)
