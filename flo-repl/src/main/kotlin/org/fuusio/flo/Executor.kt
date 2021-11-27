package org.fuusio.flo

import org.fuusio.flo.extension.execute
import org.fuusio.flo.type.Nil

object Executor {

    fun execute(ctx: Ctx, node: Any, input: Any = Nil): Any =
        if (input is Interceptor) {
            input.intercept(ctx, node)
        } else when (node) {
            is Node -> node.execute(ctx, input)
            is Boolean -> node.execute(ctx, input)
            is Byte -> node.execute(ctx, input)
            is Char -> node.execute(ctx, input)
            is Double -> node.execute(ctx, input)
            is Float -> node.execute(ctx, input)
            is Int -> node.execute(ctx, input)
            is Long -> node.execute(ctx, input)
            is String -> node.execute(ctx, input)
            else -> throw IllegalStateException()
        }
}