package org.fuusio.flo.extension

import org.fuusio.flo.*
import org.fuusio.flo.type.Array
import org.fuusio.flo.type.Map
import org.fuusio.flo.IntRange
import org.fuusio.flo.operator.ArithmeticOperator
import org.fuusio.flo.operator.Assignment
import org.fuusio.flo.operator.ComparisonOperator
import org.fuusio.flo.operator.Range
import org.fuusio.flo.type.Symbol

fun Int.execute(ctx: Ctx, input: Any): Any =
    when (input) {
        is ArithmeticOperator -> input.calculate(ctx.pop() as Number, this)
        is ComparisonOperator -> input.compare(ctx.pop() as Number, this)
        is Range -> IntRange(ctx.pop() as Int, toInt())
        is Symbol -> {
            ctx.push(this)
            input.execute(ctx, Assignment)
        }
        is Array -> input[this]
        is Map -> input[this]
        else -> this
    }