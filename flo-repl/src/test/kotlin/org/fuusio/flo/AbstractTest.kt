package org.fuusio.flo

abstract class AbstractTest {

    protected val ctx = Ctx(FloVM())

    protected fun resetCtx() {
       ctx.reset()
    }
}