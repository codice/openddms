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
package org.codice.ddms.v2.builder

import org.codice.ddms.v2.summary.GeospatialCoverage
import org.codice.ddms.v2.summary.geospatial.BoundingBox
import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.codice.ddms.v2.summary.geospatial.VerticalDistance
import org.codice.ddms.v2.summary.geospatial.VerticalExtent
import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class GeospatialCoverageBuilderTest {
    private val identifier = GeographicIdentifier(listOf("names"), listOf(), listOf(), listOf())
    private val boundingBox = BoundingBox(0.0, 0.0, 0.0, 0.0)
    private val boundingGeometry = BoundingGeometry(listOf(),
            listOf(Point("id", Position(listOf()), SrsAttributes("wgs84"))))
    private val postalAddress = PostalAddress(listOf("street"))
    private val verticalExtent = VerticalExtent(UnitOfMeasure.Foot, Datum.MSL,
            VerticalDistance(0.0), VerticalDistance(10.0))

    private val geospatialCoverage = GeospatialCoverage(listOf(identifier),
            listOf(boundingBox),
            listOf(boundingGeometry),
            listOf(postalAddress),
            listOf(verticalExtent))

    @Test
    fun `building with methods`() {
        val result = GeospatialCoverageBuilder()
                .geographicIdentifiers(identifier)
                .boundingBoxes(boundingBox)
                .boundingGeometries(boundingGeometry)
                .postalAddresses(postalAddress)
                .verticalExtents(verticalExtent)
                .build()

        assertThat(result, equalTo(geospatialCoverage))
    }

    @Test
    fun `building with geographic identifier lambda`() {
        val result = GeospatialCoverageBuilder()
                .geographicIdentifier {
                    names(identifier.names)
                }
                .boundingBoxes(boundingBox)
                .boundingGeometries(boundingGeometry)
                .postalAddresses(postalAddress)
                .verticalExtents(verticalExtent)
                .build()

        assertThat(result, equalTo(geospatialCoverage))
    }

    @Test
    fun `building with bounding box method`() {
        val result = GeospatialCoverageBuilder()
                .geographicIdentifiers(identifier)
                .boundingBox(0.0, 0.0, 0.0, 0.0)
                .boundingGeometries(boundingGeometry)
                .postalAddresses(postalAddress)
                .verticalExtents(verticalExtent)
                .build()

        assertThat(result, equalTo(geospatialCoverage))
    }

    @Test
    fun `building with bounding geometry lambda`() {
        val result = GeospatialCoverageBuilder()
                .geographicIdentifiers(identifier)
                .boundingBoxes(boundingBox)
                .boundingGeometry {
                    polygons(boundingGeometry.polygons)
                    points(boundingGeometry.points)
                }
                .postalAddresses(postalAddress)
                .verticalExtents(verticalExtent)
                .build()

        assertThat(result, equalTo(geospatialCoverage))
    }

    @Test
    fun `building with postal address lambda`() {
        val result = GeospatialCoverageBuilder()
                .geographicIdentifiers(identifier)
                .boundingBoxes(boundingBox)
                .boundingGeometries(boundingGeometry)
                .postalAddress {
                    streets(postalAddress.street)
                }
                .verticalExtents(verticalExtent)
                .build()

        assertThat(result, equalTo(geospatialCoverage))
    }

    @Test
    fun `building with vertical extent lambda`() {
        val result = GeospatialCoverageBuilder()
                .geographicIdentifiers(identifier)
                .boundingBoxes(boundingBox)
                .boundingGeometries(boundingGeometry)
                .postalAddresses(postalAddress)
                .verticalExtent {
                    unit(verticalExtent.unit)
                    datum(verticalExtent.datum)
                    minimum(verticalExtent.minVerticalExtent)
                    maximum(verticalExtent.maxVerticalExtentValue)
                }
                .build()

        assertThat(result, equalTo(geospatialCoverage))
    }

    @Test
    fun `building with lambda`() {
        val result = geospatialCoverage {
            geographicIdentifiers(identifier)
            boundingBoxes(boundingBox)
            boundingGeometries(boundingGeometry)
            postalAddresses(postalAddress)
            verticalExtents(verticalExtent)
        }

        assertThat(result, equalTo(geospatialCoverage))
    }
}
