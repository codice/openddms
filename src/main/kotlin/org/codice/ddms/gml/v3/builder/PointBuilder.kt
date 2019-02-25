/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
