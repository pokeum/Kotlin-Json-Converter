package com.example.kotlinjsonconverter.codegen.kotlin.sample

import com.example.kotlinjsonconverter.sample.Pet
import com.example.kotlinjsonconverter.sample.stub.toPetObject
import com.example.kotlinjsonconverter.sample.toJSONObject
import com.example.kotlinjsonconverter.generator.generate_Pet_None
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class PetTest {

    @Test
    fun test() {

        /** Serialization */
        //val pet = generate_Pet_Cat()
        val pet = generate_Pet_None()
        val petJsonString = pet.toJSONObject().toString(4)
        println(petJsonString)
        Assert.assertEquals(pet, Gson().fromJson(petJsonString, Pet::class.java))

        /** Deserialization */
        val petObj = petJsonString.toPetObject()
        Assert.assertEquals(pet, petObj)
    }
}