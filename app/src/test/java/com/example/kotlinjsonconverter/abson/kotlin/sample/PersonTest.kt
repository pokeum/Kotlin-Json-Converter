package com.example.kotlinjsonconverter.abson.kotlin.sample

import co.ab180.abson.serialization.serialize
import com.example.kotlinjsonconverter.sample.Person
import com.example.kotlinjsonconverter.generator.generate_Person
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