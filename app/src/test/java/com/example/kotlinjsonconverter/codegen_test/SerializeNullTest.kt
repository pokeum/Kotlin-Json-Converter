package com.example.kotlinjsonconverter.codegen_test

import com.example.kotlinjsonconverter.codegen_sample.PetFood
import com.example.kotlinjsonconverter.codegen_sample.stub.toFoodBrandObject
import com.example.kotlinjsonconverter.codegen_sample.stub.toPetFoodObject
import com.example.kotlinjsonconverter.codegen_sample.toJSONObject
import com.example.kotlinjsonconverter.codegen_test.generator.generate_FoodBrand_9Lives
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class SerializeNullTest {

    @Test
    fun test_with_FoodBrand() {

        /** Serialization */

        println("===> JSONObject")
        val foodBrand = generate_FoodBrand_9Lives()
        val foodBrandJsonString = foodBrand.toJSONObject().toString()
        println(foodBrandJsonString)

        println("===> GSON (serialize null field)")
        val foodBrandGsonString = GsonBuilder()
            .serializeNulls()
            .create().toJson(foodBrand)
        println(foodBrandGsonString)

        /** Deserialization */
        val foodBrandObj = foodBrandJsonString.toFoodBrandObject()
        Assert.assertEquals(foodBrand, foodBrandObj)
    }

    @Test
    fun test_with_PetFood() {

        /** Serialization */

        println("===> JSONObject")
        val petFood = PetFood(generate_FoodBrand_9Lives(), "label", 12.34f)
        val petFoodJsonString = petFood.toJSONObject().toString()
        println(petFoodJsonString)

        println("===> GSON (serialize null field)")
        val petFoodGsonString = GsonBuilder()
            .serializeNulls()
            .create().toJson(petFood)
        println(petFoodGsonString)

        /** Deserialization */
        val petFoodObj = petFoodJsonString.toPetFoodObject()
        Assert.assertEquals(petFood, petFoodObj)
    }
}