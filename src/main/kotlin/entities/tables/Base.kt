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

object Companies : InsertedUpdatedDeletedTable("companies") {
    val type = reference("type", EnumCompanyTypes)
    val legalName = citext("legal_name")
    val commercialName = citext("commercial_name")
    val taxId = text("tax_id").uniqueIndex("existing_company_tax_id_exception")
    val defaultCurrency = reference("default_currency", EnumCurrencyCodes)
    val parentCompany = reference("parent_company", Companies)
}

object Users : InsertedUpdatedDeletedTable("users") {
    val type = reference("type", EnumCustomerTypes)
    val givenNames = citext("given_names")
    val lastNames = citext("last_names")
    val email = citext("email").uniqueIndex("existing_email_exception")
    val phone = text("phone").uniqueIndex("existing_phone_exception")
        .check("invalid_phone_exception") { it regexp "^[0-9]{10}$" }
    val taxId = text("tax_id").uniqueIndex("existing_customer_tax_id_exception").nullable()
    val totpSecret = text("totp_secret").nullable()
    val totpEnrolled = timestampWithTimeZone("totp_enrolled").nullable()

    val emailConfirmed = timestampWithTimeZone("email_confirmed").nullable()
    val phoneConfirmed = timestampWithTimeZone("phone_confirmed").nullable()

    val defaultCompany = reference("default_company", Companies)
    val defaultAddress = reference("default_address", Addresses)
}

object SystemRoles : NameableInsertedUpdatedDeletedTable("system_roles")
object CompanyRoles : NameableInsertedUpdatedDeletedTable("company_roles") {
    val company = reference("company", Companies)
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
    val user = reference("user", Users)
    val group = reference("group", CustomerGroups)
}