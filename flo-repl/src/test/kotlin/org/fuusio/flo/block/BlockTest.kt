package org.fuusio.flo.block

import org.fuusio.flo.AbstractTest
import org.junit.jupiter.api.*

@DisplayName("Given Block")
internal class BlockTest : AbstractTest() {

    @DisplayName("When receiving Boolean True")
    @Nested
    inner class ExecuteWithBooleanInput {

        @BeforeEach
        fun beforeEach() {
            resetCtx()
        }

        @Test
        @DisplayName("With block node 42, Then should return 42")
        fun withParameters10And32() {
            val expectedResult = 42
            val block = Block(arrayOf(42))
            val result = block.execute(ctx, true)
            Assertions.assertEquals(expectedResult, result)
        }
    }
}