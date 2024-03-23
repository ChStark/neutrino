package mx.com.blackengine.entities.tables

import org.jetbrains.exposed.sql.*

class XidColumnType : ColumnType() {
    override fun sqlType(): String {
        return "public.xid"
    }
}

object Users : Table() {
    val id = registerColumn<String>("id", XidColumnType()).defaultExpression(CustomFunction("public.xid", XidColumnType()))
    override val primaryKey = PrimaryKey(id)
}