package mx.com.blackengine.html.backoffice

import com.google.common.net.HttpHeaders
import com.google.common.net.MediaType
import io.vertx.core.AsyncResult
import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.RoutingContext

fun regularHtmlResult(ctx: RoutingContext): (AsyncResult<Buffer>) -> Unit =
    {
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, MediaType.HTML_UTF_8.toString())

        if (it.succeeded()) {
            ctx.response().end(it.result().toString())
        } else {
            ctx.response().end(it.cause().message)
        }
    }