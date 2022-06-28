package com.example.kotlinjsonconverter.abson_test.kotlin.codegen_sample

import co.ab180.abson.serialization.serialize
import com.example.kotlinjsonconverter.codegen_sample.CrazyPet
import com.example.kotlinjsonconverter.codegen_test.generator.generate_CrazyPet_Cat
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class CrazyPetTest {

    @Test
    fun test_serialization() {

        val crazyPet = generate_CrazyPet_Cat()
        val crazyPetJsonString = serialize(crazyPet)
        Assert.assertEquals(
            crazyPet,
            Gson().fromJson(crazyPetJsonString, CrazyPet::class.java)
        )
    }
}