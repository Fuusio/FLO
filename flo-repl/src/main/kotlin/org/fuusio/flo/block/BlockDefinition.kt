package org.fuusio.flo.block

import org.fuusio.flo.Ctx
import org.fuusio.flo.Interceptor
import org.fuusio.flo.Node
import org.fuusio.flo.operator.BlockEnd
import org.fuusio.flo.operator.Quote
import org.fuusio.flo.operator.Range
import org.fuusio.flo.type.Nil

class BlockDefinition : Node, Interceptor {

    private val nodes: MutableList<Any> = mutableListOf()

    override fun execute(ctx: Ctx, input: Any): Any = Nil // TODO

    override fun intercept(ctx: Ctx, node: Any): Any =
        when (node) {
            is BlockEnd -> {
                val block = create()
                when (val value = ctx.peek()) {
                    is Boolean -> {
                        ctx.pop()
                        block.execute(ctx, value)
                    }
                    is Number -> {
                        ctx.pop()
                        block.execute(ctx, value)
                    }
                    is Range -> {
                        ctx.pop()
                        block.execute(ctx, value)
                    }
                    is Quote -> block
                    is Nil -> block.execute(ctx, Nil)
                    null -> block.execute(ctx, Nil) // TODO
                    else -> value
                }
            }
            else -> {
                nodes.add(node)
                this
            }
        }

    private fun create(): Block = Block(nodes.toTypedArray())
}