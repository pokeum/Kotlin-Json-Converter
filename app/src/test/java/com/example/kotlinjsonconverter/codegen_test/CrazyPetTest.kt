package com.example.kotlinjsonconverter.codegen_test

import com.example.kotlinjsonconverter.codegen_sample.CrazyPet
import com.example.kotlinjsonconverter.codegen_sample.toJSONObject
import com.example.kotlinjsonconverter.codegen_test.generator.generate_CrazyPet_Cat
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