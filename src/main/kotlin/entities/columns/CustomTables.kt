package mx.com.blackengine.entities.columns

import com.github.f4b6a3.uuid.UuidCreator
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.UUIDColumnType
import org.jetbrains.exposed.sql.json.jsonb
import java.util.*

open class MyUUIDTable(name: String = "", columnName: String = "id") : IdTable<UUID>(name) {
    final override val id: Column<EntityID<UUID>> =
        registerColumn<UUID>(columnName, UUIDColumnType()).clientDefault { UuidCreator.getTimeOrderedEpoch() }
            .entityId()
    final override val primaryKey = PrimaryKey(id)
}

abstract class TimeAuditedTable(name: String = "") : MyUUIDTable(name) {
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
}

open class EnumerableTable(name: String = "") : IdTable<String>(name) {
    override val id: Column<EntityID<String>> = citext("id").entityId()
    val props = jsonb<JsonElement>("props", Json { prettyPrint = false })
}