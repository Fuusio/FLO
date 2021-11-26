package org.fuusio.flo.operator

import org.fuusio.flo.Ctx

abstract class ComparisonOperator : BinaryOperator() {

    override fun execute(ctx: Ctx, input: Any): Any =
        when (input) {
            is Number -> {
                ctx.push(input)
                this
            }
            else -> input
        }

    abstract fun compare(firstOperand: Number, secondOperand: Number): Boolean
}