package mx.com.blackengine.entities.tables

import mx.com.blackengine.entities.columns.UUID7Table

object Addresses : UUID7Table("address") {
    val street = text("street")
    val exteriorNumber = text("exterior_number")
    val interiorNumber = text("interior_number").nullable()
    val comments = text("comments")
    val owner = reference("owner", Users)
    val parentComponent = reference("parentComponent", AddressComponents)
}

object AddressComponents : UUID7Table("address_components") {
    val type = reference("type", EnumAddressComponentTypes.id)
    val googleType = reference("google_type", EnumGoogleAddressComponentTypes.id)
    val name = text("name")
    val google_name = text("google_name")
    val latitude = double("latitude").check("invalid_latitude_exception") { it.between(-90.0, 90.0) }
    val longitude = double("longitude").check("invalid_longitude_exception") { it.between(-180.0, 180.0) }
    val zipcode = text("zipcode").check("invalid_zipcode_exception") { it regexp "^[0-9]{4,5}$" }
    val parentComponent = reference("parent_component", AddressComponents)
}