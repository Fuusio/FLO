package org.fuusio.flo.operator

import org.fuusio.flo.type.Collection
import org.fuusio.flo.Ctx

object CollectionBegin : UnaryOperator() {

    override fun execute(ctx: Ctx, input: Any): Any = Collection()
}