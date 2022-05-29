package com.example.kotlinjsonconverter.sample.stub

import com.example.kotlinjsonconverter.sample.Pet
import com.example.kotlinjsonconverter.sample.toJSONObject
import org.json.JSONArray
import org.json.JSONObject

internal fun Pet.toJSONObject(): JSONObject {
    val obj = JSONObject()
    obj.put("type", type)
    obj.put("name", name)
    obj.put("mine", mine)
    obj.put("weight", weight)
    obj.put("gender", gender)

    //==> put code here


    return obj
}