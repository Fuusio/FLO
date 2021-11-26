package org.fuusio.flo

data class ArrayIterator(
    private val array: Array,
    private var index: Int = 0
) : Node {

    override fun execute(ctx: Ctx, input: Any): Any {
        TODO("Not yet implemented")
    }

    fun isConsumed(): Boolean = index == array.size

    fun next(): Any = array[index++]
}