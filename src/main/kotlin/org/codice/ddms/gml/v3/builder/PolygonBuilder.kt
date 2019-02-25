/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.Builder
import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.SrsAttributes

class PolygonBuilder : Builder<Polygon> {
    companion object {
        fun polygon(init: PolygonBuilder.() -> Unit) = PolygonBuilder().apply(init).build()
    }

    private var id: String = ""
    private lateinit var exterior: LinearRing
    private var srsAttributes: SrsAttributes = SrsAttributes()

    fun id(id: String): PolygonBuilder {
        this.id = id
        return this
    }

    fun exterior(exterior: LinearRing): PolygonBuilder {
        this.exterior = exterior
        return this
    }

    fun exterior(init: LinearRingBuilder.() -> Unit): PolygonBuilder {
        exterior = LinearRingBuilder().apply(init).build()
        return this
    }

    fun srsAttributes(srsAttributes: SrsAttributes): PolygonBuilder {
        this.srsAttributes = srsAttributes
        return this
    }

    fun srsAttributes(init: SrsAttributesBuilder.() -> Unit): PolygonBuilder {
        srsAttributes = SrsAttributesBuilder().apply(init).build()
        return this
    }

    override fun build(): Polygon = Polygon(id, exterior, srsAttributes)
}
