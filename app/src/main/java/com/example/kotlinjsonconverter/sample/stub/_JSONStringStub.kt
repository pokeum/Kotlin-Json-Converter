package com.example.kotlinjsonconverter.sample.stub

import com.example.kotlinjsonconverter.sample.FoodBrand
import com.example.kotlinjsonconverter.sample.Pet
import com.example.kotlinjsonconverter.sample.PetFood
import com.example.kotlinjsonconverter.sample.test.PrimitiveTest
import org.json.JSONArray
import org.json.JSONObject

// SERIALIZE_NULL = true 인 경우 (null 값이 "null"로 존재하는 경우 처리)
fun Any.serializeNullToString(): String? = if (this.equals(null)) null else this.toString()

internal fun String.toFoodBrandObject(): FoodBrand {
    val obj = JSONObject(this)
    return FoodBrand(
        obj.optString("company", null),
        obj.optString("owner", null),
        obj.opt("founding year")?.serializeNullToString()?.toInt(),
    )
}

internal fun String.toPetFoodObject(): PetFood {
    val obj = JSONObject(this)
    val nullHandler = NullHandler<FoodBrand>()
    return PetFood(
        nullHandler.handle(obj.optJSONObject("brand")?.serializeNullToString()?.toFoodBrandObject()),
        obj.optString("label", null),
        obj.opt("price").toString().toFloat(),
    )
}

internal fun String.toPetObject(): Pet {
    val obj = JSONObject(this)
    return Pet(
        obj.optString("type", null),
        obj.optString("name", null),
        obj.opt("mine")?.serializeNullToString()?.toBoolean(),
        obj.opt("weight")?.serializeNullToString()?.toDouble(),
        obj.opt("gender")?.serializeNullToString()?.get(0),

        //val foods: Collection<Collection<String?>?>?
        obj.optJSONArray("foods")?.let {
            val objA = mutableListOf<Collection<String?>?>()
            for (i in 0 until it.length()) {
                objA.add(it[i].let {
                    if (it == JSONObject.NULL) {
                        null
                    } else {
                        val objB = JSONArray(it.serializeNullToString())?.let {
                            val objC = mutableListOf<String?>()
                            for (i in 0 until it.length()) {
                                objC.add(it[i].let {
                                    if (it == JSONObject.NULL) { null } else { it?.serializeNullToString() }
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
}

internal fun String.toPrimitiveTestObject(): PrimitiveTest {
    val obj = JSONObject(this)
    return PrimitiveTest(
        obj.opt("Nullable Byte")?.serializeNullToString()?.toByte(),
        obj.opt("Byte").toString().toByte(),
        obj.opt("Nullable Short")?.serializeNullToString()?.toShort(),
        obj.opt("Short").toString().toShort(),
        obj.opt("Nullable Int")?.serializeNullToString()?.toInt(),
        obj.opt("Int").toString().toInt(),
        obj.opt("Nullable Long")?.serializeNullToString()?.toLong(),
        obj.opt("Long").toString().toLong(),
        obj.opt("Nullable Float")?.serializeNullToString()?.toFloat(),
        obj.opt("Float").toString().toFloat(),
        obj.opt("Nullable Double")?.serializeNullToString()?.toDouble(),
        obj.opt("Double").toString().toDouble(),
        obj.opt("Nullable Boolean")?.serializeNullToString()?.toBoolean(),
        obj.opt("Boolean").toString().toBoolean(),
        obj.opt("Nullable Char")?.serializeNullToString()?.get(0),
        obj.opt("Char").toString().get(0),
        obj.optString("Nullable String", null),
        obj.optString("String", null),

        // list or set ???
        /** Collection */
        obj.optJSONArray("Collection")?.let {
            val objA = mutableListOf<Int?>()
            for (i in 0 until it.length()) {
                objA.add(it[i].let {
                    if (it == JSONObject.NULL) { null } else { it?.serializeNullToString()?.toInt() }
                })
            }
            objA
        },

        /** Map */
        obj.optJSONObject("Map")?.let {
            val objA = mapOf<String, Int?>(
                "hello1" to if (it.opt("hello1") == JSONObject.NULL) { null }
                    else { it.opt("hello1")?.serializeNullToString()?.toInt() },
                "hello2" to if (it.opt("hello2") == JSONObject.NULL) { null }
                    else { it.opt("hello2")?.serializeNullToString()?.toInt() },
                "hello_none" to if (it.opt("hello_none") == JSONObject.NULL) { null }
                    else { it.opt("hello_none")?.serializeNullToString()?.toInt() },
            )
            objA
        }
    )
}