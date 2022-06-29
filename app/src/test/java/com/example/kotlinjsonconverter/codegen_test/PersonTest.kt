package com.example.kotlinjsonconverter.codegen_test

import com.example.kotlinjsonconverter.codegen_sample.Person
import com.example.kotlinjsonconverter.codegen_sample.toJSONObject
import com.example.kotlinjsonconverter.codegen_test.generator.generate_Person
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class PersonTest {

    @Test
    fun test_serialization() {

        val person = generate_Person()
        val personJsonString = person.toJSONObject().toString(4)
        println(personJsonString)
        Assert.assertEquals(person, Gson().fromJson(personJsonString, Person::class.java))
    }
}