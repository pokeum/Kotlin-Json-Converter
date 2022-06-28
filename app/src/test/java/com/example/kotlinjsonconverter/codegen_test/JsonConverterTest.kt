package com.example.kotlinjsonconverter.codegen_test

import org.junit.Test
import org.junit.Assert.assertEquals

import com.example.kotlinjsonconverter.codegen_sample.test.PrimitiveTest
import com.example.kotlinjsonconverter.codegen_sample.stub.toPrimitiveTestObject
import com.example.kotlinjsonconverter.codegen_sample.test.toJSONObject
import com.google.gson.GsonBuilder

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

        println("===> JSONObject")
        val primitiveTestString = primitiveTest.toJSONObject().toString(4)
        println(primitiveTestString)

        println("===> GSON")
        val primitiveTestGsonString = GsonBuilder()
            .setPrettyPrinting()
            .create().toJson(primitiveTest)
        println(primitiveTestGsonString)

        /** Deserialization */
        val primitiveTestObj = primitiveTestString.toPrimitiveTestObject()
        assertEquals(primitiveTest, primitiveTestObj)
    }
}