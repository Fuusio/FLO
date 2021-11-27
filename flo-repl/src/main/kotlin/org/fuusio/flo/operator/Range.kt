package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.Node

object Range : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Number -> {
                ctx.push(input.toInt())
                this
            }
            else -> input
        }
}