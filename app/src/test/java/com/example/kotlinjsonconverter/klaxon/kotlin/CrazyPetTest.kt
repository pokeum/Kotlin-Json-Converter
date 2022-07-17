package com.example.kotlinjsonconverter.klaxon.kotlin

import com.beust.klaxon.Klaxon
import com.example.kotlinjsonconverter.generator.generate_CrazyPet_Cat
import com.example.kotlinjsonconverter.sample.CrazyPet
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class CrazyPetTest {

    @Test
    fun test_serialization() {

        val crazyPet = generate_CrazyPet_Cat()
        val crazyPetJsonString = Klaxon().toJsonString(crazyPet)
        //println(crazyPetJsonString)
        Assert.assertEquals(crazyPet, Gson().fromJson(crazyPetJsonString, CrazyPet::class.java))
    }

    @Test
    fun test_deserialization() {

        val crazyPet = generate_CrazyPet_Cat()
        //val crazyPetJsonString = GsonBuilder().serializeNulls().create().toJson(crazyPet)
        val crazyPetJsonString = Gson().toJson(crazyPet)
        Assert.assertEquals(crazyPet, Klaxon().parse<CrazyPet>(crazyPetJsonString))
    }
}