package mx.com.blackengine.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.LoggerHandler
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.templ.pebble.PebbleTemplateEngine
import mx.com.blackengine.routes.backoffice.formHandlersSetter
import mx.com.blackengine.routes.backoffice.mainHandlersSetter
import mx.com.blackengine.routes.backoffice.pageHandlersSetter
import org.jetbrains.exposed.sql.Database

class Backoffice : CliktCommand() {
    private val jdbcUrl by option(envvar = "DATABASE_JDBC_URL")
    private val username by option(envvar = "DATABASE_USERNAME")
    private val password by option(envvar = "DATABASE_PASSWORD")
    private val driverClassName by option(envvar = "DATABASE_DRIVER_CLASS_NAME")
    private val maximumPoolSize by option(envvar = "POOL_SIZE").int().default(6)

    private val port by option(envvar = "PORT").int().default(8080)

    override fun run() {
        val hikariConfig = HikariConfig().apply {
            this.jdbcUrl = this@Backoffice.jdbcUrl
            username = this@Backoffice.username
            password = this@Backoffice.password
            driverClassName = this@Backoffice.driverClassName
            maximumPoolSize = this@Backoffice.maximumPoolSize
            isReadOnly = false
            transactionIsolation = "TRANSACTION_SERIALIZABLE"
        }

        val db = Database.connect(HikariDataSource(hikariConfig))

        val vertx = Vertx.vertx()
        val router = Router.router(vertx)
        val templateEngine = PebbleTemplateEngine.create(vertx)

        router.route("/").handler(LoggerHandler.create())
        router.route("/assets/*").handler(StaticHandler.create("assets/backoffice"))



        mainHandlersSetter(router, db, templateEngine)
        pageHandlersSetter(router, db, templateEngine)
        formHandlersSetter(router, db, templateEngine)

        val server: HttpServer? = vertx.createHttpServer()

        server!!.requestHandler(router)
        server.listen(port)

    }
}