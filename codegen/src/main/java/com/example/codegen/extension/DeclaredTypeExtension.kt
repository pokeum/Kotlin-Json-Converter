package com.example.codegen.extension

import javax.lang.model.type.DeclaredType

fun DeclaredType.getSimpleName() = this.toString().split(".").last()
