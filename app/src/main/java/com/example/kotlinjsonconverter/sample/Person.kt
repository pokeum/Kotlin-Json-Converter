package com.example.kotlinjsonconverter.sample

import com.example.annotation.SerialName
import com.example.annotation.Serializable

@Serializable
data class Person(
    @JvmField
    @SerialName("name")
    val name: String,
    @JvmField
    @SerialName("nickname")
    val nickname: String?,
    @JvmField
    @SerialName("age")
    val age: Int,
    @JvmField
    @SerialName("simple_hobbies")
    val simpleHobbies: Collection<String>,
    @JvmField
    @SerialName("hobbies")
    val hobbies: Collection<Hobby>,
    @JvmField
    @SerialName("simple_pets")
    val simplePets: Map<String, String?>,
    //val simplePets: Map<String, Any?>,
    @JvmField
    @SerialName("pets")
    val pets: Map<String, Pet?>,
    //val pets: Map<String, Any?>,
    @JvmField
    @SerialName("card deck")
    val cardDeck: Collection<Int?>
)
