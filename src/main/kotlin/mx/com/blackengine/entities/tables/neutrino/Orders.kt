package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.EmptyJsonExpression
import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.TimestampTable
import org.jetbrains.exposed.sql.json.jsonb

object Orders : InsertedUpdatedDeletedTable("orders") {
    val company = reference("company", Companies)
}

object OrderLines : TimestampTable("order_lines") {
    val type = reference("type", EnumOrderLineTypes)
    val order = reference("order", Orders)
}

object Payments : TimestampTable("payments") {
    val type = reference("type", EnumPaymentMethods)
    val order = reference("order", Orders)
    val company = reference("company", Companies)
    val parent = reference("parent", OrderLines)
    val props = jsonb<JsonElement>("props", Json.Default).defaultExpression(EmptyJsonExpression())
}