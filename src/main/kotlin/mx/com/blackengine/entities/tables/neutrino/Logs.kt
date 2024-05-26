package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.ProcessRunEventTable
import mx.com.blackengine.entities.columns.TimestampTable
import mx.com.blackengine.entities.columns.citext
import org.jetbrains.exposed.sql.json.jsonb

object ConfigLog : TimestampTable("config_log") {
    val key = citext("key")
    val value = jsonb<JsonElement>("value", Json.Default).nullable()
    val modifier = reference("modifier", Users)
}

object PriceLog : TimestampTable("price_log") {
    val type = reference("type", EnumPriceLogTypes)
    val price = reference("price", Prices)
    val modifier = reference("modifier", Users)
    val comment = text("comment").nullable()
    val props = jsonb<JsonElement>("props", Json.Default)
}

object BotLog : ProcessRunEventTable("bot_log"){
    val botSetup = reference("bot_setup", BotSetups)
}

object RouteLog : TimestampTable("route_log"){
    val type = reference("type", EnumRouteLogTypes)
    val route = reference("route", Routes)
}