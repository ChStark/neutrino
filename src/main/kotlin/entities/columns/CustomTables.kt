package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.sql.Table

open class XidTable(name: String = "", columnName: String = "id") : Table(name) {
    val id = xid(columnName).defaultExpression(NewXidExpression())
    final override val primaryKey = PrimaryKey(id)
}

open class XidTimeAuditedTable(name: String = "", columnName: String = "id") : Table(name) {
    val id = xid(columnName).defaultExpression(NewXidExpression())
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
    final override val primaryKey = PrimaryKey(id)
}