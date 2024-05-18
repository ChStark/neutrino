package mx.com.blackengine.routes.backoffice

import com.google.common.net.HttpHeaders
import com.google.common.net.MediaType
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.rocker.RockerTemplateEngine
import org.jetbrains.exposed.sql.Database

fun homeHandler(db: Database, templateEngine: RockerTemplateEngine): Handler<RoutingContext> {
    return Handler { ctx ->
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, MediaType.HTML_UTF_8.toString())

        templateEngine.render(mapOf("foo" to "bar"), "/home")

//        ctx.response().end(createHTMLDocument().html {
//            head {
//                meta { charset = "utf-8" }
//                meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
//                title { +"Neutrino - Backoffice" }
//                script(src = "https://cdn.tailwindcss.com") {}
//                script(src = "https://unpkg.com/htmx.org@1.9.11") {}
//                link(href = "/assets/app.css", rel = "stylesheet")
//                link(rel = "preconnect", href = "https://fonts.googleapis.com")
//                link(rel = "preconnect", href = "https://fonts.gstatic.com")
//                link(
//                    href = "https://fonts.googleapis.com/css2?family=Atkinson+Hyperlegible:ital,wght@0,400;0,700;1,400;1,700&display=swap",
//                    rel = "stylesheet"
//                )
//            }
//            body {
//                classes = setOf("grid","atkinson-hyperlegible-regular")
//                style = "background-color:var(--color-0); grid-template-columns:1fr; grid-template-rows: auto 1fr auto;"
//                div {
//                    backofficeHeader()
//                }
//                div {
//                    +"main"
//                }
//                div {
//                    +"footer"
//                }
//            }
//        }.serialize(false))
    }
}