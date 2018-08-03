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
package org.codice.ddms.v2.builder

import org.codice.ddms.DateFormat
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.Date

class DateFormatTest {

    @Test
    fun `OffsetDateTime is valid`() {
        val offsetDateTime = OffsetDateTime.now().toString()
        assertThat(DateFormat.isValid(offsetDateTime), `is`(true))
    }

    @Test
    fun `LocalDate is valid`() {
        val localDate = LocalDate.now().toString()
        assertThat(DateFormat.isValid(localDate), `is`(true))
    }

    @Test
    fun `yyyy-mm is valid`() {
        val date = "2018-02"
        assertThat(DateFormat.isValid(date), `is`(true))
    }

    @Test
    fun `yyyy is valid`() {
        val date = "2018"
        assertThat(DateFormat.isValid(date), `is`(true))
    }

    @Test
    fun `unknown is valid when applicable`() {
        assertThat(DateFormat.isValid(DateFormat.UNKNOWN, true), `is`(true))
    }

    @Test
    fun `not applicable is valid when applicable`() {
        assertThat(DateFormat.isValid(DateFormat.NOT_APPLICABLE, true), `is`(true))
    }

    @Test
    fun `unknown is not valid when not applicable`() {
        assertThat(DateFormat.isValid(DateFormat.UNKNOWN), `is`(false))
    }

    @Test
    fun `not applicable is not valid when not applicable`() {
        assertThat(DateFormat.isValid(DateFormat.NOT_APPLICABLE), `is`(false))
    }

    @Test
    fun `bad date is not valid`() {
        assertThat(DateFormat.isValid("bad date"), `is`(false))
    }

    @Test
    fun `bad date is not valid when unknown is allowed`() {
        assertThat(DateFormat.isValid("bad date", true), `is`(false))
    }

    @Test
    fun `OffsetDateTime string can be parsed to Date`() {
        val offsetDateTime = OffsetDateTime.now()
        val string = offsetDateTime.toString()

        val result = DateFormat.parse(string)
        val date = Date.from(offsetDateTime.toInstant())
        assertThat(result, equalTo(date))
    }

    @Test
    fun `LocalDate string can be parsed to Date`() {
        val localDate = LocalDate.now()
        val string = localDate.toString()

        val result = DateFormat.parse(string)
        val date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        assertThat(result, equalTo(date))
    }

    @Test
    fun `yyyy-MM string can be parsed to Date`() {
        val string = "2018-02"

        val result = DateFormat.parse(string)
        val date = SimpleDateFormat("yyyy-MM").parse(string)
        assertThat(result, equalTo(date))
    }

    @Test
    fun `yyyy string can be parsed to Date`() {
        val string = "2018"

        val result = DateFormat.parse(string)
        val date = SimpleDateFormat("yyyy").parse(string)
        assertThat(result, equalTo(date))
    }

    @Test()
    fun `bad date can not be parsed to Date`() {
        assertThat(DateFormat.parse("bad date"), nullValue())
    }

    @Test
    fun `OffsetDateTime without nano second is valid`() {
        val date = "2009-06-01T17:20:52-07:00"
        assertThat(DateFormat.isValid(date), `is`(true))
    }

    @Test
    fun `OffsetDateTime without nano second can be parsed to Date`() {
        val dateString = "2009-06-01T17:20:52-07:00"
        val offsetDate = OffsetDateTime.parse(dateString)

        val result = DateFormat.parse(dateString)
        val date = Date.from(offsetDate.toInstant())
        assertThat(result, equalTo(date))
    }
}
