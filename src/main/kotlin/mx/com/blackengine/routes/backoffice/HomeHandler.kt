package mx.com.blackengine.routes.backoffice

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.input
import mx.com.blackengine.html.commons.createTailwindcssHtmxHtmlShell
import mx.com.blackengine.html.commons.putHtml

fun homeHandler(): Handler<RoutingContext> {
    return Handler { ctx ->
        putHtml( ctx )
        val htmlDoc = createTailwindcssHtmxHtmlShell("Neutrino - Backoffice", "/assets/app.css") {
            div{
               form{
                   input {
                       placeholder="test"
                   }
               }
            }
        }
        ctx.response().end(htmlDoc)
    }
}