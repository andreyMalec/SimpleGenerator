package com.malec.simplegenerator.generator

import java.util.*

class PrimeGenerator : Generator {
    companion object {
        private var page = 0
    }

    override fun generateNext(count: Int): List<Long> {
        page++
        val prime = BooleanArray(page * count + 1)
        Arrays.fill(prime, true)

        var p = 2
        while (p * p <= page * count) {
            if (prime[p]) {
                var i = p * 2
                while (i <= page * count) {
                    prime[i] = false
                    i += p
                }
            }
            p++
        }

        val result = LongRange(
            (page - 1L) * count,
            page * count.toLong()
        ).filter { prime[it.toInt()] }

        return if (page == 1)
            result.drop(2)
        else
            result
    }

    override fun reset() {
        page = 0
    }
}