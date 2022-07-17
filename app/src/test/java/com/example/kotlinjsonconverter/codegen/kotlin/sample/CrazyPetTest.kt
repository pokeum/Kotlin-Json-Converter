package com.example.kotlinjsonconverter.codegen.kotlin.sample

import com.example.kotlinjsonconverter.sample.CrazyPet
import com.example.kotlinjsonconverter.sample.toJSONObject
import com.example.kotlinjsonconverter.generator.generate_CrazyPet_Cat
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class CrazyPetTest {

    @Test
    fun test_serialization() {

        val crazyPet = generate_CrazyPet_Cat()
        val crazyPetJsonString = crazyPet.toJSONObject().toString(4)
        println(crazyPetJsonString)
        Assert.assertEquals(crazyPet, Gson().fromJson(crazyPetJsonString, CrazyPet::class.java))
    }
}