package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.Node
import org.fuusio.flo.Symbol

object Decrement: Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Symbol -> {
                val key = input.name
                when (val value = ctx.get(key)) {
                    is Byte -> ctx.set(key, value - 1)
                    is Char -> ctx.set(key, value - 1)
                    is Double -> ctx.set(key, value - 1)
                    is Float -> ctx.set(key, value - 1)
                    is Int -> ctx.set(key, value - 1)
                    is Long -> ctx.set(key, value - 1)
                    else -> this
                }
            }
            else -> this
        }
}