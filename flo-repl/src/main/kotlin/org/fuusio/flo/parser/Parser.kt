package org.fuusio.flo.parser

import org.fuusio.flo.type.Key
import org.fuusio.flo.type.Nil
import org.fuusio.flo.type.Symbol
import org.fuusio.flo.operator.*
import java.io.BufferedReader
import java.io.StringReader

class Parser(private var reader: BufferedReader) {

    private var line: Line = Line.empty()

    fun readNext(): Any? {
        if (!line.hasNext()) {
            line = Line.read(reader)
        }
        while (line.hasNext()) {
            val char = line.next()

            if (!char.isWhitespace()) {
                return when (char) {
                    '{' -> BlockBegin
                    '}' -> BlockEnd
                    '[' -> CollectionBegin
                    ']' -> CollectionEnd
                    '(' -> FunctionBegin
                    ')' -> FunctionEnd
                    '/' -> Division
                    '*' -> Multiplication
                    '%' -> Modulus
                    '=' -> readAssignmentOrEqual()
                    '\'' -> readCharOrQuote()
                    '>' -> readGreaterThan()
                    '+' -> readAdditionOrIncrement()
                    '<' -> readLesserThan()
                    ':' -> readKeyOrMapper()
                    '.' -> readNilOrRange()
                    '!' -> readNotOrNotEqual()
                    '"' -> readString()
                    '-' -> readSubtractionOrNumberOrDecrement()
                    '_' -> readSymbol()
                    else -> when {
                        char.isDigit() -> {
                            line.previous()
                            readNumber()
                        }
                        char.isJavaIdentifierStart() -> {
                            line.previous()
                            readSymbol()
                        }
                        else -> null
                    }
                }
            }
        }
        return null
    }

    private fun readAssignmentOrEqual(): Any {
        if (line.hasNext() && line.peek() == '=') {
            line.next()
            return Equal
        }
        return Assignment
    }

    private fun readCharOrQuote(): Any {
        if (line.hasNext()) {
            val char = line.next()
            if (line.hasNext() && line.next() == '\'') {
                return char
            } else {
                line.movePosition(-2)
            }
        }
        return Quote
    }

    private fun readGreaterThan(): Any {
        if (line.hasNext() && line.peek() == '=') {
            line.next()
            return GreaterThanOrEqual
        }
        return GreaterThan
    }

    private fun readLesserThan(): Any {
        if (line.hasNext() && line.peek() == '=') {
            line.next()
            return LesserThanOrEqual
        }
        return LesserThan
    }

    private fun readNilOrRange(): Any {
        if (line.hasNext()) {
            val char = line.peek()
            return when {
                char == '.' -> {
                    line.next()
                    Range
                }
                char.isWhitespace() -> Nil
                else -> Nil
            }
        }
        return Nil
    }

    private fun readKey(): Any {
        val name = StringBuilder()
        while (line.hasNext()) {
            val char = line.peek()

            if (char.isJavaIdentifierPart()) {
                name.append(char)
                line.next()
            } else {
                return Key(name.toString())
            }
        }
        return Key(name.toString())
    }

    private fun readKeyOrMapper(): Any {
        if (line.hasNext()) {
            val char = line.peek()
            when {
                char.isJavaIdentifierStart() -> {
                    return readKey()
                }
                char.isWhitespace() -> {
                    line.next()
                }
            }
        }
        return Mapper
    }

    private fun readNotOrNotEqual(): Any {
        if (line.hasNext() && line.peek() == '=') {
            line.next()
            return NotEqual
        }
        return Not
    }

    private fun readAdditionOrIncrement(): Any {
        if (line.hasNext() && line.peek() == '+') {
            line.next()
            return Increment
        }
        return Addition
    }

    private fun readNumber(): Any {
        val digits = StringBuilder()
        var isFloatingPointRead = false

        while (line.hasNext()) {
            val char = line.peek()

            if (char.isDigit()) {
                digits.append(char)
                line.next()
            } else if (char == '.' && !isFloatingPointRead) {
                isFloatingPointRead = true
                line.next()

                if (line.hasNext() && line.peek().isDigit()) {
                    digits.append(char)
                } else {
                    line.previous()
                    return if (isFloatingPointRead) digits.toString().toDouble() else digits.toString().toInt()
                }
            } else if (char == 'L') {
                line.next()
                return digits.toString().toLong()
            } else if (char == 'f') {
                line.next()
                return digits.toString().toFloat()
            } else {
                return if (isFloatingPointRead) digits.toString().toDouble() else digits.toString().toInt()
            }
        }
        return if (isFloatingPointRead) digits.toString().toDouble() else digits.toString().toInt()
    }

    private fun readString(): Any {
        val string = StringBuilder()
        while (line.hasNext()) {
            val char = line.next()

            if (char == '"') {
                return string.toString()
            } else {
                string.append(char)
            }
        }
        return string.toString()
    }

    private fun readSymbol(): Any {
        val name = StringBuilder()
        while (line.hasNext()) {
            val char = line.peek()

            if (char.isJavaIdentifierPart()) {
                name.append(char)
                line.next()
            } else {
                return createSymbolOrKeyword(name.toString())
            }
        }
        return createSymbolOrKeyword(name.toString())
    }

    private fun createSymbolOrKeyword(string: String): Any =
        when (string) {
            "true" -> true
            "false" -> false
            "nil" -> Nil
            else -> Symbol(string)
        }

    private fun readSubtractionOrNumberOrDecrement(): Any {
        if (line.hasNext()) {
            val char = line.peek()
            when {
                char.isDigit() -> return readNumber()
                char == '-' -> {
                    line.next()
                    return Decrement
                }
            }
        }
        return Subtraction
    }

    companion object {
        fun create(string: String): Parser = Parser(BufferedReader(StringReader(string)))
    }
}