package org.fuusio.flo.extension

import org.fuusio.flo.Ctx
import org.fuusio.flo.operator.Not

fun Boolean.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is Not -> !this
        else -> this
    }