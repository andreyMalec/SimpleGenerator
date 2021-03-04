package com.malec.simplegenerator.generator

interface Generator {
    fun generateNext(count: Int): List<Long>

    fun reset()
}