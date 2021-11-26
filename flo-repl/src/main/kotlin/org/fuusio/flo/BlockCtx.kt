package org.fuusio.flo

import org.fuusio.api.util.Disposable

class BlockCtx(
    vm: FloVM,
    parent: Ctx? = null,
    private var block: Block?
) : Ctx(vm, parent), Disposable {

    override fun get(key: String): Any =
        when (val value = values[key]) {
            null -> parent?.get(key) ?: throw IllegalStateException() // TODO : Appropriate exception
            else -> value
        }

    override fun set(key: String, value: Any) {
        if (values.containsKey(key)) {
            super.set(key, value)
        } else {
            parent!!.set(key, value)
        }
    }

    override fun dispose() {
        reset()
        block = null
    }
}
