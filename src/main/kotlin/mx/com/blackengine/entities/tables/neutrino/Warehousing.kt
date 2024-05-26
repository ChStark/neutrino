package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.LocationInsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.NameableInsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.UUID7Table
import org.jetbrains.exposed.sql.json.jsonb

object Warehouses : LocationInsertedUpdatedDeletedTable("warehouses") {
    val company = reference("company", Companies)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
    val parent = reference("parent", Warehouses).nullable()
}

object WarehouseProductFlows : UUID7Table("warehouse_product_flows") {
    val from = reference("from", Warehouses)
    val to = reference("to", Warehouses)
    val canSell = bool("can_sell").default(false)
    val canSend = bool("can_send").default(false)
    val canCommit = bool("can_commit").default(false)
}

object Bins : NameableInsertedUpdatedDeletedTable("bins"){
    val warehouse = reference("warehouse", Warehouses)
    val company = reference("company", Companies).nullable() // in case another company has ownership and we need to restrict consumption

    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}
