package com.example.kotlinjsonconverter.abson.kotlin.sample

import co.ab180.abson.serialization.serialize
import com.example.kotlinjsonconverter.sample.Pet
import com.example.kotlinjsonconverter.generator.generate_Pet_Cat
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class PetTest {

    @Test
    fun test_serialization() {

        val pet = generate_Pet_Cat()
        val petJsonString = serialize(pet)
        Assert.assertEquals(pet, Gson().fromJson(petJsonString, Pet::class.java))
    }
}