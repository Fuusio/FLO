package org.fuusio.flo

data class Function(
    private val nodes: kotlin.Array<Any> = emptyArray()
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when(input) {
            is Interceptor -> input.intercept(ctx, this)
            is Map -> execute(ctx, input)
            else -> this
        }

    private fun execute(ctx: Ctx, params: Map): Any {
        val functionCtx = FunctionCtx(ctx.vm, ctx, this)

        params.items.keys.forEach { key ->
            functionCtx.set(key.toString(), params.items[key] ?: Nil)
        }

        var value: Any = params
        nodes.forEach { node ->
            value = Executor.execute(functionCtx, node, value)
        }
        functionCtx.dispose()
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Function

        if (!nodes.contentEquals(other.nodes)) return false

        return true
    }

    override fun hashCode(): Int {
        return nodes.contentHashCode()
    }
}
