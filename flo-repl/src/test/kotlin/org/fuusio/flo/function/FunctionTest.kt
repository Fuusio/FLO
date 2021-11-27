package org.fuusio.flo.function

import org.fuusio.flo.AbstractTest
import org.fuusio.flo.Executor
import org.fuusio.flo.operator.Addition
import org.fuusio.flo.operator.Separator
import org.fuusio.flo.type.Map
import org.fuusio.flo.type.Symbol
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.fail

@DisplayName("Given Function")
internal class FunctionTest : AbstractTest() {

    @DisplayName("When receiving Array containing parameters")
    @Nested
    inner class ExecuteWithParameters {

        @BeforeEach
        fun beforeEach() {
            resetCtx()
        }

        @Test
        @DisplayName("With parameters 10 and 32, Then should return the 42")
        fun withParameters10And32() {
            val expectedResult = 42
            val params = Map(mapOf(Symbol("x") to 10, Symbol("y") to 32))
            val paramX = Parameter("x")
            val paramY = Parameter("y")
            val x = Symbol("x")
            val y = Symbol("y")
            val function = Function(arrayOf(paramX, paramY, x, Addition, y))
            val result = Executor.execute(ctx, function, params)
            Assertions.assertEquals(expectedResult, result)
        }

        @Test
        @DisplayName("With parameters 10 and 32, Then should return the 42")
        fun withParameters10And32ButWithoutDeclaredParameters() {
            val expectedResult = 42
            val params = Map(mapOf(Symbol("x") to 10, Symbol("y") to 32))
            val x = Symbol("x")
            val y = Symbol("y")
            val function = Function(arrayOf(x, Addition, y))
            val result = Executor.execute(ctx, function, params)
            Assertions.assertEquals(expectedResult, result)
        }

        @Test
        @DisplayName("With extra parameter, Then should throw exception")
        fun withExtraParameter() {
            val params = Map(mapOf(Symbol("x") to 10, Symbol("y") to 32))
            val x = Symbol("x")
            val y = Symbol("y")
            val z = Symbol("z")
            val function = Function(arrayOf(x, Addition, y, z))
            try {
                Executor.execute(ctx, function, params)
                fail("Should have thrown exception SymbolValueNotDefinedException")
            } catch (e: IllegalStateException) {
                // Test succeeded
            }
        }

        @Test
        @DisplayName("With no parameters, Then should return the 42")
        fun withNoParameters() {
            val expectedResult = 42
            val params = Map(mapOf())
            val function = Function(arrayOf(Separator, 10, Addition, 32))
            val result = Executor.execute(ctx, function, params)
            Assertions.assertEquals(expectedResult, result)
        }
    }
}