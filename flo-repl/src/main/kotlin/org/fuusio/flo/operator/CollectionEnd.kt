package org.fuusio.flo.operator

import org.fuusio.flo.Ctx

object CollectionEnd : UnaryOperator() {
    override fun execute(ctx: Ctx, input: Any): Any = this
}