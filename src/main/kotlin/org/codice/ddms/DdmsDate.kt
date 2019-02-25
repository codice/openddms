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
        .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
        .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM"))
        .appendOptional(DateTimeFormatter.ofPattern("yyyy"))
        .toFormatter()

private const val UNKNOWN = "Unknown"
private const val NOT_APPLICABLE = "Not Applicable"

/**
 * Wrapper class for validating dates.
 *
 * DDMS only allows for the following date-time formats:
 * - `yyyy-MM-dd'T'hh:mm:ss.sTZD`
 * - `yyyy-MM-dd'T'hh:mm:ss`
 * - `yyyy-MM-dd`
 * - `yyyy-MM`
 * - `yyyy`
 * - `Unknown`
 * - `Not Applicable`
 *
 * @throws IllegalArgumentException If parsed an invalid date format for DDMS.
 */
class DdmsDate {
    private val dateString: String
    private val parsedDate: TemporalAccessor?

    companion object {
        /**
         * [DdmsDate] for an `Unknown` date.
         */
        val unknown = DdmsDate(UNKNOWN)

        /**
         * [DdmsDate] for a `Not Applicable` date.
         */
        val notApplicable = DdmsDate(NOT_APPLICABLE)

        /**
         * Helper function for validating if a [String] is in the correct format.
         * @return True if the [String] is properly formatted, otherwise false.
         */
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

        /**
         * Helper function for validating if a [TemporalAccessor] is in the correct format.
         *
         * @return True if the [TemporalAccessor] is properly formatted, otherwise false.
         */
        fun isValid(date: TemporalAccessor): Boolean {
            return try {
                datetimeFormatter.format(date)
                true
            } catch (ignore: DateTimeException) {
                false
            }
        }
    }

    /**
     * Parses and validates a [String].
     *
     * @param date The [String] to parse and validate.
     * @throws IllegalArgumentException if [date] is invalid.
     */
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

    /**
     * Parses and validates a [TemporalAccessor].
     *
     * @param date The [TemporalAccessor] to parse and validate.
     * @throws IllegalArgumentException if [date] is invalid.
     */
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

    /**
     * Get the raw [TemporalAccessor] of this date if it's available.
     *
     * We're returning a [TemporalAccessor] instead of a concrete class as we don't want to make any assumptions about
     * missing data. This does put a burden on the user to parse / convert it to a concrete class. Consider using
     * [DdmsDate.toString] instead.
     *
     * @return The [TemporalAccessor] of the date.
     * @throws IllegalStateException when [parsedDate] is [DdmsDate.unknown] or [DdmsDate.notApplicable].
     */
    fun toRawTemporalAccessor(): TemporalAccessor = parsedDate ?: throw IllegalStateException("DdmsDate is either " +
            "Unknown or Not Applicable. Can't be converted to TemporalAccessor.")

    /**
     * Outputs the date in a human-readable format.
     */
    override fun toString() = dateString

    // Doesn't consider parsedDate as the different implementations have different equals.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DdmsDate

        if (dateString != other.dateString) return false

        return true
    }

    // Doesn't consider parsedDate as toEquals doesn't use it.
    override fun hashCode(): Int = dateString.hashCode()
}
