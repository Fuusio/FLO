package org.fuusio.flo.extension

import org.fuusio.flo.Ctx
import org.fuusio.flo.operator.Range

fun Char.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is Range -> {
            CharRange(ctx.pop() as Char, this)
        }
        else -> this
    }