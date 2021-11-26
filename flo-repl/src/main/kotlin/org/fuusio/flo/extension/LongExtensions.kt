package org.fuusio.flo.extension

import org.fuusio.flo.Ctx
import org.fuusio.flo.IntRange
import org.fuusio.flo.operator.ArithmeticOperator
import org.fuusio.flo.operator.ComparisonOperator
import org.fuusio.flo.operator.Range

fun Long.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is ArithmeticOperator -> input.calculate(ctx.pop() as Number, this)
        is ComparisonOperator -> input.compare(ctx.pop() as Number, this)
        is Range -> {
            IntRange(ctx.pop() as Int, toInt())
        }
        else -> this
    }