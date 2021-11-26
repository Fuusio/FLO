package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.FunctionDefinition
import org.fuusio.flo.Map

object FunctionBegin : UnaryOperator() {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Map -> {
                ctx.push(input)
                FunctionDefinition()
            }
            else -> FunctionDefinition()
        }
}