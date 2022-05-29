package com.example.kotlinjsonconverter.generator

import com.example.kotlinjsonconverter.sample.Hobby

fun generate_Simple_Hobbies(): Collection<String> = listOf("Hiking", "Painting", "Writing", "Cooking")

fun generate_Hobby_Hiking() = Hobby(
    "Hiking",
    4
)

fun generate_Hobby_Painting() = Hobby(
    "Painting",
    2
)

fun generate_Hobby_Writing() = Hobby(
    "Writing",
    1
)

fun generate_Hobby_Cooking() = Hobby(
    "Cooking",
    3
)