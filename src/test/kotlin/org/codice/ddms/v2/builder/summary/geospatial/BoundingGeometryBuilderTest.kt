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
package org.codice.ddms.v2.builder.summary.geospatial

import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class BoundingGeometryBuilderTest {
    private val polygon = Polygon("id", LinearRing(listOf(
            Position(listOf()),
            Position(listOf()),
            Position(listOf()),
            Position(listOf())
    )), SrsAttributes("wgs84"))

    private val point = Point("id", Position(listOf()), SrsAttributes("wgs84"))

    private val boundingGeometry = BoundingGeometry(listOf(polygon), listOf(point))

    @Test
    fun `building with methods`() {
        val result = BoundingGeometryBuilder()
                .polygons(polygon)
                .points(point)
                .build()

        assertThat(result, equalTo(boundingGeometry))
    }

    @Test
    fun `building with polygon lambda`() {
        val result = BoundingGeometryBuilder()
                .polygon {
                    id(polygon.id)
                    exterior(polygon.exterior)
                    srsAttributes(polygon.srsAttributes)
                }
                .points(point)
                .build()

        assertThat(result, equalTo(boundingGeometry))
    }

    @Test
    fun `building with point lambda`() {
        val result = BoundingGeometryBuilder()
                .polygons(polygon)
                .point {
                    id(point.id)
                    position(point.position)
                    srsAttributes(point.srsAttributes)
                }
                .build()

        assertThat(result, equalTo(boundingGeometry))
    }

    @Test
    fun `building with lambda`() {
        val result = BoundingGeometryBuilder.boundingGeometry {
            polygons(polygon)
            points(point)
        }

        assertThat(result, equalTo(boundingGeometry))
    }
}
