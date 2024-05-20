package mx.com.blackengine.routes.backoffice

import com.google.common.net.HttpHeaders
import com.google.common.net.MediaType
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.pebble.PebbleTemplateEngine
import org.jetbrains.exposed.sql.Database

fun homeHandler(db: Database, templateEngine: PebbleTemplateEngine): Handler<RoutingContext> {
    return Handler { ctx ->
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, MediaType.HTML_UTF_8.toString())

        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/home.html.peb") {
            if (it.succeeded()) {
                ctx.response().end(it.result().toString())
            } else {
                ctx.response().end(it.cause().message)
            }
        }
    }
}