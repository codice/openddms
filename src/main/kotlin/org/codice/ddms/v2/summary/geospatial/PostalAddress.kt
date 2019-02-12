/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

private const val MAX_STREET_SIZE = 6

data class PostalAddress(
    val street: List<String> = emptyList(),
    val city: String = "",
    val stateOrProvince: StateProvince? = null,
    val postalCode: String = "",
    val countryCode: CountryCode? = null
) {
    init {
        require(street.size <= MAX_STREET_SIZE) {
            "ddms:postalAddress street must contain between zero and six elements"
        }
    }
}

interface StateProvince {
    val value: String
}

data class State(override val value: String) : StateProvince
data class Province(override val value: String) : StateProvince
