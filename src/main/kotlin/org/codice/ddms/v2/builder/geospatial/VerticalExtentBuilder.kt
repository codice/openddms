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
package org.codice.ddms.v2.builder.geospatial

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.codice.ddms.v2.summary.geospatial.VerticalDistance
import org.codice.ddms.v2.summary.geospatial.VerticalExtent

fun verticalExtent(init: VerticalExtentBuilder.() -> Unit) =
        VerticalExtentBuilder().apply(init).build()

class VerticalExtentBuilder : Builder<VerticalExtent> {
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
