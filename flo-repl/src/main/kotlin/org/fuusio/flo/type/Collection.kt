package org.fuusio.flo.type

import org.fuusio.flo.*
import org.fuusio.flo.operator.CollectionEnd
import org.fuusio.flo.operator.Mapper
import org.fuusio.flo.operator.Quote

class Collection : Node, Interceptor {

    private val items: MutableList<Any> = mutableListOf()
    private var shouldBeMap: Boolean = false
    private var isQuoted: Boolean = false

    override fun execute(ctx: Ctx, input: Any): Any = Nil

    override fun intercept(ctx: Ctx, node: Any): Any =
        when (node) {
            is CollectionEnd -> createCollection(ctx)
            is Quote -> {
                isQuoted = true
                this
            }
            is Mapper -> {
                shouldBeMap = true
                items.add(node)
                this
            }
            else -> {
                if (isQuoted) {
                    items.add(node)
                    isQuoted = false
                } else {
                    items.add(Executor.execute(ctx, node, Nil))
                }
                this
            }
        }

    private fun createCollection(ctx: Ctx): Any =
        when {
            shouldBeMap -> createMap(ctx)
            else -> createArray(ctx)
        }

    private fun createArray(ctx: Ctx): Array = Array(items.toTypedArray())

    private fun createMap(ctx: Ctx): Map {
        val itemsCount = items.size

        if (itemsCount % 3 != 0) {
            throw IllegalStateException() // TODO
        }
        val map: MutableMap<Any, Any> = mutableMapOf()
        var index = 0
        do {
            val key = items[index++]
            if (items[index++] !is Mapper) {
                throw IllegalStateException() // TODO
            }
            val value = items[index++]
            map[key] = value
        } while (index < itemsCount)
        return Map(map)
    }
}