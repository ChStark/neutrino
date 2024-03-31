package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.UUID7Table
import mx.com.blackengine.entities.columns.citext
import mx.com.blackengine.entities.columns.timestampWithTimeZone
import org.jetbrains.exposed.sql.json.jsonb

object ConfigLog : UUID7Table("config_log") {
    val key = citext("key")
    val value = jsonb<JsonElement>("value", Json.Default).nullable()
    val timestamp = timestampWithTimeZone("timestamp")
}

object PriceLog : UUID7Table("price_log") {
    val type = reference("type", EnumPriceLogTypes)
    val price = reference("price", Prices)
    val modifier = reference("modifier", Users)
    val timestamp = timestampWithTimeZone("timestamp")
    val comment = text("comment").nullable()
    val props = jsonb<JsonElement>("props", Json.Default)
}