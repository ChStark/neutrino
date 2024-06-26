package mx.com.blackengine.entities.columns

import com.github.f4b6a3.uuid.UuidCreator
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.tables.neutrino.Companies
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.UUIDColumnType
import org.jetbrains.exposed.sql.json.jsonb
import java.util.*

open class UUID7Table(name: String = "", columnName: String = "id") : IdTable<UUID>(name) {
    final override val id: Column<EntityID<UUID>> =
        registerColumn<UUID>(columnName, UUIDColumnType()).clientDefault { UuidCreator.getTimeOrderedEpoch() }
            .entityId()
    final override val primaryKey = PrimaryKey(id)
}

abstract class TimestampTable(name: String = "") : UUID7Table(name) {
    val timestamp = timestampWithTimeZone("timestamp").defaultExpression(CurrentTimestampExpression())
}

abstract class FiniteEventTable(name: String = "") : UUID7Table(name) {
    val start = timestampWithTimeZone("start").defaultExpression(CurrentTimestampExpression())
    val end = timestampWithTimeZone("end").defaultExpression(CurrentTimestampExpression())
}

abstract class ProcessRunEventTable(name: String = "") : FiniteEventTable(name) {
    val stdOutput = jsonb<JsonElement>("std_output", Json.Default)
    val stdError = jsonb<JsonElement>("std_error", Json.Default)
}

abstract class InsertedUpdatedTable(name: String = "") : UUID7Table(name) {
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
}

abstract class InsertedUpdatedDeletedTable(name: String = "") : UUID7Table(name) {
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
}

abstract class NameableInsertedUpdatedDeletedTable(name: String = "") : UUID7Table(name) {
    val name = citext("name").uniqueIndex()
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
}

abstract class LocationInsertedUpdatedDeletedTable(name: String = "") : UUID7Table(name) {
    val name = citext("name")
    val latitude = double("latitude").check() { it.between(-90.0, 90.0) }
    val longitude = double("longitude").check() { it.between(-180.0, 180.0) }
    val inserted = timestampWithTimeZone("inserted").defaultExpression(CurrentTimestampExpression())
    val updated = timestampWithTimeZone("updated").defaultExpression(CurrentTimestampExpression())
    val deleted = timestampWithTimeZone("deleted").nullable()
}

open class EnumerableTable(name: String = "") : IdTable<String>(name) {
    override val id: Column<EntityID<String>> = citext("id").entityId()
    val company = reference("company", Companies)
    val props = jsonb<JsonElement>("props", Json.Default).defaultExpression(EmptyJsonExpression())
}