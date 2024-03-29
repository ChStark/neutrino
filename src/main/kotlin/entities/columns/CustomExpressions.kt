package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.sql.Function
import org.jetbrains.exposed.sql.QueryBuilder

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