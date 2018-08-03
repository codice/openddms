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
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.junit.Test

class BoundingGeometryTest {
    private val polygon = Polygon("polygon", LinearRing(listOf(Position(emptyList()), Position(emptyList()), Position(emptyList()), Position(emptyList()))), SrsAttributes("srsName"))
    private val point = Point("point", Position(emptyList()), SrsAttributes("srsName"))

    @Test
    fun `a BoundingGeometry with a Polygon is valid`() {
        BoundingGeometry(listOf(polygon), emptyList())
    }

    @Test
    fun `a BoundingGeometry with a Point is valid`() {
        BoundingGeometry(emptyList(), listOf(point))
    }

    @Test
    fun `a BoundingGeometry with a Polygon and a Point is valid`() {
        BoundingGeometry(listOf(polygon), listOf(point))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a BoundingGeometry without a Polygon or Point is invalid`() {
        BoundingGeometry(emptyList(), emptyList())
    }
}
