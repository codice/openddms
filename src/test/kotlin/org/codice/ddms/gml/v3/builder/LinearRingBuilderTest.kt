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
import org.codice.ddms.gml.v3.Position
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class LinearRingBuilderTest {
    private val positions = listOf(Position(listOf(0.0, 0.0)),
            Position(listOf(1.0, 1.0)),
            Position(listOf(2.0, 2.0)),
            Position(listOf(3.0, 3.0)))
    private val linearRing = LinearRing(positions)

    @Test
    fun `building with vararg positions`() {
        val result = LinearRingBuilder()
                .positions(positions)
                .build()

        assertThat(result, equalTo(linearRing))
    }

    @Test
    fun `building with position builder lambda`() {
        val builder = LinearRingBuilder()
        for (position in positions) {
            builder.position {
                points(position.points)
            }
        }

        val result = builder.build()

        assertThat(result, equalTo(linearRing))
    }

    @Test
    fun `building with lambda`() {
        val result = LinearRingBuilder.linearRing {
            positions.map {
                position {
                    points(it.points)
                }
            }
        }

        assertThat(result, equalTo(linearRing))
    }
}
