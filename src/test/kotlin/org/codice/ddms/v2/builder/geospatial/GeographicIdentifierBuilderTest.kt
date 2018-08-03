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
package org.codice.ddms.v2.builder.geospatial

import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.FacilityIdentifier
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class GeographicIdentifierBuilderTest {
    private val geographicIdentifier = GeographicIdentifier(listOf("names"),
            listOf("regions"),
            listOf(CountryCode("qualifier", "value")),
            listOf(FacilityIdentifier("beNumber", "oSuffix")))

    @Test
    fun `building with methods`() {
        val result = GeographicIdentifierBuilder()
                .names(geographicIdentifier.names)
                .regions(geographicIdentifier.regions)
                .countryCodes(geographicIdentifier.countryCodes)
                .facilityIdentifiers(geographicIdentifier.facilityIdentifiers)
                .build()

        assertThat(result, equalTo(geographicIdentifier))
    }

    @Test
    fun `building with country code method`() {
        val result = GeographicIdentifierBuilder()
                .names(geographicIdentifier.names)
                .regions(geographicIdentifier.regions)
                .countryCode("qualifier", "value")
                .facilityIdentifiers(geographicIdentifier.facilityIdentifiers)
                .build()

        assertThat(result, equalTo(geographicIdentifier))
    }

    @Test
    fun `building with facility identifier method`() {
        val result = GeographicIdentifierBuilder()
                .names(geographicIdentifier.names)
                .regions(geographicIdentifier.regions)
                .countryCodes(geographicIdentifier.countryCodes)
                .facilityIdentifier("beNumber", "oSuffix")
                .build()

        assertThat(result, equalTo(geographicIdentifier))
    }

    @Test
    fun `building with lambda`() {
        val result = geographicIdentifier {
            names(geographicIdentifier.names)
            regions(geographicIdentifier.regions)
            countryCodes(geographicIdentifier.countryCodes)
            facilityIdentifiers(geographicIdentifier.facilityIdentifiers)
        }

        assertThat(result, equalTo(geographicIdentifier))
    }
}
