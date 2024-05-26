package mx.com.blackengine.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import mx.com.blackengine.entities.tables.neutrino.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class AutomigrateNeutrino : CliktCommand() {
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
                Actions,
                AddressComponents,
                Addresses,
                Bins,
                BotLog,
                Bots,
                BotSetups,
                Carriers,
                Companies,
                CompanyRoles,
                ConfigLog,
                Configs,
                CustomerGroups,
                DigitalTaxReceiptConceptPurchaseOrderLines,
                DigitalTaxReceiptConcepts,
                DigitalTaxReceiptPurchaseOrders,
                DigitalTaxReceipts,
                EnumAddressComponentTypes,
                EnumAttributeTypes,
                EnumBotTypes,
                EnumCompanyTypes,
                EnumCurrencyCodes,
                EnumCustomerTypes,
                EnumDigitalTaxReceiptConceptTypes,
                EnumDigitalTaxReceiptConceptUnits,
                EnumDigitalTaxReceiptLogTypes,
                EnumDigitalTaxReceiptTypes,
                EnumGoogleAddressComponentTypes,
                EnumLedgerAccountTypes,
                EnumLotTypes,
                EnumNotificationTypes,
                EnumOrderLineTypes,
                EnumOrderLogTypes,
                EnumOutboundEmailStatuses,
                EnumOutboundEmailTypes,
                EnumPaymentProcessors,
                EnumPercentageNatures,
                EnumPriceLogTypes,
                EnumPriceTypes,
                EnumProductTransactionTypes,
                EnumProductTypes,
                EnumRouteLogTypes,
                EnumScriptLanguageTypes,
                EnumScriptTypes,
                EnumTicketEntryTypes,
                EnumTicketTypes,
                EnumUnits,
                FormulationQualityMetrics,
                FormulationQualityTestMetrics,
                FormulationQualityTests,
                FormulationRawMaterials,
                Formulations,
                LedgerAccounts,
                LedgerJournalEntries,
                Lots,
                NotificationGroups,
                Orders,
                OtpUsages,
                OutboundEmails,
                OutboundSms,
                PriceLog,
                Prices,
                ProductAvailability,
                ProductionBatches,
                ProductKits,
                Products,
                ProductVisibility,
                PurchaseOrderCycles,
                PurchaseOrderLines,
                PurchaseOrders,
                QualityMetricsCatalog,
                RouteEdges,
                RouteLog,
                Routes,
                SalesChannels,
                Scripts,
                StageActions,
                StageEdges,
                Stages,
                Stores,
                Suppliers,
                SystemRoles,
                UserCompanyRoles,
                UserCustomerGroup,
                Users,
                UserSystemRoles,
                WarehouseProductFlows,
                Warehouses,
                Workflows,
                )
        }
    }
}