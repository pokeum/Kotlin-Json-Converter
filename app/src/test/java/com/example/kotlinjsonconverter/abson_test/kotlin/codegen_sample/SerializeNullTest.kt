package com.example.kotlinjsonconverter.abson_test.kotlin.codegen_sample

import co.ab180.abson.serialization.serialize
import com.example.kotlinjsonconverter.codegen_sample.FoodBrand
import com.example.kotlinjsonconverter.codegen_sample.PetFood
import com.example.kotlinjsonconverter.codegen_test.generator.generate_FoodBrand_9Lives
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class SerializeNullTest {

    @Test
    fun test_with_FoodBrand() {

        /** Serialization */
        val foodBrand = generate_FoodBrand_9Lives()
        val foodBrandJsonString = serialize(foodBrand)
        Assert.assertEquals(foodBrand, Gson().fromJson(foodBrandJsonString, FoodBrand::class.java))

        /** Deserialization */
    }

    @Test
    fun test_with_PetFood() {

        /** Serialization */
        val petFood = PetFood(generate_FoodBrand_9Lives(), "label", 12.34f)
        val petFoodJsonString = serialize(petFood)
        Assert.assertEquals(petFood, Gson().fromJson(petFoodJsonString, PetFood::class.java))

        /** Deserialization */
    }
}