package org.fuusio.flo.parser

import java.io.BufferedReader

data class Line(private val line: String) {
    var position: Int = 0
    var length: Int = line.length

    fun next(): Char = line[position++]

    fun peek(): Char = line[position]

    fun hasNext(): Boolean = position < length

    fun previous() {
        position--
    }

    fun movePosition(offset: Int) {
        position += offset
    }

    companion object {

        fun empty() = Line("")

        fun read(reader: BufferedReader): Line = Line(reader.readLine()?.trim() ?: "")
    }
}
