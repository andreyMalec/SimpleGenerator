package com.malec.simplegenerator.model

import com.malec.simplegenerator.generator.FibonacciGenerator
import com.malec.simplegenerator.generator.PrimeGenerator

object NumberTypes {
    val PRIME = NumberType("Prime numbers", 100, PrimeGenerator())
    val FIBONACCI = NumberType("Fibonacci numbers", 10, FibonacciGenerator())
}