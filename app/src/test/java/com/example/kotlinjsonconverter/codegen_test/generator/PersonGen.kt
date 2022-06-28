package com.example.kotlinjsonconverter.codegen_test.generator

import com.example.kotlinjsonconverter.codegen_sample.Person

fun generate_Person() = Person(
    "GilDong",
    "Hong",
    12,
    generate_Simple_Hobbies(),
    listOf(generate_Hobby_Hiking(),
        generate_Hobby_Painting(),
        generate_Hobby_Writing(),
        generate_Hobby_Cooking()),
    generate_Simple_Pets(),
    mapOf("Cat" to generate_Pet_Cat(),
        "Alligator" to generate_Pet_Alligator(),
        "Cattle" to generate_Pet_Cattle(),
        "None" to generate_Pet_None(),
        "Null" to null),
    listOf(1, 2, 3, null, 5, 6, 7)
)