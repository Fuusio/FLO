package org.fuusio.flo.function

import org.fuusio.flo.Ctx
import org.fuusio.flo.Node

data class Parameter(
    val name: String
) : Node {
    override fun execute(ctx: Ctx, input: Any): Any = ctx.get(name)
}
