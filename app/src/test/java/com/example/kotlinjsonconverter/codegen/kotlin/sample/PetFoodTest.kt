package com.example.kotlinjsonconverter.codegen.kotlin.sample

import com.example.kotlinjsonconverter.generator.generate_CatFood_A
import com.example.kotlinjsonconverter.sample.PetFood
import com.example.kotlinjsonconverter.sample.stub.toPetFoodObject
import com.example.kotlinjsonconverter.sample.toJSONObject
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class PetFoodTest {

    @Test
    fun test() {

        /** Serialization */
        val petFood = generate_CatFood_A()
        val petFoodJsonString = petFood.toJSONObject().toString(4)
        println(petFoodJsonString)
        Assert.assertEquals(petFood, Gson().fromJson(petFoodJsonString, PetFood::class.java))

        /** Deserialization */
        val petFoodObj = petFoodJsonString.toPetFoodObject()
        Assert.assertEquals(petFood, petFoodObj)
    }
}