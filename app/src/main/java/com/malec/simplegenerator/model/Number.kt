package com.malec.simplegenerator.model

data class Number(
    val data: Long,
    val isColored: Boolean = color
) {
    init {
        if (i == 0 || i == 2)
            color = !color
        i++
        if (i >= 2)
            i = 0
    }

    companion object {
        private var i = 0
        private var color = false

        fun resetColor() {
            i = 0
            color = false
        }
    }
}