package mx.com.blackengine.html.commons

import io.vertx.ext.web.RoutingContext

fun putHtml(ctx: RoutingContext) {
    ctx.response().putHeader("Content-Type", "text/html")
}