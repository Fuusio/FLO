package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.Node

object Assignment : Node {

    override fun execute(ctx: Ctx, input: Any): Any {
        ctx.push(input)
        return this
    }
}