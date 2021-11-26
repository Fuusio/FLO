package org.fuusio.flo.operator

object Equal : ComparisonOperator() {

    override fun compare(firstOperand: Number, secondOperand: Number): Boolean =
        firstOperand == secondOperand
}