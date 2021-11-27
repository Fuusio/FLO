package org.fuusio.flo.operator

import org.fuusio.flo.block.BlockDefinition
import org.fuusio.flo.Ctx

object BlockBegin : UnaryOperator() {

    override fun execute(ctx: Ctx, input: Any): Any {
        ctx.push(input)
        return BlockDefinition()
    }
}