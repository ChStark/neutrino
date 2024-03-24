package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import mx.com.blackengine.entities.columns.CurrentTimestampExpression
import mx.com.blackengine.entities.columns.XidTimeAuditedTable
import mx.com.blackengine.entities.columns.citext
import mx.com.blackengine.entities.columns.timestampWithTimeZone
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.json.jsonb


object Configs : Table() {
    val key = citext("key")
    val value = jsonb<ConfigProps>("value", Json { prettyPrint = false }).nullable()
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    override val primaryKey = PrimaryKey(key)
}

object Users : XidTimeAuditedTable() {
}
