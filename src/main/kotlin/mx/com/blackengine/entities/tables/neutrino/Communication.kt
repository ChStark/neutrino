package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.json.json
import org.jetbrains.exposed.sql.json.jsonb

object NotificationGroups : UUID7Table("notification_groups") {
    val type = reference("type", EnumNotificationTypes)
    val company = reference("company", Companies)
    val name = citext("name")
    val emailAddresses = array<String>("email_addresses").nullable()
    val discordWebhooks = array<String>("discord_webhooks").nullable()
    val telegramChannels = array<String>("telegram_channels").nullable()
    val slackChannels = array<String>("slack_channels").nullable()
    val props = json<JsonElement>("props", Json.Default).nullable()

    init {
        uniqueIndex(columns = arrayOf(company, name))
    }
}

object OutboundEmails : IdTable<String>("outbound_emails") {
    override val id = char("id", 60).entityId()
    val company = reference("company", Companies)
    val type = reference("type", EnumOutboundEmailTypes)
    val status = reference("status", EnumOutboundEmailStatuses)
    val timestamp = timestampWithTimeZone("timestamp").defaultExpression(CurrentTimestampExpression())
    val email = text("email")
    val props = jsonb<JsonElement>("props", Json.Default).nullable()
    override val primaryKey = PrimaryKey(id)
}

object OutboundSms : UUID7Table("outbound_sms") {
    val company = OutboundEmails.reference("company", Companies)
    val status = text("status").nullable()
    val timestamp = timestampWithTimeZone("timestamp").nullable()
    val phone = text("phone").nullable()
    val text = text("text").nullable()
}

object OtpUsages : UUID7Table("otp_usages") {
    val owner = reference("owner", Users)
    val code = char("code", 6)
    val lifespan = timestampWithTimeZoneRange("lifespan")

    init {
        uniqueIndex(columns = arrayOf(owner, code))
    }
}