package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.Node
import org.fuusio.flo.type.Symbol

object Assignment : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Symbol -> input
            else -> {
                ctx.push(input)
                this
            }
        }
}