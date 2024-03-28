package mx.com.blackengine.entities.columns

import com.github.f4b6a3.uuid.UuidCreator
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.UUIDColumnType
import java.util.*

open class MyUUIDTable(name: String = "", columnName: String = "id") : IdTable<UUID>(name) {
    final override val id: Column<EntityID<UUID>> =
        registerColumn<UUID>(columnName, UUIDColumnType()).clientDefault { UuidCreator.getTimeOrderedEpoch() }
            .entityId()
    final override val primaryKey = PrimaryKey(id)
}

abstract class TimeAuditedTable(name: String = "", columnName: String = "id") : MyUUIDTable(name) {
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
}

