package mx.com.blackengine.html.backoffice

import kotlinx.html.DIV
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.style

fun DIV.backofficeHeader() {
    apply {
        classes = setOf("flex", "flex-row", "items-center", "justify-center", "text-white", "py-1", "shadow")
        style = "background-color: var(--color-8);"
        div {

        }
        div {
            +"Jorge Christopher Garza Sepúlveda @ Ramos Arizpe"
        }
        div {

        }
    }
}