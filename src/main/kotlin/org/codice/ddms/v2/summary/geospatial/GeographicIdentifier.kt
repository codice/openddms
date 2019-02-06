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
package org.codice.ddms.v2.summary.geospatial

data class GeographicIdentifier(
    val names: List<String>,
    val regions: List<String>,
    val countryCodes: List<CountryCode>,
    val facilityIdentifiers: List<FacilityIdentifier>
) {
    init {
        require(names.isNotEmpty() || regions.isNotEmpty() ||
                countryCodes.isNotEmpty() || facilityIdentifiers.isNotEmpty()) {
            "ddms:geographicIdentifier must contain at least one of the following: ddms:name, ddms:region, " +
                    "ddms:countryCode, ddms:facilityIdentifier"
        }
    }
}
