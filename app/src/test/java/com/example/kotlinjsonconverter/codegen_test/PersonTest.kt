package com.example.kotlinjsonconverter.codegen_test

import com.example.kotlinjsonconverter.codegen_sample.toJSONObject
import com.example.kotlinjsonconverter.codegen_test.generator.generate_Person
import com.google.gson.GsonBuilder
import org.junit.Test

class PersonTest {

    @Test
    fun test() {
        println("===> JSONObject")
        val person = generate_Person()
        val personJsonString = person.toJSONObject().toString(4)
        println(personJsonString)

        println("===> GSON")
        val personGsonString = GsonBuilder()
            .setPrettyPrinting()
            .create().toJson(person)
        println(personGsonString)
    }
}