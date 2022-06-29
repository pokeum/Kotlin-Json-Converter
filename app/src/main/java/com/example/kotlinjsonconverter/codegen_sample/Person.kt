package com.example.kotlinjsonconverter.codegen_sample

import co.ab180.abson.JsonName
import com.example.annotation.SerialName
import com.example.annotation.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class Person(
    @JvmField
    @SerialName("name")         // codegen
    @JsonName("name")           // abson
    @SerializedName("name")     // gson
    val name: String,
    @JvmField
    @SerialName("nickname")
    @JsonName("nickname")
    @SerializedName("nickname")
    val nickname: String?,
    @JvmField
    @SerialName("age")
    @JsonName("age")
    @SerializedName("age")
    val age: Int,
    @JvmField
    @SerialName("simple_hobbies")
    @JsonName("simple_hobbies")
    @SerializedName("simple_hobbies")
    val simpleHobbies: Collection<String>,
    @JvmField
    @SerialName("hobbies")
    @JsonName("hobbies")
    @SerializedName("hobbies")
    val hobbies: Collection<Hobby>,
    @JvmField
    @SerialName("simple_pets")
    @JsonName("simple_pets")
    @SerializedName("simple_pets")
    val simplePets: Map<String, String?>,
    @JvmField
    @SerialName("pets")
    @JsonName("pets")
    @SerializedName("pets")
    val pets: Map<String, Pet?>,
    @JvmField
    @SerialName("card deck")
    @JsonName("card deck")
    @SerializedName("card deck")
    val cardDeck: Collection<Int?>
)
