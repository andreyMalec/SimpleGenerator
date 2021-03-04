package com.malec.simplegenerator.model

import com.malec.simplegenerator.generator.Generator

data class NumberType(
    val name: String,
    val pageSize: Int,
    val generator: Generator
)

