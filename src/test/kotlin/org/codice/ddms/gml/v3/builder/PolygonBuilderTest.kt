/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
        val result = PolygonBuilder.polygon {
            id(polygon.id)
            exterior(polygon.exterior)
            srsAttributes(polygon.srsAttributes)
        }

        assertThat(result, equalTo(polygon))
    }
}
