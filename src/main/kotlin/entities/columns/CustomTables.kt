package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.dao.id.IdTable

abstract class TimeAuditedTable<T : Comparable<T>>(name: String = "", columnName: String = "id") :
    IdTable<T>(name) {
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
}