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
package org.codice.ddms.v2.summary.geospatial

enum class UnitOfMeasure {
    Meter,
    Kilometer,
    Foot,
    StatuteMile,
    NauticalMile,
    Fathom,
    Inch
}

enum class Datum {
    MSL,
    AGL,
    HAE
}

data class VerticalExtent(
    val unit: UnitOfMeasure,
    val datum: Datum,
    val minVerticalExtent: VerticalDistance,
    val maxVerticalExtentValue: VerticalDistance
) {
    init {
        require(minVerticalExtent.value <= maxVerticalExtentValue.value) {
            "ddms:verticalExtent ddms:MinVerticalExtent must be less than or equal to ddms:MaxVerticalExtent"
        }
    }
}

data class VerticalDistance(
    val value: Double,
    val unit: UnitOfMeasure? = null,
    val datum: Datum? = null
)
