package com.example.kotlinjsonconverter.codegen_sample.stub

import com.example.kotlinjsonconverter.codegen_sample.FoodBrand
import com.example.kotlinjsonconverter.codegen_sample.Pet
import com.example.kotlinjsonconverter.codegen_sample.PetFood
import com.example.kotlinjsonconverter.codegen_sample.test.PrimitiveTest
import org.json.JSONArray
import org.json.JSONObject

// SERIALIZE_NULL = true 인 경우
fun Any.toStringNullCheck(): String? = if (this.equals(null)) null else this.toString()

internal fun String.toFoodBrandObject(): FoodBrand {
    val obj = JSONObject(this)

    return FoodBrand(
        obj.optString("company", null),
        obj.optString("owner", null),
        obj.opt("founding year")?.toStringNullCheck()?.toInt(),
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

internal fun String.toPetObject(): Pet {
    val obj = JSONObject(this)
    return Pet(
        obj.optString("type", null),
        obj.optString("name", null),
        obj.opt("mine")?.toStringNullCheck()?.toBoolean(),
        obj.opt("weight")?.toStringNullCheck()?.toDouble(),
        obj.opt("gender")?.toStringNullCheck()?.get(0),

        //val foods: Collection<Collection<String?>?>?
        obj.optJSONArray("foods")?.let {
            val objA = mutableListOf<Collection<String?>?>()
            for (i in 0 until it.length()) {
                objA.add(it[i].let {
                    if (it == JSONObject.NULL) {
                        null
                    } else {
                        val objB = JSONArray(it.toStringNullCheck())?.let {
                            val objC = mutableListOf<String?>()
                            for (i in 0 until it.length()) {
                                objC.add(it[i].let {
                                    if (it == JSONObject.NULL) { null } else { it?.toStringNullCheck() }
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
        obj.opt("Nullable Byte")?.toStringNullCheck()?.toByte(),
        obj.opt("Byte").toString().toByte(),
        obj.opt("Nullable Short")?.toStringNullCheck()?.toShort(),
        obj.opt("Short").toString().toShort(),
        obj.opt("Nullable Int")?.toStringNullCheck()?.toInt(),
        obj.opt("Int").toString().toInt(),
        obj.opt("Nullable Long")?.toStringNullCheck()?.toLong(),
        obj.opt("Long").toString().toLong(),
        obj.opt("Nullable Float")?.toStringNullCheck()?.toFloat(),
        obj.opt("Float").toString().toFloat(),
        obj.opt("Nullable Double")?.toStringNullCheck()?.toDouble(),
        obj.opt("Double").toString().toDouble(),
        obj.opt("Nullable Boolean")?.toStringNullCheck()?.toBoolean(),
        obj.opt("Boolean").toString().toBoolean(),
        obj.opt("Nullable Char")?.toStringNullCheck()?.get(0),
        obj.opt("Char").toString().get(0),
        obj.optString("Nullable String", null),
        obj.optString("String", null),

        // list or set ???
        /** Collection */
        obj.optJSONArray("Collection")?.let {
            val objA = mutableListOf<Int?>()
            for (i in 0 until it.length()) {
                objA.add(it[i].let {
                    if (it == JSONObject.NULL) { null } else { it?.toString()?.toInt() }
                })
            }
            objA
        },

        /** Map */
        obj.optJSONObject("Map")?.let {
            val objA = mapOf<String, Int?>(
                "hello1" to if (it.opt("hello1") == JSONObject.NULL) { null }
                    else { it.opt("hello1")?.toString()?.toInt() },
                "hello2" to if (it.opt("hello2") == JSONObject.NULL) { null }
                    else { it.opt("hello2")?.toString()?.toInt() },
                "hello_none" to if (it.opt("hello_none") == JSONObject.NULL) { null }
                    else { it.opt("hello_none")?.toString()?.toInt() },
            )
            objA
        }
    )
}