package org.fuusio.flo

interface Node {
    fun execute(ctx: Ctx, input: Any): Any
}