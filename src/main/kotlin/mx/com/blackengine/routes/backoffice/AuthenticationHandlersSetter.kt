package mx.com.blackengine.routes.backoffice

import io.vertx.ext.web.Router
import io.vertx.ext.web.templ.pebble.PebbleTemplateEngine
import mx.com.blackengine.html.backoffice.regularHtmlResult
import org.jetbrains.exposed.sql.Database

fun authenticationHandlersSetter(router: Router, db: Database, templateEngine: PebbleTemplateEngine) {
    router.get("/login").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/login.html.peb", regularHtmlResult(ctx))
    }
}