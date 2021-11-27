package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.type.Nil
import org.fuusio.flo.Node

object Not : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Boolean -> !input
            is Nil -> this
            else -> input
        }
}