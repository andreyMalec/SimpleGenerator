package com.malec.simplegenerator

import androidx.paging.PositionalDataSource
import com.malec.simplegenerator.generator.Generator
import com.malec.simplegenerator.model.Number

class NumberDataSource(
    private val generator: Generator,
    private val size: Int
) : PositionalDataSource<Number>() {
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Number>) {
        generator.reset()
        Number.resetColor()
        callback.onResult(generator.generateNext(size).map { Number(it) }, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Number>) {
        callback.onResult(generator.generateNext(size).map { Number(it) })
    }
}