package org.fuusio.flo.operator

import org.fuusio.flo.Ctx
import org.fuusio.flo.type.Nil

object Separator : UnaryOperator() {
    override fun execute(ctx: Ctx, input: Any): Any = Nil
}