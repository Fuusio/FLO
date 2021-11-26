package org.fuusio.flo.operator

object Addition : ArithmeticOperator() {

    override fun calculate(firstOperand: Number, secondOperand: Number): Number =
        when (firstOperand) {
            is Byte -> when (secondOperand) {
                is Byte -> firstOperand + secondOperand
                is Double -> firstOperand + secondOperand
                is Float -> firstOperand + secondOperand
                is Int -> firstOperand + secondOperand
                is Long -> firstOperand + secondOperand
                else -> throw IllegalStateException()
            }
            is Double -> when (secondOperand) {
                is Byte -> firstOperand + secondOperand
                is Double -> firstOperand + secondOperand
                is Float -> firstOperand + secondOperand
                is Int -> firstOperand + secondOperand
                is Long -> firstOperand + secondOperand
                is Short -> firstOperand + secondOperand
                else -> throw IllegalStateException()
            }
            is Float -> when (secondOperand) {
                is Byte -> firstOperand + secondOperand
                is Double -> firstOperand + secondOperand
                is Float -> firstOperand + secondOperand
                is Int -> firstOperand + secondOperand
                is Long -> firstOperand + secondOperand
                else -> throw IllegalStateException()
            }
            is Int -> when (secondOperand) {
                is Byte -> firstOperand + secondOperand
                is Double -> firstOperand + secondOperand
                is Float -> firstOperand + secondOperand
                is Int -> firstOperand + secondOperand
                is Long -> firstOperand + secondOperand
                else -> throw IllegalStateException()
            }
            is Long -> when (secondOperand) {
                is Byte -> firstOperand + secondOperand
                is Double -> firstOperand + secondOperand
                is Float -> firstOperand + secondOperand
                is Int -> firstOperand + secondOperand
                is Long -> firstOperand + secondOperand
                else -> throw IllegalStateException()
            }
            else -> throw IllegalStateException()
        }
}