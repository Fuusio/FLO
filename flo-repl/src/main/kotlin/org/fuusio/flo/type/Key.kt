package org.fuusio.flo.type

import org.fuusio.flo.Ctx
import org.fuusio.flo.Node

data class Key(
    val name: String
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when(input) {
            is Map -> input[this]
            else -> this
        }

    fun isKey(key: String): Boolean = key == name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Key

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString() = name
}
