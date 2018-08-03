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
package org.codice.ddms.v2.validation

import org.codice.ddms.DateFormat
import org.codice.ddms.v2.resource.Dates
import org.junit.Test
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.Year
import java.time.YearMonth

class DatesTest {
    private val offsetDateTime = OffsetDateTime.now().toString()
    private val localDate = LocalDate.now().toString()
    private val yearMonth = YearMonth.now().toString()
    private val year = Year.now().toString()

    @Test
    fun `a Dates with default arguments is valid`() {
        Dates()
    }

    @Test
    fun `a Dates with local date time is valid`() {
        Dates(offsetDateTime, offsetDateTime, offsetDateTime, offsetDateTime)
    }

    @Test
    fun `a Dates with local date is valid`() {
        Dates(localDate, localDate, localDate, localDate)
    }

    @Test
    fun `a Dates with year month is valid`() {
        Dates(yearMonth, yearMonth, yearMonth, yearMonth)
    }

    @Test
    fun `a Dates with year is valid`() {
        Dates(year, year, year, year)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a created of unknown is invalid`() {
        Dates(DateFormat.UNKNOWN, offsetDateTime, offsetDateTime, offsetDateTime)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a created of not applicable is invalid`() {
        Dates(DateFormat.NOT_APPLICABLE, offsetDateTime, offsetDateTime, offsetDateTime)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a posted of unknown is invalid`() {
        Dates(offsetDateTime, DateFormat.UNKNOWN, offsetDateTime, offsetDateTime)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a posted of not applicable is invalid`() {
        Dates(offsetDateTime, DateFormat.NOT_APPLICABLE, offsetDateTime, offsetDateTime)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a validTil of unknown is invalid`() {
        Dates(offsetDateTime, offsetDateTime, DateFormat.UNKNOWN, offsetDateTime)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a validTil of not applicable is invalid`() {
        Dates(offsetDateTime, offsetDateTime, DateFormat.NOT_APPLICABLE, offsetDateTime)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a infoCutOff of unknown is invalid`() {
        Dates(offsetDateTime, offsetDateTime, offsetDateTime, DateFormat.UNKNOWN)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Dates with a infoCutOff of not applicable is invalid`() {
        Dates(offsetDateTime, offsetDateTime, offsetDateTime, DateFormat.NOT_APPLICABLE)
    }
}
