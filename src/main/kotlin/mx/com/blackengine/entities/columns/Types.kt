package mx.com.blackengine.entities.columns

import kotlinx.serialization.Serializable

@Serializable
class Price(val currencyCode: String, val base: Int, val vat: Int, val ieps: Int, val total: Int)