package mx.com.blackengine.routes.backoffice

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import mx.com.blackengine.html.commons.putHtml
import org.jetbrains.exposed.sql.Database

fun homeHandler(db: Database): Handler<RoutingContext> {
    return Handler { ctx ->
        putHtml(ctx)
        ctx.response().end(createHTMLDocument().html {
            head{
                meta { charset = "utf-8" }
                meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
                title { +"Neutrino - Backoffice" }
                script(src = "https://cdn.tailwindcss.com") {}
                script(src = "https://unpkg.com/htmx.org@1.9.11") {}
                link(href = "/assets/app.css", rel = "stylesheet")
            }
            body {
                classes = setOf("grid","bg-red-500")
                style = "grid-template-cols:1fr;grid-template-rows: auto 1fr auto;"
                header {
                    +"header"
                }
                main {
                    +"main"
                }
                footer {
                    +"footer"
                }
            }
        }.serialize(false))
    }
}
