package org.fuusio.flo.repl

import org.fuusio.flo.Ctx
import org.fuusio.flo.Executor
import org.fuusio.flo.FloVM
import org.fuusio.flo.type.Nil
import org.fuusio.flo.parser.Parser

object Repl {

    fun eval(string: String): Any {
        val ctx = Ctx(FloVM())
        val parser = Parser.create(string)
        var value: Any = Nil
        var node: Any?

        do {
            node = parser.readNext()

            if (node != null) {

                value = Executor.execute(ctx, node, value)
            }
        } while (node != null)

        return value
    }
}