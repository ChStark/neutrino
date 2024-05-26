package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.InsertedUpdatedTable
import mx.com.blackengine.entities.columns.NameableInsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.timestampWithTimeZoneRange
import org.jetbrains.exposed.sql.json.jsonb

object Carriers : NameableInsertedUpdatedDeletedTable("carriers") {
    val company = reference("company", Companies)
    val rootUser = reference("root_user", Users)
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object Routes : InsertedUpdatedDeletedTable("routes") {
    val currentStage = reference("current_stage", Stages)
    val warehouse = reference("warehouse", Warehouses)
    val carrier = reference("carrier", Carriers)
    val iid = long("iid") //serial over warehouse
    val driver = reference("driver", Users)
    val assigner = reference("assigner", Users)
    val estimatedLifespan = timestampWithTimeZoneRange("estimated_lifespan").nullable()
    val realLifespan = timestampWithTimeZoneRange("real_lifespan").nullable()
    val lastKnownLatitude = double("last_known_latitude").check() { it.between(-90.0, 90.0) }
    val lastKnownLongitude = double("last_known_longitude").check() { it.between(-180.0, 180.0) }
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
}

object RouteEdges : InsertedUpdatedTable("route_edges") {
    val currentStage = reference("current_stage", Stages)
    val route = reference("route", Routes)
    val position = integer("position").default(0)
    val from = reference("from", Orders)
    val to = reference("to", Orders)
    val estimatedLifespan = timestampWithTimeZoneRange("estimated_lifespan").nullable()
    val realLifespan = timestampWithTimeZoneRange("real_lifespan").nullable()
    val estimated_distance = integer("estimated_distance").default(0) // in meters
    val real_distance = integer("real_distance").default(0) // in meters
}