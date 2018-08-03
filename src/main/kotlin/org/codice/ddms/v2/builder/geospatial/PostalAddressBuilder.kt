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

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.Province
import org.codice.ddms.v2.summary.geospatial.State
import org.codice.ddms.v2.summary.geospatial.StateProvince

fun postalAddress(init: PostalAddressBuilder.() -> Unit) =
        PostalAddressBuilder().apply(init).build()

class PostalAddressBuilder : Builder<PostalAddress> {
    private val streets: ArrayList<String> = arrayListOf()
    private var city: String = ""
    private var stateProvince: StateProvince? = null
    private var postalCode: String = ""
    private var countryCode: CountryCode = CountryCode()

    fun streets(vararg street: String): PostalAddressBuilder {
        streets.addAll(street)
        return this
    }

    fun streets(street: List<String>): PostalAddressBuilder {
        streets.addAll(street)
        return this
    }

    fun city(city: String): PostalAddressBuilder {
        this.city = city
        return this
    }

    fun state(code: String): PostalAddressBuilder {
        stateProvince = State(code)
        return this
    }

    fun province(code: String): PostalAddressBuilder {
        stateProvince = Province(code)
        return this
    }

    fun postalCode(postalCode: String): PostalAddressBuilder {
        this.postalCode = postalCode
        return this
    }

    fun countryCode(countryCode: CountryCode): PostalAddressBuilder {
        this.countryCode = countryCode
        return this
    }

    fun countryCode(qualifier: String, value: String): PostalAddressBuilder {
        countryCode = CountryCode(qualifier, value)
        return this
    }

    override fun build(): PostalAddress = PostalAddress(streets,
            city,
            stateProvince,
            postalCode,
            countryCode)
}
