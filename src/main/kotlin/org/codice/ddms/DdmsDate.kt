/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms

import java.lang.IllegalArgumentException
import java.time.DateTimeException
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.temporal.TemporalAccessor

private val datetimeFormatter = DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME) // yyyy-MM-dd'T'hh:mm:ss.sTZD
        .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME) // yyyy-MM-dd'T'hh:mm:ss
        .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE) // yyyy-MM-dd
        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM"))
        .appendOptional(DateTimeFormatter.ofPattern("yyyy"))
        .toFormatter()

private const val UNKNOWN = "Unknown"
private const val NOT_APPLICABLE = "Not Applicable"

class DdmsDate {
    private val dateString: String
    private val parsedDate: TemporalAccessor?

    companion object {
        val unknown = DdmsDate(UNKNOWN)
        val notApplicable = DdmsDate(NOT_APPLICABLE)

        fun isValid(date: String): Boolean {
            if (date == UNKNOWN || date == NOT_APPLICABLE) {
                return true
            }
            return try {
                datetimeFormatter.parse(date)
                true
            } catch (ignore: DateTimeParseException) {
                false
            }
        }

        fun isValid(date: TemporalAccessor): Boolean {
            return try {
                datetimeFormatter.format(date)
                true
            } catch (ignore: DateTimeException) {
                false
            }
        }
    }

    constructor(date: String) {
        dateString = date
        if (!isUnknown() && !isNotApplicable()) {
            try {
                parsedDate = datetimeFormatter.parse(date)
            } catch (e: DateTimeParseException) {
                throw IllegalArgumentException("Invalid date: $date")
            }
        } else {
            parsedDate = null
        }
    }

    constructor(date: TemporalAccessor) {
        parsedDate = date
        try {
            dateString = datetimeFormatter.format(date)
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("Invalid date: $date")
        }
    }

    fun isUnknown() = dateString == UNKNOWN

    fun isNotApplicable() = dateString == NOT_APPLICABLE

    fun toRawTemporalAccessor(): TemporalAccessor = parsedDate ?: throw IllegalStateException("DdmsDate is either " +
            "Unknown or Not Applicable. Can't be converted to TemporalAccessor.")

    override fun toString() = dateString

    // Doesn't consider parsedDate as the different implementations have different equals.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DdmsDate

        if (dateString != other.dateString) return false

        return true
    }

    override fun hashCode(): Int = dateString.hashCode()
}
