package org.fuusio.flo.repl

import org.fuusio.flo.*
import org.fuusio.flo.Array
import org.fuusio.flo.Function
import org.fuusio.flo.Map
import org.fuusio.flo.operator.Addition
import org.fuusio.flo.operator.GreaterThanOrEqual
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
            eval("{ \"Hello World!\"}", "Hello World!")
            eval("!true", false)
            eval(" ! false", true)
            eval("true", true)
            eval("false", false)
            eval(".", Nil)
            eval("nil", Nil)
            eval("123", 123)
            eval("1 + 2", 3)
            eval("43 % 2", 1)
            eval("1 + 2 * 3", 9)
            eval("(x + y)", Function(arrayOf(Symbol("x"), Addition, Symbol("y"))))
            eval("['x : 40 'y : 2]", Map(mutableMapOf(Symbol("x") to 40, Symbol("y") to 2)))
            eval("['x : 40 'y : 2](x + y)", 42)
            eval("[1 2 \"foo\" 'b']", Array(arrayOf(1, 2, "foo", 'b')))
            eval("0 = a . 1 = b . { a >= b }", false)
            eval("'{ a }", Block(arrayOf(Symbol("a"))))
            eval("true {\"true\"} ! {\"false\"}", "true")
            eval("false {\"true\"} ! {\"false\"}", "false")
            eval("42 = foo", 42)
            eval("42 = foo + 58", 100)
            eval("42 = foo + 58 . foo", 42)
            eval("0 = foo . 42 {++foo} . foo", 42)
            eval("0 = foo . 42 {'foo++} . foo", 42)
        }

        private fun eval(expression: String, expectedResult: Any) {
            val result = Repl.eval(expression)
            assertEquals(expectedResult, result)
        }
    }
}