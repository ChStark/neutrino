package mx.com.blackengine.entities.tables

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.InsertedUpdatedTable
import mx.com.blackengine.entities.columns.citext
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.TextColumnType
import org.jetbrains.exposed.sql.json.jsonb

object Scripts : InsertedUpdatedDeletedTable("scripts") {
    val type = reference("type", EnumScriptTypes)
    val language = reference("language", EnumScriptLanguageTypes)
    val company = reference("company", Companies)
    val creator = reference("creator", Users)
    val name = citext("name")
    val code = text("code")
    val version = integer("version")
    val notificationGroup = reference("notification_group", NotificationGroups).nullable()
}

object Workflows : InsertedUpdatedDeletedTable("workflows") {
    val name = citext("name")
    val warehouse = reference("warehouse", Warehouses).nullable()
    val company = reference("company", Companies).nullable()
    val creator = reference("creator", Users)
    val currentScript = reference("current_script", Scripts)
}

object Stages : InsertedUpdatedTable("stages") {
    val company = reference("company", Companies)
    val name = citext("name")
    val currentScript = reference("current_script", Scripts)
}

object StageEdges : Table("stage_edges") {
    val from = reference("from", Stages)
    val to = reference("to", Stages)
    val isInitialStage = bool("is_initial_state").default(false)
    val isFinalStage = bool("is_final_state").default(false)

    init {
        uniqueIndex(columns = arrayOf(from, to))
    }
}

object Bots : InsertedUpdatedDeletedTable("bots") {
    val type = reference("type", EnumBotTypes)
    val company = reference("company", Companies)
    val name = citext("name")
    val creator = reference("creator", Users)
    val isSystem = bool("is_system").default(false)
    val isPublic = bool("is_public").default(false)
    val currentScript = reference("current_script", Scripts).nullable()
    val currentCommand = text("command").nullable()
    val props = jsonb<JsonElement>("props", Json.Default).nullable() // params to run in case is system command
}

object BotSetups : InsertedUpdatedDeletedTable("bot_setups") {
    val bot = reference("bot", Bots)
    val stage = reference("stage", Stages)
    val cron = citext("cron")
    val notificationGroup = reference("notification_group", NotificationGroups).nullable()
}

object Actions : InsertedUpdatedDeletedTable("actions") {
    val company = reference("company", Companies)
    val name = citext("name")
    val aliases = array<String>("aliases", TextColumnType()).nullable()
    val currentScript = reference("current_script", Scripts)
}

object StageActions : InsertedUpdatedDeletedTable("stage_actions"){
    val stage = reference("stage",Stages)
    val action = reference("action",Actions)
    val isStageDefault = bool("is_stage_default").nullable()
}