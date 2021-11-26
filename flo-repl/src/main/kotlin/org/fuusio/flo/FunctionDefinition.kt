package org.fuusio.flo

import org.fuusio.flo.operator.FunctionEnd

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

    fun create(): Function = Function(nodes.toTypedArray())
}