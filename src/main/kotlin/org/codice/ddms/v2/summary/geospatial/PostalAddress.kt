/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

private const val MAX_STREET_SIZE = 6

/**
 * Describes a postal address
 *
 * [street] must be between zero and siz elements.
 *
 * @param street The parts of a postal address, such as street number, name, PO box, etc. Must be between zero and six
 * elements.
 * @param city Name of the city.
 * @param stateOrProvince The state or province.
 * @param postalCode A mailing code designation such as ZIP or postcode.
 * @param countryCode The country code of that address.
 * @throws IllegalArgumentException If [street] is not between zero and six elements.
 */
data class PostalAddress(
    val street: List<String> = emptyList(),
    val city: String = "",
    val stateOrProvince: StateProvince? = null,
    val postalCode: String = "",
    val countryCode: CountryCode? = null
) {
    init {
        require(street.size in 0..MAX_STREET_SIZE) {
            "ddms:postalAddress street must contain between zero and six elements"
        }
    }
}

interface StateProvince {
    val value: String
}

data class State(override val value: String) : StateProvince
data class Province(override val value: String) : StateProvince
