package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.json.jsonb


object Configs : Table() {
    val key = citext("key")
    val value = jsonb<ConfigProps>("value", Json { prettyPrint = false })
    val lastUpdate = timestampWithTimeZone("last_update").defaultExpression(
        CustomFunction(
            "CURRENT_TIMESTAMP",
            TimestampTZColumnType()
        )
    )
    override val primaryKey = PrimaryKey(key)
}

object Users : Table() {
    val id = xid("id").defaultExpression(CustomFunction("public.xid", XidColumnType()))
    override val primaryKey = PrimaryKey(id)
}