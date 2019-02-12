/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PositionBuilderTest {
    private val position = Position(listOf(0.0, 0.0), SrsAttributes("wgs84"))

    @Test
    fun `building with methods`() {
        val result = PositionBuilder()
                .points(position.points)
                .srsAttributes(position.srsAttributes)
                .build()

        assertThat(result, equalTo(position))
    }

    @Test
    fun `building srs attributes lambda`() {
        val result = PositionBuilder()
                .points(position.points)
                .srsAttributes {
                    srsName(position.srsAttributes.srsName)
                }
                .build()

        assertThat(result, equalTo(position))
    }

    @Test
    fun `building with lambda`() {
        val result = PositionBuilder.position {
            points(position.points)
            srsAttributes(position.srsAttributes)
        }

        assertThat(result, equalTo(position))
    }
}
