package org.fuusio.flo

interface Interceptor {

    fun intercept(ctx: Ctx, node: Any): Any
}