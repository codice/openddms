/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary.geospatial

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
        val result = GeographicIdentifierBuilder.geographicIdentifier {
            names(geographicIdentifier.names)
            regions(geographicIdentifier.regions)
            countryCodes(geographicIdentifier.countryCodes)
            facilityIdentifiers(geographicIdentifier.facilityIdentifiers)
        }

        assertThat(result, equalTo(geographicIdentifier))
    }
}
