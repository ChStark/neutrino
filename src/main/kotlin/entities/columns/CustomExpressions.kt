package mx.com.blackengine.entities.columns

import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Function
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.json.JsonBColumnType

open class CurrentTimestampExpression<T> : Function<T>(TimestampTzColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder): Unit = queryBuilder {
        append("CURRENT_TIMESTAMP")
    }
}

open class NewXidExpression<T> : Function<T>(XidColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder): Unit = queryBuilder {
        append("public.xid()")
    }
}