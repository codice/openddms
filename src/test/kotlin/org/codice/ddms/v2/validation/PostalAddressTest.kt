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

import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.Province
import org.codice.ddms.v2.summary.geospatial.State
import org.junit.Test

class PostalAddressTest {
    @Test
    fun `a PostalAddress with default values is valid`() {
        PostalAddress()
    }

    @Test
    fun `a PostalAddress with a street is valid`() {
        PostalAddress(listOf("street"))
    }

    @Test
    fun `a PostalAddress with a city is valid`() {
        PostalAddress(city = "city")
    }

    @Test
    fun `a PostalAddress with a state is valid`() {
        PostalAddress(stateOrProvince = State("state"))
    }

    @Test
    fun `a PostalAddress with a province is valid`() {
        PostalAddress(stateOrProvince = Province("province"))
    }

    @Test
    fun `a PostalAddress with a postalCode is valid`() {
        PostalAddress(postalCode = "postalCode")
    }

    @Test
    fun `a PostalAddress with a countryCode is valid`() {
        PostalAddress(countryCode = CountryCode("qualifier", "value"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a PostalAddress with more than six streets is invalid`() {
        PostalAddress(street = (1..10).map { "street" })
    }
}
