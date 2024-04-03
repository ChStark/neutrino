package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.sql.Function
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.TextColumnType

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

open class EmptyJsonExpression<T> : Function<T>(TextColumnType()) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder): Unit = queryBuilder {
        append("'{}'::JSONB")
    }
}
