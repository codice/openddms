/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary.geospatial

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.builder.PointBuilder
import org.codice.ddms.gml.v3.builder.PolygonBuilder

class BoundingGeometryBuilder : Builder<BoundingGeometry> {
    companion object {
        fun boundingGeometry(init: BoundingGeometryBuilder.() -> Unit) =
                BoundingGeometryBuilder().apply(init).build()
    }

    private val polygons: ArrayList<Polygon> = arrayListOf()
    private val points: ArrayList<Point> = arrayListOf()

    fun polygons(vararg polygon: Polygon): BoundingGeometryBuilder {
        polygons.addAll(polygon)
        return this
    }

    fun polygons(polygon: List<Polygon>): BoundingGeometryBuilder {
        polygons.addAll(polygon)
        return this
    }

    fun polygon(init: PolygonBuilder.() -> Unit): BoundingGeometryBuilder {
        val polygon = PolygonBuilder().apply(init).build()
        return polygons(polygon)
    }

    fun points(vararg point: Point): BoundingGeometryBuilder {
        points.addAll(point)
        return this
    }

    fun points(point: List<Point>): BoundingGeometryBuilder {
        points.addAll(point)
        return this
    }

    fun point(init: PointBuilder.() -> Unit): BoundingGeometryBuilder {
        val point = PointBuilder().apply(init).build()
        return points(point)
    }

    override fun build(): BoundingGeometry = BoundingGeometry(polygons, points)
}
