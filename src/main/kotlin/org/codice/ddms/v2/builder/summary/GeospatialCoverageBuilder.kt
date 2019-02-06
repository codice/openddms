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
package org.codice.ddms.v2.builder.summary

import org.codice.ddms.Builder
import org.codice.ddms.v2.builder.summary.geospatial.BoundingGeometryBuilder
import org.codice.ddms.v2.builder.summary.geospatial.GeographicIdentifierBuilder
import org.codice.ddms.v2.builder.summary.geospatial.PostalAddressBuilder
import org.codice.ddms.v2.builder.summary.geospatial.VerticalExtentBuilder
import org.codice.ddms.v2.summary.GeospatialCoverage
import org.codice.ddms.v2.summary.geospatial.BoundingBox
import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.VerticalExtent

@Suppress("TooManyFunctions") // TODO: Not sure how we can make this 'smaller'
class GeospatialCoverageBuilder : Builder<GeospatialCoverage> {
    companion object {
        fun geospatialCoverage(init: GeospatialCoverageBuilder.() -> Unit): GeospatialCoverage =
                GeospatialCoverageBuilder().apply(init).build()
    }

    private val geographicIdentifiers: ArrayList<GeographicIdentifier> = arrayListOf()
    private val boundingBoxes: ArrayList<BoundingBox> = arrayListOf()
    private val boundingGeometries: ArrayList<BoundingGeometry> = arrayListOf()
    private val postalAddresses: ArrayList<PostalAddress> = arrayListOf()
    private val verticalExtents: ArrayList<VerticalExtent> = arrayListOf()

    fun geographicIdentifiers(vararg geographicIdentifier: GeographicIdentifier): GeospatialCoverageBuilder {
        geographicIdentifiers.addAll(geographicIdentifier)
        return this
    }

    fun geographicIdentifiers(geographicIdentifier: List<GeographicIdentifier>): GeospatialCoverageBuilder {
        geographicIdentifiers.addAll(geographicIdentifier)
        return this
    }

    fun geographicIdentifier(init: GeographicIdentifierBuilder.() -> Unit): GeospatialCoverageBuilder {
        val geographicIdentifier = GeographicIdentifierBuilder().apply(init).build()
        return geographicIdentifiers(geographicIdentifier)
    }

    fun boundingBoxes(vararg boundingBox: BoundingBox): GeospatialCoverageBuilder {
        boundingBoxes.addAll(boundingBox)
        return this
    }

    fun boundingBoxes(boundingBox: List<BoundingBox>): GeospatialCoverageBuilder {
        boundingBoxes.addAll(boundingBox)
        return this
    }

    fun boundingBox(north: Double, south: Double, east: Double, west: Double) =
            boundingBoxes(BoundingBox(north, south, east, west))

    fun boundingGeometries(vararg boundingGeometry: BoundingGeometry): GeospatialCoverageBuilder {
        boundingGeometries.addAll(boundingGeometry)
        return this
    }

    fun boundingGeometries(boundingGeometry: List<BoundingGeometry>): GeospatialCoverageBuilder {
        boundingGeometries.addAll(boundingGeometry)
        return this
    }

    fun boundingGeometry(init: BoundingGeometryBuilder.() -> Unit): GeospatialCoverageBuilder {
        val boundingGeometry = BoundingGeometryBuilder().apply(init).build()
        return boundingGeometries(boundingGeometry)
    }

    fun postalAddresses(vararg postalAddress: PostalAddress): GeospatialCoverageBuilder {
        postalAddresses.addAll(postalAddress)
        return this
    }

    fun postalAddresses(postalAddress: List<PostalAddress>): GeospatialCoverageBuilder {
        postalAddresses.addAll(postalAddress)
        return this
    }

    fun postalAddress(init: PostalAddressBuilder.() -> Unit): GeospatialCoverageBuilder {
        val postalAddress = PostalAddressBuilder().apply(init).build()
        return postalAddresses(postalAddress)
    }

    fun verticalExtents(vararg verticalExtent: VerticalExtent): GeospatialCoverageBuilder {
        verticalExtents.addAll(verticalExtent)
        return this
    }

    fun verticalExtents(verticalExtent: List<VerticalExtent>): GeospatialCoverageBuilder {
        verticalExtents.addAll(verticalExtent)
        return this
    }

    fun verticalExtent(init: VerticalExtentBuilder.() -> Unit): GeospatialCoverageBuilder {
        val verticalExtent = VerticalExtentBuilder().apply(init).build()
        return verticalExtents(verticalExtent)
    }

    override fun build(): GeospatialCoverage = GeospatialCoverage(geographicIdentifiers,
            boundingBoxes,
            boundingGeometries,
            postalAddresses,
            verticalExtents)
}
