package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.sql.json.jsonb

object Configs : UUID7Table("configs") {
    val key = citext("key")
    val value = jsonb<JsonElement>("value", Json { prettyPrint = false }).nullable()
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
}

object Users : TimeAuditedTable("users") {
}

object Products : TimeAuditedTable("products") {
}

object Lots : TimeAuditedTable("lots") {
}