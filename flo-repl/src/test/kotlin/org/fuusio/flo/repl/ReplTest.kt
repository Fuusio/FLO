package org.fuusio.flo.repl

import org.fuusio.flo.*
import org.fuusio.flo.block.Block
import org.fuusio.flo.type.Array
import org.fuusio.flo.function.Function
import org.fuusio.flo.type.Map
import org.fuusio.flo.operator.Addition
import org.fuusio.flo.operator.Range
import org.fuusio.flo.type.Nil
import org.fuusio.flo.type.Symbol
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@DisplayName("Given Repl")
internal class ReplTest : AbstractTest() {

    @DisplayName("When eval")
    @Nested
    inner class EvalExpressions {

        @BeforeEach
        fun beforeEach() {
            resetCtx()
        }

        @Test
        @DisplayName("With given expression, Then should evaluate to correct result")
        fun withStrings() {
            val startTime = System.currentTimeMillis()
            eval("{ \"Hello World!\"}", "Hello World!")
            eval("'a'", 'a')
            eval("true", true)
            eval("false", false)
            eval("!true", false)
            eval(" ! false", true)
            eval(".", Nil)
            eval("nil", Nil)
            eval("123", 123)
            eval("123L", 123L)
            eval("123.0", 123.0)
            eval("123.0f", 123f)
            eval("1 + 2", 3)
            eval("43 % 2", 1)
            eval("1 + 2 * 3", 9)
            eval("1 - 2 * 3", -3)
            eval("(x + y)", Function(arrayOf(Symbol("x"), Addition, Symbol("y"))))
            eval("['x : 40 'y : 2]", Map(mutableMapOf(Symbol("x") to 40, Symbol("y") to 2)))
            eval("['x : 40 'y : 2](x + y)", 42)
            eval("[1 2 \"foo\" 'b']", Array(arrayOf(1, 2, "foo", 'b')))
            eval("0 = a . 1 = b . { a >= b }", false)
            eval("'{ a }", Block(arrayOf(Symbol("a"))))
            eval("42 !", 42)
            eval("\"42\" !", "42")
            eval("true {\"true\"} ! {\"false\"}", "true")
            eval("4 > 2", true)
            eval("4 > 2 {\"true\"} ! {\"false\"}", "true")
            eval("4 > 2 {10 * 4 + 2} ! {10 * 2}", 42)
            eval("false {\"true\"} ! {\"false\"}", "false")
            eval("42 = foo", 42)
            eval("42 = foo + 58", 100)
            eval("42 = foo + 58 . foo", 42)
            eval("0 = foo . 0 ..41 {++foo} . foo", 42)
            eval("0 = foo . 0..41 {'foo++} . foo", 42)
            eval("'foo = 42 . foo", 42)
            eval("'foo = Nil . foo", Nil)
            eval("0 = foo . 1..4 {++foo} . foo", 4)
            eval("'foo = 0 . 1..4 {++foo} . foo", 4)
            eval("2 > 1 {\"true\"} ! {\"false\"}", "true")
            eval("2 >= 2 {\"true\"} ! {\"false\"}", "true")
            eval("(x + y) = foo . ['x : 2 'y : 40]          foo", 42)
            eval("(x + y) = foo . foo", Function(arrayOf(Symbol("x"), Addition, Symbol("y"))))
            eval("['a' 'b' 'c'] 1", 'b')
            eval("[0 : 'a'  1 : 'b' 2 : 'c'] 2", 'c')
            eval("[0 : 'a'  1 : 'b' 2 : 'c'] 0L", Nil)
            eval("[0L : 'a'  1 : 'b' 2 : 'c'] 0L", 'a')
            eval("0.", Nil)
            eval("0..", Range)
// TODO     eval("foo = 42 . foo", 42)
// TODO     eval("'foo = (x + y) . ['x : 2 'y : 40]foo", 42)
            val endTime = System.currentTimeMillis()
            println("Duration: ${endTime - startTime}")
        }

        private fun eval(expression: String, expectedResult: Any) {
            val result = Repl.eval(expression)
            assertEquals(expectedResult, result, expression)
        }
    }
}