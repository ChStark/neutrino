package mx.com.blackengine.entities.tables

import mx.com.blackengine.entities.columns.MyUUIDTable
import mx.com.blackengine.entities.columns.citext

object AddressComponents : MyUUIDTable() {
    val type = citext("type").references( EnumAddressComponentTypes.id )
    val googleType = citext("google_type").references(Enums.googleAddressComponentTypes)
}