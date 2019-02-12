/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary.geospatial

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.codice.ddms.v2.summary.geospatial.VerticalDistance
import org.codice.ddms.v2.summary.geospatial.VerticalExtent

class VerticalExtentBuilder : Builder<VerticalExtent> {
    companion object {
        fun verticalExtent(init: VerticalExtentBuilder.() -> Unit) =
                VerticalExtentBuilder().apply(init).build()
    }

    private lateinit var unit: UnitOfMeasure
    private lateinit var datum: Datum
    private lateinit var min: VerticalDistance
    private lateinit var max: VerticalDistance

    fun unit(unit: UnitOfMeasure): VerticalExtentBuilder {
        this.unit = unit
        return this
    }

    fun datum(datum: Datum): VerticalExtentBuilder {
        this.datum = datum
        return this
    }

    fun minimum(min: VerticalDistance): VerticalExtentBuilder {
        this.min = min
        return this
    }

    fun minimum(value: Double, unit: UnitOfMeasure? = null, datum: Datum? = null): VerticalExtentBuilder {
        min = VerticalDistance(value, unit, datum)
        return this
    }

    fun maximum(max: VerticalDistance): VerticalExtentBuilder {
        this.max = max
        return this
    }

    fun maximum(value: Double, unit: UnitOfMeasure? = null, datum: Datum? = null): VerticalExtentBuilder {
        max = VerticalDistance(value, unit, datum)
        return this
    }

    override fun build(): VerticalExtent = VerticalExtent(unit, datum, min, max)
}
