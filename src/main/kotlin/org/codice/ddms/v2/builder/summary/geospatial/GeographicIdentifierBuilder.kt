/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary.geospatial

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.FacilityIdentifier
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier

// Builders are pretty complex objects, any break up would be artificial and not useful
@Suppress("TooManyFunctions")
class GeographicIdentifierBuilder : Builder<GeographicIdentifier> {
    companion object {
        fun geographicIdentifier(init: GeographicIdentifierBuilder.() -> Unit) =
                GeographicIdentifierBuilder().apply(init).build()
    }

    private val names: ArrayList<String> = arrayListOf()
    private val regions: ArrayList<String> = arrayListOf()
    private val countryCodes: ArrayList<CountryCode> = arrayListOf()
    private val facilityIdentifiers: ArrayList<FacilityIdentifier> = arrayListOf()

    fun names(vararg name: String): GeographicIdentifierBuilder {
        names.addAll(name)
        return this
    }

    fun names(name: List<String>): GeographicIdentifierBuilder {
        names.addAll(name)
        return this
    }

    fun regions(vararg region: String): GeographicIdentifierBuilder {
        regions.addAll(region)
        return this
    }

    fun regions(region: List<String>): GeographicIdentifierBuilder {
        regions.addAll(region)
        return this
    }

    fun countryCodes(vararg countryCode: CountryCode): GeographicIdentifierBuilder {
        countryCodes.addAll(countryCode)
        return this
    }

    fun countryCodes(countryCode: List<CountryCode>): GeographicIdentifierBuilder {
        countryCodes.addAll(countryCode)
        return this
    }

    fun countryCode(qualifier: String, value: String) = countryCodes(CountryCode(qualifier, value))

    fun facilityIdentifiers(vararg facilityIdentifier: FacilityIdentifier): GeographicIdentifierBuilder {
        facilityIdentifiers.addAll(facilityIdentifier)
        return this
    }

    fun facilityIdentifiers(facilityIdentifier: List<FacilityIdentifier>): GeographicIdentifierBuilder {
        facilityIdentifiers.addAll(facilityIdentifier)
        return this
    }

    fun facilityIdentifier(beNumber: String, oSuffix: String) =
            facilityIdentifiers(FacilityIdentifier(beNumber, oSuffix))

    override fun build() = GeographicIdentifier(names,
            regions,
            countryCodes,
            facilityIdentifiers)
}
