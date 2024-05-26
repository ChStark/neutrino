package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.Price
import mx.com.blackengine.entities.columns.timestampWithTimeZone
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.jsonb

object DigitalTaxReceipts : InsertedUpdatedDeletedTable("digital_tax_receipts") {
    val type = reference("type", EnumDigitalTaxReceiptTypes)
    val currentStage = reference("current_stage", Stages)
    val issuer = reference("issuer", Companies)
    val receiver = reference("receiver", Companies)
    val governmentTimestamp = timestampWithTimeZone("government_timestamp")
    val governmentId = text("government_id")
    val supplierId = text("supplier_id").nullable()
    val total = jsonb<Price>("total", Json.Default)
    val discounts = jsonb<Price>("amount", Json.Default).nullable()
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object DigitalTaxReceiptConcepts : InsertedUpdatedDeletedTable("digital_tax_receipt_concepts") {
    val digitalTaxReceipt = reference("digital_tax_receipt", DigitalTaxReceipts)
    val governmentId = text("government_id").nullable()
    val type = reference("type", EnumDigitalTaxReceiptConceptTypes)
    val unit = reference("unit", EnumDigitalTaxReceiptConceptUnits)
    val description = text("description")
    val unitPrice = jsonb<Price>("unit_price", Json.Default)
    val amount = jsonb<Price>("amount", Json.Default)
    val discounts = jsonb<Price>("discounts", Json.Default).nullable()
    val quantity = double("quantity")
    val product = reference("product", Products).nullable()
    val warehouse = reference("warehouse", Warehouses).nullable()
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object DigitalTaxReceiptPurchaseOrders : Table("digital_tax_receipt_purchase_orders") {
    val digitalTaxReceipt = reference("digital_tax_receipt", DigitalTaxReceipts)
    val purchaseOrder = reference("purchase_order", PurchaseOrders)

    init {
        uniqueIndex(columns = arrayOf(digitalTaxReceipt, purchaseOrder))
    }
}

object DigitalTaxReceiptConceptPurchaseOrderLines : Table("digital_tax_receipt_concept_purchase_order_lines") {
    val digitalTaxReceiptConcept = reference("digital_tax_receipt_concept", DigitalTaxReceiptConcepts)
    val purchaseOrderLine = reference("purchase_order_line", PurchaseOrderLines)

    init {
        uniqueIndex(columns = arrayOf(digitalTaxReceiptConcept, purchaseOrderLine))
    }
}

