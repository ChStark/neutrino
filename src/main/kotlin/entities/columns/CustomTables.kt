package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.dao.id.IdTable

abstract class TimeAuditedTable(name: String = "", columnName: String = "id") : IdTable<String>(name) {
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
}