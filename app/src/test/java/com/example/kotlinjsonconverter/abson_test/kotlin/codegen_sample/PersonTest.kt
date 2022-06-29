package com.example.kotlinjsonconverter.abson_test.kotlin.codegen_sample

import co.ab180.abson.serialization.serialize
import com.example.kotlinjsonconverter.codegen_sample.Person
import com.example.kotlinjsonconverter.codegen_test.generator.generate_Person
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class PersonTest {

    @Test
    fun test_serialization() {

        val person = generate_Person()
        val personJsonString = serialize(person)
        Assert.assertEquals(person, Gson().fromJson(personJsonString, Person::class.java))
    }
}