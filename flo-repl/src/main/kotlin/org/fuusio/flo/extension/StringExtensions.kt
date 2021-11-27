package org.fuusio.flo.extension

import org.fuusio.flo.Ctx
import org.fuusio.flo.type.Nil
import org.fuusio.flo.type.Symbol
import org.fuusio.flo.operator.Assignment

fun String.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is Nil -> this
        is Symbol -> {
            ctx.push(this)
            input.execute(ctx, Assignment)
        }
        else -> input.toString() + this
    }