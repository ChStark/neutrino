package mx.com.blackengine.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import mx.com.blackengine.entities.tables.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class Automigrate : CliktCommand() {
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

            SchemaUtils.create(
                AddressComponents,
                Addresses,
                Companies,
                CompanyRoles,
                ConfigLog,
                Configs,
                CustomerGroups,
                EnumAddressComponentTypes,
                EnumAttributeTypes,
                EnumAttributeUnits,
                EnumCodeScriptTypes,
                EnumCompanyTypes,
                EnumCurrencyCodes,
                EnumCustomerTypes,
                EnumDigitalTaxReceiptConceptTypes,
                EnumDigitalTaxReceiptLogTypes,
                EnumDigitalTaxReceiptTypes,
                EnumGoogleAddressComponentTypes,
                EnumLotTypes,
                EnumNotificationTypes,
                EnumOrderLineTypes,
                EnumOrderLogTypes,
                EnumOutboundEmailStatuses,
                EnumOutboundEmailTypes,
                EnumPaymentProcessors,
                EnumPriceLogTypes,
                EnumProductTransactionTypes,
                EnumProductTypes,
                EnumRouteLogTypes,
                EnumTicketEntryTypes,
                EnumTicketTypes,
                Lots,
                NotificationGroups,
                OtpUsages,
                OutboundEmails,
                OutboundSms,
                Products,
                SalesChannels,
                SystemRoles,
                UserCompanyRoles,
                UserCustomerGroup,
                Users,
                UserSystemRoles,
                )
        }
    }
}