package org.fuusio.flo.type

import org.fuusio.flo.Ctx
import org.fuusio.flo.Node

object Nil : Node {

    override fun execute(ctx: Ctx, input: Any) = Nil
}