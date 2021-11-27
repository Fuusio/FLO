package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.Interceptor
import org.fuusio.flo.Node

object Quote : Node, Interceptor {

    override fun execute(ctx: Ctx, input: Any): Any = Quote

    override fun intercept(ctx: Ctx, node: Any): Any =
        when (node) {
            is BlockBegin -> node.execute(ctx, this)
            else -> node
        }
}