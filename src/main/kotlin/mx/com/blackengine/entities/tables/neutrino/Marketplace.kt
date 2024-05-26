package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.NameableInsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.citext
import org.jetbrains.exposed.sql.json.jsonb

object Stores : NameableInsertedUpdatedDeletedTable("stores") {
    val company = reference("company", Companies)
    val serverApp = reference("server_app", ServerApps)
    val domain = citext("domain")
    val isSingleSeller = bool("is_single_seller").default(true)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object ExternalStoreServers : InsertedUpdatedDeletedTable("external_store_servers") {
    val store = reference("store", Stores)
    val server = reference("server", Servers)
    val role = reference("role", EnumServerRoles)

    init {
        uniqueIndex(columns = arrayOf(store, server, role))
    }
}

