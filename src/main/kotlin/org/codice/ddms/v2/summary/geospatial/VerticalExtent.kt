/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
