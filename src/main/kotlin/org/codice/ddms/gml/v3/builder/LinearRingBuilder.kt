/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.Builder
import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Position

class LinearRingBuilder : Builder<LinearRing> {
    companion object {
        fun linearRing(init: LinearRingBuilder.() -> Unit) =
                LinearRingBuilder().apply(init).build()
    }

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
