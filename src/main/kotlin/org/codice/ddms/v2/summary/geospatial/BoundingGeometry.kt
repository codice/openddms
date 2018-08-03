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

import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Polygon

data class BoundingGeometry(
    val polygons: List<Polygon>,
    val points: List<Point>
) {
    init {
        require(polygons.isNotEmpty() || points.isNotEmpty()) {
            "ddms:boundingGeometry must contain at least one of the following: gml:Polygon, gml:Point"
        }
    }
}
