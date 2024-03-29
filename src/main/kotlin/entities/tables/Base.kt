package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.sql.json.jsonb

object EnumAddressComponentTypes : EnumerableTable("enum_address_component_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_address_component_type_exception")
}

object EnumAttributeTypes : EnumerableTable("enum_attribute_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_attribute_type_exception")
}

object EnumAttributeUnits : EnumerableTable("enum_attribute_units") {
    override val primaryKey = PrimaryKey(id, name = "existing_attribute_unit_exception")
}

object EnumCodeScriptTypes : EnumerableTable("enum_code_script_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_code_script_type_exception")
}

object EnumCompanyTypes : EnumerableTable("enum_company_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_company_type_exception")
}

object EnumDigitalTaxReceiptTypes : EnumerableTable("enum_digital_tax_receipt_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_digital_tax_receipt_type_exception")
}

object EnumDigitalTaxReceiptConceptTypes : EnumerableTable("enum_digital_tax_receipt_concept_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_digital_tax_receipt_concept_type_exception")
}

object EnumDigitalTaxReceiptLogTypes : EnumerableTable("enum_digital_tax_receipt_log_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_digital_tax_receipt_log_type_exception")
}

object EnumGoogleAddressComponentTypes : EnumerableTable("enum_google_address_component_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_google_address_component_type_exception")
}

object EnumNotificationTypes : EnumerableTable("enum_notification_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_notification_type_exception")
}

object EnumOrderLineTypes : EnumerableTable("enum_order_line_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_order_line_type_exception")
}

object EnumOrderLogTypes : EnumerableTable("enum_order_log_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_order_log_type_exception")
}

object EnumOutboundEmailStatuses : EnumerableTable("enum_outbound_email_statuses") {
    override val primaryKey = PrimaryKey(id, name = "existing_outbound_email_status_exception")
}

object EnumOutboundEmailTypes : EnumerableTable("enum_outbound_email_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_outbound_email_type_exception")
}

object EnumPaymentProcessors : EnumerableTable("enum_payment_processors") {
    override val primaryKey = PrimaryKey(id, name = "existing_payment_processor_exception")
}

object EnumPriceLogTypes : EnumerableTable("enum_price_log_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_price_log_type_exception")
}

object EnumProductTransactionTypes : EnumerableTable("enum_product_transaction_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_product_transaction_type_exception")
}

object EnumProductTypes : EnumerableTable("enum_product_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_product_type_exception")
}

object EnumLotTypes : EnumerableTable("enum_lot_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_lot_type_exception")
}

object EnumRouteLogTypes : EnumerableTable("enum_route_log_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_route_log_type_exception")
}

object EnumTicketEntryTypes : EnumerableTable("enum_ticket_entry_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_ticket_entry_type_exception")
}

object EnumTicketTypes : EnumerableTable("enum_ticket_types") {
    override val primaryKey = PrimaryKey(id, name = "existing_ticket_type_exception")
}

object Configs : MyUUIDTable() {
    val key = citext("key")
    val value = jsonb<JsonElement>("value", Json { prettyPrint = false }).nullable()
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
}

object Users : TimeAuditedTable() {
}

object Products : TimeAuditedTable() {
}

object Lots : TimeAuditedTable() {
}