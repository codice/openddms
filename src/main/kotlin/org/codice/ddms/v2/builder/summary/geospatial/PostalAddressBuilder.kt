/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary.geospatial

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.Province
import org.codice.ddms.v2.summary.geospatial.State
import org.codice.ddms.v2.summary.geospatial.StateProvince

class PostalAddressBuilder : Builder<PostalAddress> {
    companion object {
        fun postalAddress(init: PostalAddressBuilder.() -> Unit) =
                PostalAddressBuilder().apply(init).build()
    }

    private val streets: ArrayList<String> = arrayListOf()
    private var city: String = ""
    private var stateProvince: StateProvince? = null
    private var postalCode: String = ""
    private var countryCode: CountryCode? = null

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
