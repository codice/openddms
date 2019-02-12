/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

data class BoundingBox(
    val north: Double,
    val south: Double,
    val east: Double,
    val west: Double
)
