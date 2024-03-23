package mx.com.blackengine.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest
import kotlinx.html.h1
import mx.com.blackengine.html.commons.Shells


class Marketplace : CliktCommand() {
    private val port by option(envvar = "PORT").int().default(8080)
    override fun run() {
        val vertx = Vertx.vertx()
        val server: HttpServer? = vertx.createHttpServer()

        server!!.requestHandler { request: HttpServerRequest ->

            // This handler gets called for each request that arrives on the server
            val response = request.response()
            response.putHeader("Content-Type", "text/html")

            val htmlDoc = Shells.createHtmlShell("Marketplace") {
                h1 {
                    +"Test"
                }
            }

            // Write to the response and end it
            response.end(htmlDoc)
        }

        server.listen(port)
    }
}