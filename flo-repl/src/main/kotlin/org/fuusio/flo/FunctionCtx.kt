package org.fuusio.flo

import org.fuusio.api.util.Disposable

class FunctionCtx(
    vm: FloVM,
    parent: Ctx? = null,
    private var function: Function?
) : Ctx(vm, parent), Disposable {

    override fun get(key: String): Any =
        when (val value = values[key]) {
            null -> throw IllegalStateException() // TODO : Appropriate exception
            else -> value
        }

    override fun dispose() {
        reset()
        function = null
    }
}
