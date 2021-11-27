package org.fuusio.flo.extension

import org.fuusio.flo.Ctx
import org.fuusio.flo.type.Symbol
import org.fuusio.flo.operator.ArithmeticOperator
import org.fuusio.flo.operator.Assignment
import org.fuusio.flo.operator.ComparisonOperator

fun Double.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is ArithmeticOperator -> input.calculate(ctx.pop() as Number, this)
        is ComparisonOperator -> input.compare(ctx.pop() as Number, this)
        is Symbol -> {
            ctx.push(this)
            input.execute(ctx, Assignment)
        }
        else -> this
    }