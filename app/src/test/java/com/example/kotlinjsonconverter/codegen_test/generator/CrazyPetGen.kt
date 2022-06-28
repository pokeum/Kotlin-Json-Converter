package com.example.kotlinjsonconverter.codegen_test.generator

import com.example.kotlinjsonconverter.codegen_sample.CrazyPet

fun generate_CrazyPet_Cat() = CrazyPet(
    "Cat",
    "Meow",
    true,
    12.5,
    'F',
    mapOf(
        "raw1" to listOf(
            mapOf("inner1" to listOf(generate_CatFood_A(), generate_CatFood_B())),
            mapOf("inner2" to listOf(generate_CatFood_C(), generate_CatFood_A()))
        ),
        "raw2" to listOf(
            mapOf("inner1" to listOf(generate_CatFood_B(), generate_CatFood_C())),
            mapOf("inner2" to listOf(generate_CatFood_A(), null))
        )
    )
)