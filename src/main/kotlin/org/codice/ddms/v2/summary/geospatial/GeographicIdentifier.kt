/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

/**
 * A wrapper for an identifier or reference to an identifier that describes a geographic extent using a name or other
 * identifier.
 *
 * Must contain at least one [name][String], or [region][String], or [CountryCode] or [FacilityIdentifier].
 *
 * @param names The names of a place of interest, other than a country, region, or facility.
 * @param regions The names of a sub-national or transnational geographic or geopolitical region that is a subject of
 * the resource.
 * @param countryCodes The [country codes][CountryCode] that the country goes by.
 * @param facilityIdentifiers The [facility identifiers][FacilityIdentifier] applicable to the location.
 * @throws IllegalArgumentException If [names] or [regions] or [countryCodes] or [facilityIdentifiers] are all empty.
 */
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
