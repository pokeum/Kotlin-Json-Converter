package com.example.kotlinjsonconverter.codegen_test.generator

import com.example.kotlinjsonconverter.codegen_sample.PetFood

fun generate_CatFood_A() = PetFood(
    generate_FoodBrand_9Lives(),
    "Daily Essentials with Chicken, Beef & Salmon Flavor Dry Cat Food",
    16.48f
)

fun generate_CatFood_B() = PetFood(
    generate_FoodBrand_Iams(),
    "ProActive Health Indoor Weight & Hairball Care Dry Cat Food",
    24.98f
)

fun generate_CatFood_C() = PetFood(
    generate_FoodBrand_BBW(),
    "Chicken Recipe Grain-Free Dry Cat Food",
    39.98f
)

