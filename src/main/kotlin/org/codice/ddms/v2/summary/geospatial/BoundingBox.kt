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
 * @param west The westernmost latitude of the area of interest.
 * @param east The easternmost latitude of the area of interest.
 * @param south The southernmost longitude of the area of interest.
 * @param north The northernmost longitude of the area of interest.
 */
data class BoundingBox(
    val west: Double,
    val east: Double,
    val south: Double,
    val north: Double
)
