package org.fuusio.flo.extension

import org.fuusio.flo.Ctx
import org.fuusio.flo.type.Symbol
import org.fuusio.flo.operator.Assignment
import org.fuusio.flo.operator.Not

fun Boolean.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is Not -> !this
        is Symbol -> {
            ctx.push(this)
            input.execute(ctx, Assignment)
        }
        else -> this
    }