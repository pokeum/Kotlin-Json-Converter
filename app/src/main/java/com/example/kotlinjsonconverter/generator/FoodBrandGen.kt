package com.example.kotlinjsonconverter.generator

import com.example.kotlinjsonconverter.sample.FoodBrand

//FoodBrand("9 Lives", "J.M. Smucker", 1959)
fun generate_FoodBrand_9Lives() = FoodBrand("9 Lives", null, null)

fun generate_FoodBrand_Iams() = FoodBrand("Iams", "Mars, Incorporated", 1946)

fun generate_FoodBrand_BBW() =
    FoodBrand("Blue Buffalo Wilderness", "Nexus Capital Management LP", 1989)