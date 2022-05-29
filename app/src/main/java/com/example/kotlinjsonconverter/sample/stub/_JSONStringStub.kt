package com.example.kotlinjsonconverter.sample.stub

import com.example.kotlinjsonconverter.sample.FoodBrand
import com.example.kotlinjsonconverter.sample.PetFood
import com.example.kotlinjsonconverter.sample.PrimitiveTest
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

        // list or set ?
        obj.optJSONArray("Collection")?.let {
            val objA = mutableListOf<Int?>()
            for (i in 0 until it.length()) {
                objA.add(it[i].let { elem ->
                    if (elem == JSONObject.NULL) null else elem.toString().toInt()
                })
            }
            objA
        }
    )
}