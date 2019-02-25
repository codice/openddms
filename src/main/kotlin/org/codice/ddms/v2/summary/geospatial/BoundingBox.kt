/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

/**
 * A wrapper for describing the bounding longitudes and latitudes of the geographic extent.
 *
 * There are no checks to make sure the bounds are valid.
 *
 * @param north The northernmost latitude of the area of interest.
 * @param south The southernmost latitude of the area of interest.
 * @param east The easternmost longitude of the area of interest.
 * @param west The westernmost longitude of the area of interest.
 */
data class BoundingBox(
    val north: Double,
    val south: Double,
    val east: Double,
    val west: Double
)
