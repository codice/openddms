/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.Year
import java.time.YearMonth

class DdmsDateTest {
    @Test
    fun `OffsetDateTime is valid`() {
        val date = OffsetDateTime.now()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `OffsetDateTime string is valid`() {
        val date = OffsetDateTime.now().toString()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `LocalDateTime is valid`() {
        val date = LocalDateTime.now()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `LocalDateTime string is valid`() {
        val date = LocalDateTime.now().toString()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `LocalDate is valid`() {
        val date = LocalDate.now()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `LocalDate string is valid`() {
        val date = LocalDate.now().toString()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `YearMonth is valid`() {
        val date = YearMonth.now()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `YearMonth string is valid`() {
        val date = YearMonth.now().toString()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `Year is valid`() {
        val date = Year.now()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `Year string is valid`() {
        val date = Year.now().toString()
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `Unknown is valid`() {
        val date = "Unknown"
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `Not Applicable is valid`() {
        val date = "Not Applicable"
        assertThat(DdmsDate.isValid(date), `is`(true))
    }

    @Test
    fun `Gibberish is not valid`() {
        val date = "Hello, World!"
        assertThat(DdmsDate.isValid(date), `is`(false))
    }

    @Test
    fun `Two DdmsDates with the same date are equal`() {
        val now = OffsetDateTime.now()
        val date1 = DdmsDate(now)
        val date2 = DdmsDate(now)
        assertThat(date1, equalTo(date2))
    }

    @Test(expected = IllegalStateException::class)
    fun `Unknown DdmsDate should throw an exception upon getting raw temporal accessor`() {
        val date = DdmsDate("Unknown")
        date.toRawTemporalAccessor()
    }

    @Test(expected = IllegalStateException::class)
    fun `Not Applicable DdmsDate should throw an exception upon getting raw temporal accessor`() {
        val date = DdmsDate("Not Applicable")
        date.toRawTemporalAccessor()
    }

    @Test
    fun `Unknown DdmsDate should be Unknown`() {
        val date = DdmsDate("Unknown")
        assertThat(date.isUnknown(), equalTo(true))
    }

    @Test
    fun `Unknown DdmsDate should not be Not Applicable`() {
        val date = DdmsDate("Unknown")
        assertThat(date.isNotApplicable(), equalTo(false))
    }

    @Test
    fun `Not Applicable DdmsDate should be Not Applicable`() {
        val date = DdmsDate("Not Applicable")
        assertThat(date.isNotApplicable(), equalTo(true))
    }

    @Test
    fun `Not Applicable DdmsDate should not be Unknown`() {
        val date = DdmsDate("Not Applicable")
        assertThat(date.isUnknown(), equalTo(false))
    }

    @Test
    fun `Sanity check for UNKNOWN date`() {
        val date = DdmsDate("Unknown")
        assertThat(date, `is`(DdmsDate.unknown))
    }

    @Test
    fun `Sanity check for NOT APPLICABLE date`() {
        val date = DdmsDate("Not Applicable")
        assertThat(date, `is`(DdmsDate.notApplicable))
    }
}
