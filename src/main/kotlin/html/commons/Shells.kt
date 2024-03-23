package mx.com.blackengine.html.commons

import kotlinx.html.*
import kotlinx.html.dom.*

class Shells {
    companion object{
        fun createHtmlShell(title: String, block: HtmlBlockTag.() -> Unit): String {
            return createHTMLDocument().html {
                head {
                    meta { charset = "utf-8" }
                    meta { name="viewport"; content="width=device-width, initial-scale=1" }
                    title { +title }
                    script( src = "https://cdn.tailwindcss.com" ){  }
                }
                body {
                    block()
                }
            }.serialize(false)
        }
    }

}