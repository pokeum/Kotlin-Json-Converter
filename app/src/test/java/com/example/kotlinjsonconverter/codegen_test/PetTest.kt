package com.example.kotlinjsonconverter.codegen_test

import com.example.kotlinjsonconverter.codegen_sample.stub.toPetObject
import com.example.kotlinjsonconverter.codegen_sample.toJSONObject
import com.example.kotlinjsonconverter.codegen_test.generator.generate_Pet_Cat
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class PetTest {

    @Test
    fun test() {

        /** Serialization */

        println("===> JSONObject")
        val pet = generate_Pet_Cat()
        val petJsonString = pet.toJSONObject().toString()
        println(petJsonString)

        println("===> GSON")
        val petGsonString = Gson().toJson(pet)
        println(petGsonString)

        /** Deserialization */
        val petObj = petJsonString.toPetObject()
        Assert.assertEquals(pet, petObj)
    }
}