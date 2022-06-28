package com.example.kotlinjsonconverter.codegen_test

import com.example.kotlinjsonconverter.codegen_sample.toJSONObject
import com.example.kotlinjsonconverter.codegen_test.generator.generate_CrazyPet_Cat
import com.google.gson.GsonBuilder
import org.junit.Test

class CrazyPetTest {

    @Test
    fun test() {

        println("===> JSONObject")
        val crazyPet = generate_CrazyPet_Cat()
        val crazyPetJsonString = crazyPet.toJSONObject().toString(4)
        println(crazyPetJsonString)

        println("===> GSON")
        val crazyPetGsonString = GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create().toJson(crazyPet)
        println(crazyPetGsonString)
    }
}