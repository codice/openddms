/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.FacilityIdentifier
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier
import org.junit.Test

class GeographicIdentifierTest {
    @Test
    fun `a GeographicIdentifier with a name is valid`() {
        GeographicIdentifier(listOf("name"), emptyList(), emptyList(), emptyList())
    }

    @Test
    fun `a GeographicIdentifier with a region is valid`() {
        GeographicIdentifier(emptyList(), listOf("region"), emptyList(), emptyList())
    }

    @Test
    fun `a GeographicIdentifier with a CountryCode is valid`() {
        GeographicIdentifier(emptyList(), emptyList(), listOf(CountryCode("qualifier", "value")), emptyList())
    }

    @Test
    fun `a GeographicIdentifier with a FacilityIdentifier is valid`() {
        GeographicIdentifier(emptyList(), emptyList(), emptyList(), listOf(FacilityIdentifier("beNumber")))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a GeographicIdentifier with nothing is invalid`() {
        GeographicIdentifier(emptyList(), emptyList(), emptyList(), emptyList())
    }
}
