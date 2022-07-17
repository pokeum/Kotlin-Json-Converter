package com.example.kotlinjsonconverter.abson.kotlin.sample

import co.ab180.abson.serialization.serialize
import com.example.kotlinjsonconverter.sample.CrazyPet
import com.example.kotlinjsonconverter.generator.generate_CrazyPet_Cat
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class CrazyPetTest {

    @Test
    fun test_serialization() {

        val crazyPet = generate_CrazyPet_Cat()
        val crazyPetJsonString = serialize(crazyPet)
        Assert.assertEquals(crazyPet, Gson().fromJson(crazyPetJsonString, CrazyPet::class.java))
    }
}