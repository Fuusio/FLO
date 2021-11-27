package org.fuusio.flo.function

import org.fuusio.flo.Ctx
import org.fuusio.flo.Interceptor
import org.fuusio.flo.type.Nil
import org.fuusio.flo.Node
import org.fuusio.flo.operator.FunctionEnd
import org.fuusio.flo.type.Map

class FunctionDefinition : Node, Interceptor {

    private val nodes: MutableList<Any> = mutableListOf()

    override fun execute(ctx: Ctx, input: Any): Any = Nil // TODO

    override fun intercept(ctx: Ctx, node: Any): Any =
        when (node) {
            FunctionEnd -> {
                val function = create()
                when (val value = ctx.peek()) {
                    is Map -> {
                        ctx.pop()
                        function.execute(ctx, value)
                    }
                    else -> function
                }
            }
            else -> {
                nodes.add(node)
                this
            }
        }

    private fun create(): Function = Function(nodes.toTypedArray())
}