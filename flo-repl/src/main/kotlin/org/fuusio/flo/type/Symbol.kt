package org.fuusio.flo.type

import org.fuusio.flo.Ctx
import org.fuusio.flo.Executor
import org.fuusio.flo.Interceptor
import org.fuusio.flo.Node
import org.fuusio.flo.operator.*

data class Symbol(
    val name: String
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when(input) {
            is Interceptor -> input.intercept(ctx, this)
            is Nil -> ctx.get(name)
            is Assignment -> ctx.set(name, ctx.pop())
            is Decrement -> {
                when (val value = ctx.get(name)) {
                    is Byte -> ctx.set(name, value - 1)
                    is Char -> ctx.set(name, value - 1)
                    is Double -> ctx.set(name, value - 1)
                    is Float -> ctx.set(name, value - 1)
                    is Int -> ctx.set(name, value - 1)
                    is Long -> ctx.set(name, value - 1)
                    else -> value // TODO
                }
            }
            is Increment -> {
                when (val value = ctx.get(name)) {
                    is Byte -> ctx.set(name, value + 1)
                    is Char -> ctx.set(name, value + 1)
                    is Double -> ctx.set(name, value + 1)
                    is Float -> ctx.set(name, value + 1)
                    is Int -> ctx.set(name, value + 1)
                    is Long -> ctx.set(name, value + 1)
                    else -> value // TODO
                }
            }
            else -> Executor.execute(ctx, ctx.get(name), input)
        }

    /* TODO
     fun intercept(ctx: Ctx, node: Any): Any =
        when (node) {
            is BlockBegin -> {
                ctx.push(this)
            }
            is CollectionBegin -> {
                ctx.push(this)
            }
            is FunctionBegin -> {
                ctx.push(this)
            }
            else -> {
                ctx.set(name, Executor.execute(ctx, node, Nil))
            }
        }
     */

    override fun toString() = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Symbol

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
