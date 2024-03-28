package mx.com.blackengine.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.f4b6a3.uuid.UuidCreator
import com.github.shamil.Xid
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import mx.com.blackengine.entities.dao.EnumClass
import mx.com.blackengine.entities.dao.Lot
import mx.com.blackengine.entities.dao.User
import mx.com.blackengine.entities.tables.Configs
import mx.com.blackengine.entities.tables.Enums
import mx.com.blackengine.entities.tables.Lots
import mx.com.blackengine.entities.tables.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class Test : CliktCommand() {
    override fun run() {
        for (i in 1..100) {
            println(UuidCreator.getTimeOrderedEpoch())
        }
    }
}