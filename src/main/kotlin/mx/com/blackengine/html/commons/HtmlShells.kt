package mx.com.blackengine.html.commons

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize

fun createTailwindcssHtmxHtmlShell(title: String, mainCssFilePath: String, block: HtmlBlockTag.() -> Unit): String {
    return createHTMLDocument().html {
        head {
            meta { charset = "utf-8" }
            meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
            title { +title }
            script(src = "https://cdn.tailwindcss.com") {}
            script(src = "https://unpkg.com/htmx.org@1.9.11") {}
            link(href = mainCssFilePath, rel = "stylesheet")
        }
        body {
            block()
        }
    }.serialize(false)
}