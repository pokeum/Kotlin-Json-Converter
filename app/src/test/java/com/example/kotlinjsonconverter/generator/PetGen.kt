package com.example.kotlinjsonconverter.generator

import com.example.kotlinjsonconverter.sample.Pet

fun generate_Simple_Pets(): Map<String, String?> = mapOf(
    "Cat" to "Meow",
    "Alligator" to "Bellow",
    "Cattle" to "Moo",
    "None" to null
)

fun generate_Pet_Cat() = Pet(
    "Cat",
    "Meow",
    true,
    12.5,
    'F',
    listOf(
        null,
        listOf("test 1", "test 2"),
        listOf("test 3", null)
    )
)

fun generate_Pet_Alligator() = Pet(
    "Alligator",
    "Bellow",
    false,
    100.25,
    'M',
    null
)

fun generate_Pet_Cattle() = Pet(
    "Cattle",
    "Moo",
    false,
    123.456,
    'F',
    null
)

fun generate_Pet_None() = Pet(
    "None_Type",
    "None_Name",
    null,
    null,
    null,
    null
)