package com.example.kotlinjsonconverter.codegen_test

import com.example.kotlinjsonconverter.codegen_sample.FoodBrand
import com.example.kotlinjsonconverter.codegen_sample.PetFood
import com.example.kotlinjsonconverter.codegen_sample.stub.toFoodBrandObject
import com.example.kotlinjsonconverter.codegen_sample.stub.toPetFoodObject
import com.example.kotlinjsonconverter.codegen_sample.toJSONObject
import com.example.kotlinjsonconverter.codegen_test.generator.generate_FoodBrand_9Lives
import com.google.gson.Gson

import org.junit.Assert
import org.junit.Test

class SerializeNullTest {

    @Test
    fun test_with_FoodBrand() {

        /** Serialization */
        val foodBrand = generate_FoodBrand_9Lives()
        val foodBrandJsonString = foodBrand.toJSONObject().toString(4)
        println(foodBrandJsonString)
        Assert.assertEquals(foodBrand, Gson().fromJson(foodBrandJsonString, FoodBrand::class.java))

        /** Deserialization */
        val foodBrandObj = foodBrandJsonString.toFoodBrandObject()
        Assert.assertEquals(foodBrand, foodBrandObj)
    }

    @Test
    fun test_with_PetFood() {

        /** Serialization */
        val petFood = PetFood(generate_FoodBrand_9Lives(), "label", 12.34f)
        val petFoodJsonString = petFood.toJSONObject().toString(4)
        println(petFoodJsonString)
        Assert.assertEquals(petFood, Gson().fromJson(petFoodJsonString, PetFood::class.java))

        /** Deserialization */
        val petFoodObj = petFoodJsonString.toPetFoodObject()
        Assert.assertEquals(petFood, petFoodObj)
    }
}