package com.example.kotlinjsonconverter.abson_test.kotlin.codegen_sample

import co.ab180.abson.serialization.serialize
import com.example.kotlinjsonconverter.codegen_sample.test.AnyTest
import com.example.kotlinjsonconverter.codegen_sample.test.PrimitiveTest
import com.example.kotlinjsonconverter.codegen_test.generator.generate_Person
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

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
        val primitiveTestJsonString = serialize(primitiveTest)
        Assert.assertEquals(primitiveTest, Gson().fromJson(primitiveTestJsonString, PrimitiveTest::class.java))
    }

    @Test
    fun test_AnyTest() {

        val anyTest = AnyTest(generate_Person(), null)

        /** Serialization */
        val anyTestJsonString = serialize(anyTest)
        val gsonExpected = Gson().fromJson(
            GsonBuilder().serializeNulls().create().toJson(anyTest),
            AnyTest::class.java
        )
        Assert.assertEquals(gsonExpected, Gson().fromJson(anyTestJsonString, AnyTest::class.java))
    }
}