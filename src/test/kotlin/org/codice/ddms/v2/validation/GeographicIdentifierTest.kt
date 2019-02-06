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
