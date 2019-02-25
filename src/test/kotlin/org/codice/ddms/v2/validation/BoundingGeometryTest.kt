/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
