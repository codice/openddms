/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.Builder
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes

class PositionBuilder : Builder<Position> {
    companion object {
        fun position(init: PositionBuilder.() -> Unit) = PositionBuilder().apply(init).build()
    }

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
