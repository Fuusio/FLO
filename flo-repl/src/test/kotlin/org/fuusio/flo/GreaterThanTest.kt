package org.fuusio.flo

import org.fuusio.flo.operator.Addition
import org.junit.jupiter.api.*

@DisplayName("Given GreaterThan")
internal class GreaterThanTest : AbstractTest() {

    @DisplayName("When receiving Number operands")
    @Nested
    inner class ExecuteWithParameters {

        @BeforeEach
        fun beforeEach() {
            resetCtx()
        }

        @Test
        @DisplayName("With parameters 10 and 32, Then should return false")
        fun withParameters10And32() {
            val expectedResult = false
            val array = Array(arrayOf(10, 32))
            val paramX = Parameter("x")
            val paramY = Parameter("y")
            val x = Symbol("x")
            val y = Symbol("y")
            val function = Function(arrayOf(paramX, paramY, x, Addition, y))
            val result = Executor.execute(ctx, function, array)
            Assertions.assertEquals(expectedResult, result)
        }

        @Test
        @DisplayName("With no parameters, Then should return the 42")
        fun withNoParameters() {
            val expectedResult = 42
            val array = Array(arrayOf())
            val function = Function(arrayOf(Separator, 10, Addition, 32))
            val result = Executor.execute(ctx, function, array)
            Assertions.assertEquals(expectedResult, result)
        }
    }
}