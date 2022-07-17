package com.example.kotlinjsonconverter.codegen.kotlin.sample

import org.junit.Test
import org.junit.Assert.assertEquals

import com.example.kotlinjsonconverter.sample.test.PrimitiveTest
import com.example.kotlinjsonconverter.sample.stub.toPrimitiveTestObject
import com.example.kotlinjsonconverter.sample.test.toJSONObject
import com.google.gson.Gson

class JsonConverterTest {

    @Test
    fun test_PrimitiveTest() {

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

        /** Serialization */
        val primitiveTestString = primitiveTest.toJSONObject().toString(4)
        println(primitiveTestString)
        assertEquals(primitiveTest, Gson().fromJson(primitiveTestString, PrimitiveTest::class.java))

        /** Deserialization */
        val primitiveTestObj = primitiveTestString.toPrimitiveTestObject()
        assertEquals(primitiveTest, primitiveTestObj)
    }
}