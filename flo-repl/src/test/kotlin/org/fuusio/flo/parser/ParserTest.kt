package org.fuusio.flo.parser

import org.fuusio.flo.AbstractTest
import org.fuusio.flo.Key
import org.fuusio.flo.Nil
import org.fuusio.flo.Symbol
import org.fuusio.flo.operator.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.io.BufferedReader
import java.io.StringReader
import kotlin.reflect.KClass

@DisplayName("Given Parser")
internal class ParserTest : AbstractTest() {

    @DisplayName("When readNext")
    @Nested
    inner class ExecuteWithParameters {

        @BeforeEach
        fun beforeEach() {
            resetCtx()
        }

        @Test
        @DisplayName("With given string, Then should return correct nodes")
        fun withStrings() {
            parseString("42 = foo", Int::class, Assignment::class, Symbol::class)
            parseString("-42 -- -", Int::class, Decrement::class, Subtraction::class)
            parseString("++ +", Increment::class, Addition::class)
            parseString("nil .", Nil::class, Nil::class)
            parseString("123", Int::class)
            parseString("123L", Long::class)
            parseString("123L 123.0f 123.0", Long::class, Float::class, Double::class)
            parseString("'A'", Char::class)
            parseString("\"foo\"", String::class)
            parseString(" foo   :foo _bar _FooBar123 ", Symbol::class, Key::class, Symbol::class, Symbol::class)
            parseString(" foo   true false nil .", Symbol::class, Boolean::class, Boolean::class, Nil::class, Nil::class)
            parseString(" ( foo   :foo ) ", FunctionBegin::class, Symbol::class, Key::class, FunctionEnd::class)
            parseString("+ * / % - -2", Addition::class, Multiplication::class, Division::class, Modulus::class, Subtraction::class, Int::class)
            parseString(" () { } [ ] ", FunctionBegin::class, FunctionEnd::class, BlockBegin::class, BlockEnd::class, CollectionBegin::class, CollectionEnd::class)
            parseString("> >= < <= == != ! =", GreaterThan::class, GreaterThanOrEqual::class, LesserThan::class, LesserThanOrEqual::class, Equal::class, NotEqual::class, Not::class, Assignment::class)
        }

        private fun parseString(line: String, vararg expectedNodeTypes: KClass<*>) {
            val stringReader = StringReader(line)
            val parser = Parser(BufferedReader(stringReader))
            val nodes = mutableListOf<Any>()
            var node: Any?

            do {
                node = parser.readNext()

                if (node != null) {
                    nodes.add(node)
                }
            } while (node != null)

            assertEquals(expectedNodeTypes.size, nodes.size)
            expectedNodeTypes.forEachIndexed { i, type ->  assertTrue(type.isInstance(nodes[i])) }
        }
    }
}