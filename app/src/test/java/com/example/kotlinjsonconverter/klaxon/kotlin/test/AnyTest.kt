package com.example.kotlinjsonconverter.klaxon.kotlin.test

import com.beust.klaxon.Klaxon
import com.example.kotlinjsonconverter.generator.*
import com.example.kotlinjsonconverter.sample.test.AnyTest
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class AnyTest {

    @Test
    fun test_serialization() {

        val any = AnyTest(
            mutableListOf(
                generate_Hobby_Hiking(),
                generate_Hobby_Painting(),
                generate_Hobby_Writing(),
                generate_Hobby_Cooking()
            ),
            null
        )
        val anyJsonStringActual = Klaxon().toJsonString(any)
        val anyJsonStringExpected = Gson().toJson(any)
        println(anyJsonStringActual)
        Assert.assertEquals(Gson().fromJson(anyJsonStringExpected, AnyTest::class.java),
            Gson().fromJson(anyJsonStringActual, AnyTest::class.java))
    }

    @Test
    fun test_deserialization() {

        //val any = AnyTest(mutableListOf("abc", "def"), null)
        val any = AnyTest(
            mutableListOf(
                generate_Hobby_Hiking(),
                generate_Hobby_Painting(),
                generate_Hobby_Writing(),
                generate_Hobby_Cooking()
            ),
            null
        )
        val anyJsonString = Gson().toJson(any)
        Assert.assertEquals(any, Klaxon().parse<AnyTest>(anyJsonString))
    }
}