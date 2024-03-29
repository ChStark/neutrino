package mx.com.blackengine.entities.tables

import mx.com.blackengine.entities.columns.MyUUIDTable

object AddressComponents : MyUUIDTable() {
    val type = reference("type", EnumAddressComponentTypes.id)
    val googleType = reference("google_type", EnumGoogleAddressComponentTypes.id)
}