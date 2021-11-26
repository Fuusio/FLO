package org.fuusio.flo.operator

object NotEqual : ComparisonOperator() {

    override fun compare(firstOperand: Number, secondOperand: Number): Boolean =
        firstOperand != secondOperand
}