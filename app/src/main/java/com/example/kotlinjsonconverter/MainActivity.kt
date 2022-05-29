package com.example.kotlinjsonconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinjsonconverter.sample.Person
import com.example.kotlinjsonconverter.sample.toJSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}