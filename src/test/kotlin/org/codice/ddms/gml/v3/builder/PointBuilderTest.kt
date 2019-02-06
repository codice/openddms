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

import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PointBuilderTest {
    private val point = Point("point", Position(listOf(0.0, 0.0)), SrsAttributes("wgs84"))

    @Test
    fun `building with methods`() {
        val result = PointBuilder()
                .id(point.id)
                .position(point.position)
                .srsAttributes(point.srsAttributes)
                .build()

        assertThat(result, equalTo(point))
    }

    @Test
    fun `building with position lambdas`() {
        val result = PointBuilder()
                .id(point.id)
                .position {
                    points(point.position.points)
                }
                .srsAttributes(point.srsAttributes)
                .build()

        assertThat(result, equalTo(point))
    }

    @Test
    fun `building with srsAttributes lambdas`() {
        val result = PointBuilder()
                .id(point.id)
                .position(point.position)
                .srsAttributes {
                    srsName(point.srsAttributes.srsName)
                }
                .build()

        assertThat(result, equalTo(point))
    }

    @Test
    fun `building with lambda`() {
        val result = PointBuilder.point {
            id(point.id)
            position(point.position)
            srsAttributes(point.srsAttributes)
        }

        assertThat(result, equalTo(point))
    }
}
