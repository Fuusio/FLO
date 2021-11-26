package org.fuusio.flo

data class Map(
    val items: kotlin.collections.Map<Any, Any> = emptyMap()
) : Node {

    operator fun get(key: Any): Any = items[key] ?: Nil

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Interceptor -> input.intercept(ctx, eval(ctx))
            is Function -> input.execute(ctx, eval(ctx))
            else -> eval(ctx)
        }

    private fun eval(ctx: Ctx): Map {
        val evaluatedItems: MutableMap<Any, Any> = mutableMapOf()
        items.entries.forEach { entry ->
            val key = Executor.execute(ctx, entry.key)
            val value = Executor.execute(ctx, entry.value)
            evaluatedItems[key] = value
        }
        return Map(evaluatedItems)
    }
}
