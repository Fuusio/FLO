package org.fuusio.flo

data class Parameter(
    val name: String
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any = ctx.get(name)
}
