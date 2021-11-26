package org.fuusio.flo.extension

import org.fuusio.flo.Ctx
import org.fuusio.flo.Nil

fun String.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is Nil -> this
        else -> input.toString() + this
    }