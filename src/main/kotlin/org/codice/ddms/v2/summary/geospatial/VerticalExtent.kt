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

/**
 *  The vertical extent that is applicable to the resource.
 *
 * [minVerticalExtent] must be smaller than [maxVerticalExtent].
 *
 * @param unit The unit of measure being used.
 * @param datum The datum being used.
 * @param minVerticalExtent The lowest vertical point within the coverage. Must be smaller than [maxVerticalExtent].
 * @param maxVerticalExtent The highest vertical point within the coverage. Must be larger than [minVerticalExtent].
 * @throws IllegalArgumentException If [minVerticalExtent] is not smaller than [maxVerticalExtent].
 */
data class VerticalExtent(
    val unit: UnitOfMeasure,
    val datum: Datum,
    val minVerticalExtent: VerticalDistance,
    val maxVerticalExtent: VerticalDistance
) {
    init {
        require(minVerticalExtent.value <= maxVerticalExtent.value) {
            "ddms:verticalExtent ddms:MinVerticalExtent must be less than or equal to ddms:MaxVerticalExtent"
        }
    }
}

/**
 * A wrapper used to describe the height of an extent.
 *
 * @param value The height of the extent.
 * @param unit The unit of measure of [value].
 * @param datum The datum used.
 */
data class VerticalDistance(
    val value: Double,
    val unit: UnitOfMeasure? = null,
    val datum: Datum? = null
)
