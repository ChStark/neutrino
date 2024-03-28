package mx.com.blackengine.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.shamil.Xid
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import mx.com.blackengine.entities.dao.EnumClass
import mx.com.blackengine.entities.dao.User
import mx.com.blackengine.entities.tables.Configs
import mx.com.blackengine.entities.tables.Enums
import mx.com.blackengine.entities.tables.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

class DbLocalTest : CliktCommand() {
    private val _jdbcUrl by option(envvar = "DATABASE_JDBC_URL")
    private val _username by option(envvar = "DATABASE_USERNAME")
    private val _password by option(envvar = "DATABASE_PASSWORD")
    private val _driverClassName by option(envvar = "DATABASE_DRIVER_CLASS_NAME")
    override fun run() {
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

        transaction(db) {
            addLogger(StdOutSqlLogger)
            val user = User.findById(Xid("co2de3pudet91tov6deg"))
            println(user)
        }
    }
}