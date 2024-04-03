package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.UUID7Table
import org.jetbrains.exposed.sql.json.jsonb

object Stores : UUID7Table("stores") {
    val company = reference("company", Companies)
    val isSingleSeller = bool("is_single_seller").default(true)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}