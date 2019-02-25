/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Polygon

/**
 * A wrapper for expressing a geographic location as a point or polygon.
 *
 * Must have at least one element in [polygons] or [points].
 *
 * @param polygons The [polygons][Polygon] that are described by this geographic extent.
 * @param points The [points][Point] that are described by this geographic extent.
 * @throws IllegalArgumentException If [polygons] and [points] are empty.
 */
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
