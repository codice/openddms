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

data class PostalAddress(
    val street: List<String> = emptyList(),
    val city: String = "",
    val stateOrProvince: StateProvince? = null,
    val postalCode: String = "",
    val countryCode: CountryCode = CountryCode()
) {
    init {
        require(street.size <= 6) {
            "ddms:postalAddress street must contain between zero and six elements"
        }
    }
}

interface StateProvince {
    val value: String
}

data class State(override val value: String) : StateProvince
data class Province(override val value: String) : StateProvince
