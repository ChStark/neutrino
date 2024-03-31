package mx.com.blackengine.entities.tables

import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable

object Scripts : InsertedUpdatedDeletedTable("scripts"){
    val type = reference("type",EnumScriptTypes)
}

object Workflows : InsertedUpdatedDeletedTable("workflows"){
}