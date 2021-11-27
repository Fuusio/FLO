package org.fuusio.flo

import org.fuusio.api.util.Stack
import org.fuusio.flo.type.Nil

open class Ctx(
    val vm: FloVM,
    protected val parent: Ctx? = null
) {
    protected val values: MutableMap<String, Any> = mutableMapOf()
    private val stack: Stack<Any> = Stack()

    fun peek(): Any? = stack.peek()

    fun pop(): Any = stack.pop() ?: Nil

    fun push(value: Any) {
        stack.push(value)
    }

    open fun get(key: String): Any =
        when (val value = values[key]) {
            null -> {
                when (parent) {
                    null -> Nil // throw IllegalStateException() // TODO : Appropriate exception
                    else -> parent.get(key)
                }
            }
            else -> value
        }

    open fun set(key: String, value:Any): Any {
        values[key] = value
        return value
    }

    fun reset() {
        stack.clear()
        values.clear()
    }
}
