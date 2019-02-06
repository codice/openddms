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
import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes

class PointBuilder : Builder<Point> {
    companion object {
        fun point(init: PointBuilder.() -> Unit) = PointBuilder().apply(init).build()
    }

    private var id: String = ""
    private lateinit var position: Position
    private var srsAttributes: SrsAttributes = SrsAttributes()

    fun id(id: String): PointBuilder {
        this.id = id
        return this
    }

    fun position(position: Position): PointBuilder {
        this.position = position
        return this
    }

    fun position(init: PositionBuilder.() -> Unit): PointBuilder {
        position = PositionBuilder().apply(init).build()
        return this
    }

    fun srsAttributes(srsAttributes: SrsAttributes): PointBuilder {
        this.srsAttributes = srsAttributes
        return this
    }

    fun srsAttributes(init: SrsAttributesBuilder.() -> Unit): PointBuilder {
        this.srsAttributes = SrsAttributesBuilder().apply(init).build()
        return this
    }

    override fun build(): Point = Point(id, position, srsAttributes)
}
