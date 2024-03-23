package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table

class XidColumnType : ColumnType() {
    override fun sqlType(): String {
        return "public.xid"
    }
}

class CitextColumnType : ColumnType() {
    override fun sqlType(): String {
        return "citext"
    }
}


fun Table.xid(name: String): Column<String> = registerColumn(name, XidColumnType())
fun Table.citext(name: String): Column<String> = registerColumn(name, CitextColumnType())