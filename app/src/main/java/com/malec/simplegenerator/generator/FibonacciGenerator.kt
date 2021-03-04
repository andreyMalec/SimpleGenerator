package com.malec.simplegenerator.generator

import java.util.stream.Collectors
import java.util.stream.Stream

class FibonacciGenerator : Generator {
    companion object {
        //можно было бы использовать BigInteger, чтобы увеличить количество чисел, но я не стал
        private var first = 0L
        private var second = 1L
    }

    override fun generateNext(count: Int): List<Long> {
        if (first < 0 || second < 0)
            return emptyList()

        val result = Stream.iterate(longArrayOf(first, second)) { arr ->
            longArrayOf(
                arr[1],
                arr[0] + arr[1]
            )
        }
            .limit(count.toLong())
            .map { y -> y[0] }
            .filter { it >= 0 }
            .collect(Collectors.toList())

        if (result.size != count) {
            first = -1
            second = -1
        } else {
            first = result[result.lastIndex - 1]
            second = result.last()
        }

        return result.dropLast(2)
    }

    override fun reset() {
        first = 0L
        second = 1L
    }
}