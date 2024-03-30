package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.UUID7Table
import mx.com.blackengine.entities.columns.citext
import mx.com.blackengine.entities.columns.timestampWithTimeZone
import org.jetbrains.exposed.sql.json.jsonb

object ConfigLog : UUID7Table("config_log") {
    val key = citext("key")
    val value = jsonb<JsonElement>("value", Json { prettyPrint = false }).nullable()
    val timestamp = timestampWithTimeZone("timestamp")
}