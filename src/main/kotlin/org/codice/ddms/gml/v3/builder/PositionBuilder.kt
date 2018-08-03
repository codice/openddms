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
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes

fun position(init: PositionBuilder.() -> Unit) = PositionBuilder().apply(init).build()

class PositionBuilder : Builder<Position> {
    private val points: ArrayList<Double> = arrayListOf()
    private var srsAttributes: SrsAttributes = SrsAttributes()

    fun points(vararg point: Double): PositionBuilder {
        points.addAll(point.toList())
        return this
    }

    fun points(point: List<Double>): PositionBuilder {
        points.addAll(point.toList())
        return this
    }

    fun srsAttributes(srsAttributes: SrsAttributes): PositionBuilder {
        this.srsAttributes = srsAttributes
        return this
    }

    fun srsAttributes(init: SrsAttributesBuilder.() -> Unit): PositionBuilder {
        this.srsAttributes = SrsAttributesBuilder().apply(init).build()
        return this
    }

    override fun build(): Position = Position(points, srsAttributes)
}
