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
import org.codice.ddms.v2.summary.TemporalCoverage
import org.junit.Test
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.Year
import java.time.YearMonth

class TemporalCoverageTest {
    @Test
    fun `a TemporalCoverage with default arguments is valid`() {
        TemporalCoverage()
    }

    @Test
    fun `a start and end date of not applicable is valid`() {
        TemporalCoverage("Not Applicable", DateFormat.NOT_APPLICABLE, DateFormat.NOT_APPLICABLE)
    }

    @Test
    fun `a start and end date with OffsetDateTime format is valid`() {
        val date = OffsetDateTime.now().toString()
        TemporalCoverage("Offset Date Time", date, date)
    }

    @Test
    fun `a start and end date with LocalDate format is valid`() {
        val date = LocalDate.now().toString()
        TemporalCoverage("Local Date", date, date)
    }

    @Test
    fun `a start and end date with YearMonth format is valid`() {
        val date = YearMonth.now().toString()
        TemporalCoverage("Year Month", date, date)
    }

    @Test
    fun `a start and end date with Year format is valid`() {
        val date = Year.now().toString()
        TemporalCoverage("Year", date, date)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a start and end date with a string of characters is invalid`() {
        TemporalCoverage("String of Character", "abc", "def")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a start and end date with a string of numbers that isn't a date format is invalid`() {
        TemporalCoverage("String of Numbers", "123", "456")
    }
}
