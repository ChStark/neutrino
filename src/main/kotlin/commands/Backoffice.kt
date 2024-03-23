package mx.com.blackengine.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.shamil.Xid
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager

class Backoffice : CliktCommand() {
    private val _jdbcUrl by option(envvar = "DATABASE_JDBC_URL")
    private val _username by option(envvar = "DATABASE_USERNAME")
    private val _password by option(envvar = "DATABASE_PASSWORD")
    private val _driverClassName by option(envvar = "DATABASE_DRIVER_CLASS_NAME")
    override fun run() {
        val id = Xid.get()
        println("Backoffice $id")

        val hikariConfig = HikariConfig().apply {
            jdbcUrl = _jdbcUrl
            username = _username
            password = _password
            driverClassName = _driverClassName
            maximumPoolSize = 6
            isReadOnly = false
            transactionIsolation = "TRANSACTION_SERIALIZABLE"
        }

        val db = Database.connect(HikariDataSource(hikariConfig))

        transaction {
            addLogger(StdOutSqlLogger)
            val conn = TransactionManager.current().connection
            val statement = conn.prepareStatement("SELECT CURRENT_TIMESTAMP", false)
            val resultSet = statement.executeQuery()

            while (resultSet.next()) {
                val obj = resultSet.getObject(1)
                println(obj)
            }
        }
    }
}