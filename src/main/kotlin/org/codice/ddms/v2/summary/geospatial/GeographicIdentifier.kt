/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
