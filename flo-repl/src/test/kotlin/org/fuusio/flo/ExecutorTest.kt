package org.fuusio.flo

import org.fuusio.flo.operator.Addition
import org.fuusio.flo.type.Nil
import org.junit.jupiter.api.*

@DisplayName("Given Executor")
internal class ExecutorTest : AbstractTest() {

    @DisplayName("When executing Byte")
    @Nested
    inner class ExecuteByte {

        private val byte: Byte = 42

        @BeforeEach
        fun beforeEach() {
            resetCtx()
        }

        @Test
        @DisplayName("With input Nil, Then should return the Byte itself")
        fun withInputNil() {
            val expectedResult = byte
            val result = Executor.execute(ctx, byte, Nil)
            Assertions.assertEquals(expectedResult, result)
        }

        @Test
        @DisplayName("With input Addition and byte 58 in stack, Then should return 100")
        fun withInputAddition() {
            ctx.push(58)
            val expectedResult = 100
            val result = Executor.execute(ctx, byte, Addition)
            Assertions.assertEquals(expectedResult, result)
        }
    }
}