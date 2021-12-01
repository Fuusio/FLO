package org.fuusio.flo.operator

import org.fuusio.flo.AbstractTest
import org.fuusio.flo.Executor
import org.fuusio.flo.function.Function
import org.fuusio.flo.function.Parameter
import org.fuusio.flo.type.Array
import org.fuusio.flo.type.Symbol
import org.junit.jupiter.api.*

@DisplayName("Given GreaterThan")
internal class GreaterThanOrEqualTest : AbstractTest() {

    @DisplayName("When receiving Number operands")
    @Nested
    inner class ExecuteWithParameters {

        @BeforeEach
        fun beforeEach() {
            resetCtx()
        }

        @Test
        @DisplayName("With parameters 10 and 10, Then should return false")
        fun withParameters10And32() {
            val expectedResult = false
            ctx.push(10)
            val result = Executor.execute(ctx, 32, GreaterThanOrEqual)
            Assertions.assertEquals(expectedResult, result)
        }

        @Test
        @DisplayName("With parameters 32 and 10, Then should return true")
        fun withParameters32And10() {
            val expectedResult = true
            ctx.push(32)
            val result = Executor.execute(ctx, 10, GreaterThan)
            Assertions.assertEquals(expectedResult, result)
        }
    }
}