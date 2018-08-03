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
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PolygonBuilderTest {
    private val polygon = Polygon("id",
            LinearRing(listOf(Position(listOf()),
                    Position(listOf()),
                    Position(listOf()),
                    Position(listOf()))),
            SrsAttributes("wgs84"))

    @Test
    fun `building with methods`() {
        val result = PolygonBuilder()
                .id(polygon.id)
                .exterior(polygon.exterior)
                .srsAttributes(polygon.srsAttributes)
                .build()

        assertThat(result, equalTo(polygon))
    }

    @Test
    fun `building with linear ring lambdas`() {
        val result = PolygonBuilder()
                .id(polygon.id)
                .exterior {
                    positions(polygon.exterior.positions)
                }
                .srsAttributes(polygon.srsAttributes)
                .build()

        assertThat(result, equalTo(polygon))
    }

    @Test
    fun `building with srsAttributes lambdas`() {
        val result = PolygonBuilder()
                .id(polygon.id)
                .exterior(polygon.exterior)
                .srsAttributes {
                    srsName(polygon.srsAttributes.srsName)
                }
                .build()

        assertThat(result, equalTo(polygon))
    }

    @Test
    fun `building with lambda`() {
        val result = polygon {
            id(polygon.id)
            exterior(polygon.exterior)
            srsAttributes(polygon.srsAttributes)
        }

        assertThat(result, equalTo(polygon))
    }
}
