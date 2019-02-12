/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary.geospatial

import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.State
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PostalAddressBuilderTest {
    private val postalAddress = PostalAddress(listOf("streets"),
            "city",
            State("state"),
            "postalCode",
            CountryCode("qualifier", "value"))

    @Test
    fun `building with methods`() {
        val result = PostalAddressBuilder()
                .streets(postalAddress.street)
                .city(postalAddress.city)
                .state(postalAddress.stateOrProvince!!.value)
                .postalCode(postalAddress.postalCode)
                .countryCode(postalAddress.countryCode!!)
                .build()

        assertThat(result, equalTo(postalAddress))
    }

    @Test
    fun `building with province`() {
        val result = PostalAddressBuilder()
                .streets(postalAddress.street)
                .city(postalAddress.city)
                .province(postalAddress.stateOrProvince!!.value)
                .postalCode(postalAddress.postalCode)
                .countryCode(postalAddress.countryCode!!)
                .build()

        assertThat(result.stateOrProvince!!.value,
                equalTo(postalAddress.stateOrProvince!!.value))
    }

    @Test
    fun `building with country code`() {
        val result = PostalAddressBuilder()
                .streets(postalAddress.street)
                .city(postalAddress.city)
                .state(postalAddress.stateOrProvince!!.value)
                .postalCode(postalAddress.postalCode)
                .countryCode(postalAddress.countryCode!!.qualifier,
                        postalAddress.countryCode!!.value)
                .build()

        assertThat(result, equalTo(postalAddress))
    }

    @Test
    fun `building with lambda`() {
        val result = PostalAddressBuilder.postalAddress {
            streets(postalAddress.street)
            city(postalAddress.city)
            state(postalAddress.stateOrProvince!!.value)
            postalCode(postalAddress.postalCode)
            countryCode(postalAddress.countryCode!!)
        }

        assertThat(result, equalTo(postalAddress))
    }
}
