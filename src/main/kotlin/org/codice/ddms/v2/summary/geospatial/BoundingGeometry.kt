/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
