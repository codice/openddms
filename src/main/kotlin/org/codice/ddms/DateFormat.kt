/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.ddms

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date

object DateFormat {
    const val UNKNOWN = "Unknown"
    const val NOT_APPLICABLE = "Not Applicable"
    private val formatters = listOf(
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,             // yyyy-MM-dd'T'hh:mm:ss.sTZD
            DateTimeFormatter.ISO_LOCAL_DATE,                   // yyy-MM-dd
            DateTimeFormatter.ofPattern("yyyy-MM"),
            DateTimeFormatter.ofPattern("yyyy"))

    fun isValid(date: String, unknownOrNotApplicable: Boolean = false): Boolean {
        if (unknownOrNotApplicable && (date == UNKNOWN || date == NOT_APPLICABLE))
            return true

        for (formatter in formatters) {
            try {
                formatter.parse(date)
                return true
            } catch (ignore: DateTimeParseException) {
            }
        }

        return false
    }

    fun parse(date: String): Date? {

        try {
            val offsetDateTime = OffsetDateTime.parse(date)
            return Date.from(offsetDateTime.toInstant())
        } catch (ignore: DateTimeParseException) {
        }

        try {
            val localDate = LocalDate.parse(date)
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        } catch (ignore: DateTimeParseException) {
        }

        try {
            val format = SimpleDateFormat("yyyy-MM")
            return format.parse(date)
        } catch (ignore: ParseException) {
        }

        try {
            val format = SimpleDateFormat("yyyy")
            return format.parse(date)
        } catch (ignore: ParseException) {
        }

        return null
    }
}
