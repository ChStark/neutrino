package mx.com.blackengine.entities.tables.neutrino

import kotlinx.serialization.json.Json
import mx.com.blackengine.entities.columns.Price
import mx.com.blackengine.entities.columns.UUID7Table
import org.jetbrains.exposed.sql.json.jsonb

object Prices : UUID7Table("prices") {
    val type = reference("type", EnumPriceTypes)
    val product = reference("product", Products)
    val price = jsonb<Price>("price", Json.Default)
    val discount = jsonb<Price>("discount", Json.Default).nullable()
    val precedence = integer("precedence").default(0) //the bigger the value the important the price
    val isDefault = bool("is_default").nullable()

    val customerGroup = reference("customer_group", CustomerGroups).nullable()
    val paymentMethod = reference("payment_method",EnumPaymentMethods).nullable()
    val warehouse = reference("warehouse",Warehouses).nullable()
    val salesChannel = reference("sales_channel", SalesChannels).nullable()
}