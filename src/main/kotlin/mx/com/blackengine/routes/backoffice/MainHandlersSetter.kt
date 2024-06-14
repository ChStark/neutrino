package mx.com.blackengine.routes.backoffice

import com.google.common.net.HttpHeaders
import com.google.common.net.MediaType
import io.vertx.ext.web.Router
import io.vertx.ext.web.templ.pebble.PebbleTemplateEngine
import org.jetbrains.exposed.sql.Database

fun mainHandlersSetter(router: Router, db: Database, templateEngine: PebbleTemplateEngine) {
    router.get("/").handler { ctx ->
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, MediaType.HTML_UTF_8.toString())

        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/shortcuts.html.peb") {
            if (it.succeeded()) {
                ctx.response().end(it.result().toString())
            } else {
                ctx.response().end(it.cause().message)
            }
        }
    }

    router.get("/panel").handler { ctx ->
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, MediaType.HTML_UTF_8.toString())

        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/panel.html.peb") {
            if (it.succeeded()) {
                ctx.response().end(it.result().toString())
            } else {
                ctx.response().end(it.cause().message)
            }
        }
    }
}