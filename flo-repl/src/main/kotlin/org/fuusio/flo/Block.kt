package org.fuusio.flo

data class Block(
    private val nodes: kotlin.Array<Any> = emptyArray()
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any {
        val blockCtx = BlockCtx(ctx.vm, ctx, this)
        val value = when (input) {
            is Interceptor -> input.intercept(ctx, this)
            is Nil -> execute(blockCtx)
            is Number -> executeInLoop(blockCtx, input.toLong())
            is Boolean -> if (input) execute(blockCtx) else input
            is CharRange -> executeInCharRange(blockCtx, input)
            is IntRange -> executeInIntRange(blockCtx, input)
            else -> input
        }
        blockCtx.dispose()
        return value
    }

    private fun executeInLoop(ctx: Ctx, input: Long): Any {
        var value: Any = Nil
        for (i in 0 until input) {
            value = execute(ctx)
        }
        return value
    }

    private fun executeInCharRange(ctx: Ctx, range: CharRange): Any {
        var value: Any = Nil
        for (char in range.start .. range.end) {
            value = execute(ctx) // TODO
        }
        return value
    }

    private fun executeInIntRange(ctx: Ctx, range: IntRange): Any {
        var value: Any = Nil
        for (int in range.start .. range.end) {
            value = execute(ctx) // TODO
        }
        return value
    }

    private fun execute(ctx: Ctx): Any {
        var value: Any = Nil
        nodes.forEach { node ->
            value = Executor.execute(ctx, node, value)
        }
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Block

        if (!nodes.contentEquals(other.nodes)) return false

        return true
    }

    override fun hashCode(): Int {
        return nodes.contentHashCode()
    }
}
