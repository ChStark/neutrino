package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.InsertedUpdatedTable
import mx.com.blackengine.entities.columns.citext
import mx.com.blackengine.entities.columns.timestampWithTimeZone
import org.jetbrains.exposed.sql.json.jsonb

object Products : InsertedUpdatedDeletedTable("products") {
    val type = reference("type", EnumProductTypes)
    val company = reference("company", Companies)
    val name = citext("name")
    val slug = citext("slug")
    val sku = citext("sku")

    val warehousingUnit = reference("unit", EnumUnits)

    val defaultBin = reference("default_bin", Bins)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object ProductKits : InsertedUpdatedTable("product_kits") {
    val kit = reference("kit", Products) //the parent product
    val product = reference("product", Products) //the actual product
    val quantity = integer("quantity")
}

object ProductAvailability : InsertedUpdatedTable("product_availability") {
    val product = reference("product", Products)
    val warehouse = reference("warehouse", Warehouses)
    val currentQuantity = double("current_quantity").default(0.0)
    val currentCommitted = double("current_committed").default(0.0).check { it lessEq currentQuantity }
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object ProductVisibility : InsertedUpdatedTable("product_visibility") {
    val product = reference("product", Products)
    val warehouse = reference("warehouse", Warehouses)
    val quantity = double("current_quantity").default(0.0)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object Lots : InsertedUpdatedDeletedTable("lots") {
    val type = reference("type", EnumLotTypes)
    val warehouse = reference("warehouse", Warehouses)
    val bin = reference("bin", Bins).nullable()
    val product = reference("product", Products)
    val internalIdentifier = text("internal_identifier").nullable()
    val supplierIdentifier = text("supplier_identifier").nullable()
    val manufacturerIdentifier = text("manufacturer_identifier").nullable()
    val expiration = timestampWithTimeZone("expiration").nullable()
    val initialQuantity = double("initial_quantity").check { it greaterEq 0.0 }
    val currentQuantity = double("current_quantity").check { it greaterEq 0.0 }
}

//TODO: product attributes