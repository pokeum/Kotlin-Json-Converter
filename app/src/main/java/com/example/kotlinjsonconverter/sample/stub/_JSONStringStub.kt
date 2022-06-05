package com.example.kotlinjsonconverter.sample.stub

import com.example.kotlinjsonconverter.sample.FoodBrand
import com.example.kotlinjsonconverter.sample.PetFood
import com.example.kotlinjsonconverter.sample.test.PrimitiveTest
import org.json.JSONObject

internal fun String.toFoodBrandObject(): FoodBrand {
    val obj = JSONObject(this)
    return FoodBrand(
        obj.optString("company", null),
        obj.optString("owner", null),
        obj.opt("founding year")?.toString()?.toInt(),
    )
}

internal fun String.toPetFoodObject(): PetFood {
    val obj = JSONObject(this)
    return PetFood(
        obj.getJSONObject("brand").toString().toFoodBrandObject(),
        obj.optString("label", null),
        obj.opt("price").toString().toFloat(),
    )
}

/*
internal fun String.toPetObject(): Pet {
    val obj = JSONObject(this)
    return Pet(
        obj.optString("type", null),
        obj.optString("name", null),
        obj.opt("mine")?.toString()?.toBoolean(),
        obj.opt("weight")?.toString()?.toDouble(),
        obj.opt("gender")?.toString()?.get(0),

        //val foods: Collection<Collection<String?>?>?
        obj.optJSONArray("foods")?.let {
            val objA = mutableListOf<Collection<String?>?>()
            for (i in 0 until it.length()) {
                objA.add(it[i].let {
                    if (it == JSONObject.NULL) {
                        null
                    } else {
                        val objB = JSONArray(it.toString())?.let {
                            val objC = mutableListOf<String?>()
                            for (i in 0 until it.length()) {
                                objC.add(it[i].let {
                                    if (it == JSONObject.NULL) null else it.toString()
                                })
                            }
                            objC
                        }
                        objB
                    }
                })
            }
            objA
        })
} */

internal fun String.toPrimitiveTestObject(): PrimitiveTest {
    val obj = JSONObject(this)
    return PrimitiveTest(
        obj.opt("Nullable Byte")?.toString()?.toByte(),
        obj.opt("Byte").toString().toByte(),
        obj.opt("Nullable Short")?.toString()?.toShort(),
        obj.opt("Short").toString().toShort(),
        obj.opt("Nullable Int")?.toString()?.toInt(),
        obj.opt("Int").toString().toInt(),
        obj.opt("Nullable Long")?.toString()?.toLong(),
        obj.opt("Long").toString().toLong(),
        obj.opt("Nullable Float")?.toString()?.toFloat(),
        obj.opt("Float").toString().toFloat(),
        obj.opt("Nullable Double")?.toString()?.toDouble(),
        obj.opt("Double").toString().toDouble(),
        obj.opt("Nullable Boolean")?.toString()?.toBoolean(),
        obj.opt("Boolean").toString().toBoolean(),
        obj.opt("Nullable Char")?.toString()?.get(0),
        obj.opt("Char").toString().get(0),
        obj.optString("Nullable String", null),
        obj.optString("String", null),

        // list or set ???
        /** Collection */
        obj.optJSONArray("Collection")?.let {
            val objA = mutableListOf<Int?>()
            for (i in 0 until it.length()) {
                objA.add(it[i].let {
                    if (it == JSONObject.NULL) { null } else { it.toString().toInt() }
                })
            }
            objA
        },

        /** Map */
        obj.optJSONObject("Map")?.let {
            val objA = mapOf<String, Int?>(
                "hello1" to it.opt("hello1")?.toString()?.toInt(),
                "hello2" to it.opt("hello2")?.toString()?.toInt(),
                "hello_none" to it.opt("hello_none")?.toString()?.toInt(),
            )
            objA
        }
    )
}