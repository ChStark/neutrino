package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.jsonb

object Configs : UUID7Table("config") {
    val key = citext("key")
    val value = jsonb<JsonElement>("value", Json.Default).nullable()
}

object Servers : TimestampTable("servers") {
    val type = reference("type", EnumServerTypes) // node, message hub, tracker
    val domain = citext("domain").nullable()
    val ip = citext("ip").nullable()
    val name = text("name")

    val rootUser = reference("root_user", Users)
    val company = reference("company", Companies)

    val neutrinoVersion = text("neutrino_version").nullable()
    val lastKnownStatus = reference("last_known_status", EnumServerStatuses).nullable()

    init {
        uniqueIndex(columns = arrayOf(domain))
        uniqueIndex(columns = arrayOf(ip))
    }
}

object ServerApps : NameableInsertedUpdatedDeletedTable("server_apps") {
    val server = reference("server", Servers)
    val domain = citext("domain").nullable()
    val ip = citext("ip").nullable()

    init {
        uniqueIndex(columns = arrayOf(server, name))
    }
}

object Companies : InsertedUpdatedDeletedTable("companies") {
    val type = reference("type", EnumCompanyTypes)
    val legalName = citext("legal_name")
    val commercialName = citext("commercial_name")
    val taxId = text("tax_id").uniqueIndex("existing_company_tax_id_exception")
    val defaultCurrency = reference("default_currency", EnumCurrencyCodes)
    val parentCompany = reference("parent_company", Companies).nullable()
    val rootUser = reference("root_user", Users)

    val discoverable = bool("discoverable").default(true)
}

object Users : InsertedUpdatedDeletedTable("users") {
    val type = reference("type", EnumCustomerTypes)
    val company = reference("company", Companies)

    val givenNames = citext("given_names")
    val lastNames = citext("last_names")
    val email = citext("email").uniqueIndex("existing_email_exception")
    val phone = text("phone").nullable().uniqueIndex("existing_phone_exception")
        .check("invalid_phone_exception") { it regexp "^[0-9]{10}$" }

    val taxId = text("tax_id").uniqueIndex("existing_customer_tax_id_exception").nullable()

    val totpSecret = text("totp_secret").nullable()
    val totpEnrolled = timestampWithTimeZone("totp_enrolled").nullable()

    val emailConfirmed = timestampWithTimeZone("email_confirmed").nullable()
    val phoneConfirmed = timestampWithTimeZone("phone_confirmed").nullable()

    val defaultAddress = reference("default_address", Addresses).nullable()

    init {
        uniqueIndex(columns = arrayOf(company, email))
        uniqueIndex(columns = arrayOf(company, phone))
        uniqueIndex(columns = arrayOf(company, taxId))
    }
}

object SystemRoles : NameableInsertedUpdatedDeletedTable("system_roles") {
    val entitlements = array<String>("entitlements")
}

object CompanyRoles : NameableInsertedUpdatedDeletedTable("company_roles") {
    val company = reference("company", Companies)
    val entitlements = array<String>("entitlements")
}

object UserSystemRoles : Table("user_system_roles") {
    val user = reference("user", Users)
    val role = reference("role", SystemRoles)
}

object UserCompanyRoles : Table("user_company_roles") {
    val user = reference("user", Users)
    val role = reference("role", CompanyRoles)
}

object SalesChannels : NameableInsertedUpdatedDeletedTable("sales_channels") {
    val company = reference("company", Companies)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object CustomerGroups : NameableInsertedUpdatedDeletedTable("customer_groups") {
    val company = reference("company", Companies)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object UserCustomerGroup : Table("user_customer_groups") {
    val company = CustomerGroups.reference("company", Companies)
    val user = reference("user", Users)
    val group = reference("group", CustomerGroups)
}