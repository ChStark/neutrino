package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.sql.json.jsonb

object Enums : MyUUIDTable() {
    val addressComponentTypes = citext("address_component_types").nullable()
        .index(isUnique = true, customIndexName = "existing_address_component_type_exception")
    val attributeTypes = citext("attribute_types").nullable()
        .index(isUnique = true, customIndexName = "existing_attribute_type_exception")
    val attributeUnits = citext("attribute_units").nullable()
        .index(isUnique = true, customIndexName = "existing_attribute_unit_exception")
    val codeScriptTypes = citext("code_script_types").nullable()
        .index(isUnique = true, customIndexName = "existing_code_script_type_exception")
    val companyTypes =
        citext("company_types").nullable().index(isUnique = true, customIndexName = "existing_company_type_exception")
    val digitalTaxReceiptTypes = citext("digital_tax_receipt_types").nullable()
        .index(isUnique = true, customIndexName = "existing_digital_tax_receipt_type_exception")
    val digitalTaxReceiptConceptTypes = citext("digital_tax_receipt_concept_types").nullable()
        .index(isUnique = true, customIndexName = "existing_digital_tax_receipt_concept_type_exception")
    val digitalTaxReceiptLogTypes = citext("digital_tax_receipt_log_types").nullable()
        .index(isUnique = true, customIndexName = "existing_digital_tax_receipt_log_type_exception")
    val googleAddressComponentTypes = citext("google_address_component_types").nullable()
        .index(isUnique = true, customIndexName = "existing_google_address_component_type_exception")
    val notificationTypes = citext("notification_types").nullable()
        .index(isUnique = true, customIndexName = "existing_notification_type_exception")
    val orderLineTypes = citext("order_line_types").nullable()
        .index(isUnique = true, customIndexName = "existing_order_line_type_exception")
    val orderLogTypes = citext("order_log_types").nullable()
        .index(isUnique = true, customIndexName = "existing_order_log_type_exception")
    val outboundEmailStatuses = citext("outbound_email_statuses").nullable()
        .index(isUnique = true, customIndexName = "existing_sent_email_status_exception")
    val outboundEmailTypes = citext("outbound_email_types").nullable()
        .index(isUnique = true, customIndexName = "existing_sent_email_type_exception")
    val paymentProcessors = citext("payment_processors").nullable()
        .index(isUnique = true, customIndexName = "existing_payment_processor_exception")
    val priceLogTypes = citext("price_log_types").nullable()
        .index(isUnique = true, customIndexName = "existing_price_log_type_exception")
    val productTransactionTypes = citext("product_transaction_types").nullable()
        .index(isUnique = true, customIndexName = "existing_product_transaction_type_exception")
    val productTypes =
        citext("product_types").nullable().index(isUnique = true, customIndexName = "existing_product_type_exception")
    val lotTypes =
        citext("lot_types").nullable().index(isUnique = true, customIndexName = "existing_lot_type_exception")
    val routeLogTypes = citext("route_log_types").nullable()
        .index(isUnique = true, customIndexName = "existing_route_log_type_exception")
    val ticketEntryTypes = citext("ticket_entry_types").nullable()
        .index(isUnique = true, customIndexName = "existing_ticket_entry_type_exception")
    val ticketTypes =
        citext("ticket_types").nullable().index(isUnique = true, customIndexName = "existing_ticket_type_exception")
    val props = jsonb<JsonElement>("props", Json { prettyPrint = false })
}

object Configs : MyUUIDTable() {
    val key = citext("key")
    val value = jsonb<JsonElement>("value", Json { prettyPrint = false }).nullable()
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
}

object Users : TimeAuditedTable() {
}

object Products : MyUUIDTable() {
}

object Lots : MyUUIDTable() {
    val name = text("name").nullable()
}