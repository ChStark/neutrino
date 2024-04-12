package mx.com.blackengine.routes.backoffice

import com.google.common.net.HttpHeaders
import com.google.common.net.MediaType
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.jetbrains.exposed.sql.Database

fun homeHandler(db: Database): Handler<RoutingContext> {
    return Handler { ctx ->
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, MediaType.HTML_UTF_8.toString())

        ctx.response().end(createHTMLDocument().html {
            head {
                meta { charset = "utf-8" }
                meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
                title { +"Neutrino - Backoffice" }
                script(src = "https://cdn.tailwindcss.com") {}
                script(src = "https://unpkg.com/htmx.org@1.9.11") {}
                link(href = "/assets/app.css", rel = "stylesheet")
            }
            body {
                classes = setOf("grid")
                style = "background-color:var(--color-0); grid-template-columns:1fr; grid-template-rows: auto 1fr auto;"
                header {
                    +"header"
                }
                main {
                    customMain()
                }
                footer {
                    +"footer"
                }
            }
        }.serialize(false))
    }
}

private fun MAIN.customMain() {
    apply {
        div {
            +"d1"
            form {
                input {
                    onKeyUp = "alert('hola mundo')"
                }
            }
        }
        div { +"d2" }
        div { +"d3" }
    }
}

//
//
//fun customMain(context: MAIN): MAIN {
//    return context.apply{
//        div {
//            +"d1"
//            form {
//                input {
//                    onKeyUp = "alert('hola mundo')"
//                }
//            }
//        }
//        div { +"d2" }
//        div { +"d3" }
//    }
//}
