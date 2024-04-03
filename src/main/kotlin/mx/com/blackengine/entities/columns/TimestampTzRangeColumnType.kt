package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.IDateColumnType
import org.jetbrains.exposed.sql.Table
import java.sql.ResultSet
import java.time.Instant

class InstantRange {
    var lower: Instant?
    var upper: Instant?

    constructor(lower: Instant?, upper: Instant?) {
        this.lower = lower
        this.upper = upper
    }

    constructor(text: String) {
        val vals = text.substring(1, text.length - 1).split(',')
        if (vals.size != 2) {
            this.lower = null
            this.upper = null
        }

        if (vals[0].isNotEmpty()) {
            this.lower = Instant.parse(vals[0]) //TODO: this probably will fail, postgres format is not ISO8601, is '2024-03-25 18:05:32.483743+00':
        } else {
            this.lower = null
        }

        if (vals[1].isNotEmpty()) {
            this.upper = Instant.parse(vals[1])
        } else {
            this.upper = null
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append('[')
        if (lower != null) sb.append(lower.toString())
        sb.append(',')
        if (upper != null) sb.append(upper.toString())
        sb.append(']')
        return sb.toString()
    }
}

class TimestampTzRangeColumnType : ColumnType(), IDateColumnType {
    override val hasTimePart: Boolean = true

    override fun nonNullValueToString(value: Any): String {
        if (value !is InstantRange)
            error("$value is not a InstantRange!")

        return value.toString()
    }

    override fun valueFromDB(value: Any): InstantRange {
        if (value is String) {
            return InstantRange(value)
        }

        error("$value is not a parsable InstantRange")
    }

    override fun readObject(rs: ResultSet, index: Int): Any? {
        return InstantRange(rs.getString(index))
    }

    override fun sqlType() = "TSTZRANGE"

    override fun notNullValueToDB(value: Any): Any {
        if (value !is InstantRange)
            error("$value is not a java.time.Instant!")

        return value.toString()
    }
}

/**
 * A timestamp with timezone to store a instant.
 *
 * **See:** https://www.toolbox.com/tech/data-management/blogs/zone-of-misunderstanding-092811/
 *
 * @param name The column name
 */
fun Table.timestampWithTimeZoneRange(name: String): Column<InstantRange> =
    registerColumn(name, TimestampTzRangeColumnType())