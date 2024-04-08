package mx.com.blackengine.entities.columns

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import java.sql.ResultSet

class NumericRange {
    var lower: Number?
    var upper: Number?

    constructor(lower: Number?, upper: Number?) {
        this.lower = lower
        this.upper = upper
    }

    constructor(text: String) {
        val vals = text.substring(1, text.length - 1).split(',')
        if (vals.size != 2) {
            this.lower = null
            this.upper = null
        }

        this.lower = vals[0].toDoubleOrNull()
        this.upper = vals[1].toDoubleOrNull()
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

class NumericRangeColumnType : ColumnType() {
    override fun nonNullValueToString(value: Any): String {
        if (value !is NumericRange)
            error("$value is not a NumericRange!")

        return value.toString()
    }

    override fun valueFromDB(value: Any): NumericRange {
        if (value is String) {
            return NumericRange(value)
        }

        error("$value is not a parsable NumericRange")
    }

    override fun readObject(rs: ResultSet, index: Int): Any {
        return NumericRange(rs.getString(index))
    }

    override fun sqlType() = "NUMRANGE"

    override fun notNullValueToDB(value: Any): Any {
        if (value !is NumericRange)
            error("$value is not a NumericRange!")

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
fun Table.numericRange(name: String): Column<NumericRange> =
    registerColumn(name, NumericRangeColumnType())