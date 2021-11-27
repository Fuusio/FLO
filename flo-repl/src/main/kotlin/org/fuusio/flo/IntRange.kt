package org.fuusio.flo

import org.fuusio.flo.type.Nil

data class IntRange(
   val start: Int,
   val end: Int = start
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Nil -> Nil
            else -> input
        }
}
