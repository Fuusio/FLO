package org.fuusio.flo

import org.fuusio.flo.type.Nil

data class CharRange(
   val start: Char,
   var end: Char = start
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Nil -> Nil
            else -> input
        }
}
