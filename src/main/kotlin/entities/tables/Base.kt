package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.sql.json.jsonb

object EnumAddressComponentTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_address_component_type_exception")
}

object EnumAttributeTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_attribute_type_exception")
}

object EnumAttributeUnits: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_attribute_unit_exception")
}

object EnumCodeScriptTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_code_script_type_exception")
}

object EnumCompanyTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_company_type_exception")
}

object EnumDigitalTaxReceiptTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_digital_tax_receipt_type_exception")
}

object EnumDigitalTaxReceiptConceptTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_digital_tax_receipt_concept_type_exception")
}
object EnumDigitalTaxReceiptLogTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_digital_tax_receipt_log_type_exception")
}
object EnumGoogleAddressComponentTypes: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "existing_google_address_component_type_exception")
}
object Enum: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "")
}
object Enum: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "")
}
object Enum: EnumerableTable(){
    override val primaryKey = PrimaryKey(id, name = "")
}

object Enums : MyUUIDTable() {
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

object Products : TimeAuditedTable() {
}

object Lots : TimeAuditedTable() {
    val name = text("name").nullable()
}