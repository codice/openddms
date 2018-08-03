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

import org.codice.ddms.Builder
import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Position

fun linearRing(init: LinearRingBuilder.() -> Unit) =
        LinearRingBuilder().apply(init).build()

class LinearRingBuilder : Builder<LinearRing> {
    private val positions: ArrayList<Position> = arrayListOf()

    fun positions(vararg pos: Position): LinearRingBuilder {
        positions.addAll(pos)
        return this
    }

    fun positions(pos: List<Position>): LinearRingBuilder {
        positions.addAll(pos)
        return this
    }

    fun position(init: PositionBuilder.() -> Unit): LinearRingBuilder {
        val position = PositionBuilder().apply(init).build()
        return positions(position)
    }

    override fun build(): LinearRing = LinearRing(positions)
}
