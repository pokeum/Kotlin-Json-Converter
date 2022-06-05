package com.example.kotlinjsonconverter

import org.junit.Test

import com.example.kotlinjsonconverter.generator.generate_FoodBrand_9Lives
import com.example.kotlinjsonconverter.generator.generate_Person
import com.example.kotlinjsonconverter.generator.generate_Pet_Cat
import com.example.kotlinjsonconverter.sample.PetFood
import com.example.kotlinjsonconverter.sample.test.PrimitiveTest
import com.example.kotlinjsonconverter.sample.stub.toFoodBrandObject
import com.example.kotlinjsonconverter.sample.stub.toPetFoodObject
import com.example.kotlinjsonconverter.sample.stub.toPetObject
import com.example.kotlinjsonconverter.sample.stub.toPrimitiveTestObject
import com.example.kotlinjsonconverter.sample.test.toJSONObject
import com.example.kotlinjsonconverter.sample.toJSONObject

import com.example.kotlinjsonconverter.sample.stub.toJSONObject as customToJSONObject

class JsonConverterTest {

    @Test
    fun jsonTest_Person() {
        println("===> JSONObject")
        val person = generate_Person()
        val personJsonString = person.toJSONObject().toString(4)
        println(personJsonString)
    }

    @Test
    fun jsonTest_Pet() {
        println("===> JSONObject")
        val pet = generate_Pet_Cat()
        //val petJsonString = pet.toJSONObject().toString(4)
        val petJsonString = pet.customToJSONObject().toString(4)
        println(petJsonString)

        println("===> Pet")
        val petFoodObj = petJsonString.toPetObject()
        println(petFoodObj)
    }

    @Test
    fun jsonTest_FoodBrand() {
        println("===> JSONObject")
        val foodBrand = generate_FoodBrand_9Lives()
        val foodBrandJsonString = foodBrand.toJSONObject().toString(4)
        println(foodBrandJsonString)

        println("===> FoodBrand")
        val foodBrandObj = foodBrandJsonString.toFoodBrandObject()
        println(foodBrandObj)
    }

    @Test
    fun jsonTest_PetFood() {
        println("===> JSONObject")
        val petFood = PetFood(generate_FoodBrand_9Lives(), "label", 12.34f)
        val petFoodJsonString = petFood.toJSONObject().toString(4)
        println(petFoodJsonString)

        println("===> PetFood")
        val petFoodObj = petFoodJsonString.toPetFoodObject()
        println(petFoodObj)
    }

    @Test
    fun jsonTest_PrimitiveTest() {
        val primitiveTest = PrimitiveTest(
            null, 1,
            null, 2,
            null, 3,
            null, 5,
            null, 6.7f,
            null, 8.9,
            null, true,
            null, 'A',
            null, "Add String Test",
            listOf(1, 2, null, 3, 4),
            mapOf("hello1" to 10, "hello2" to 20, "hello_none" to null)
        )
        println("===> JSONObject")
        val primitiveTestString = primitiveTest.toJSONObject().toString(4)
        println(primitiveTestString)

        println("===> PrimitiveTest")
        val primitiveTestObj = primitiveTestString.toPrimitiveTestObject()
        println(primitiveTestObj)
    }
}