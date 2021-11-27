package org.fuusio.flo.type

import org.fuusio.flo.Ctx
import org.fuusio.flo.Interceptor
import org.fuusio.flo.Node

data class Array(
    val items: kotlin.Array<Any> = emptyArray()
) : Node {

    val size: Int
        get() = items.size

    operator fun get(index: Int): Any = items[index]

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Interceptor -> input.intercept(ctx, this)
            is Number -> items[input.toInt()]
            else -> this
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Array

        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        return items.contentHashCode()
    }
}
