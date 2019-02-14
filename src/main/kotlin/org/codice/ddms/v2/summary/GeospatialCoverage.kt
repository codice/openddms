/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary

import org.codice.ddms.v2.summary.geospatial.BoundingBox
import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.VerticalExtent

/**
 * Geographic place names or coordinates that relate to the resource, such as jurisdiction, point, area, or volume on
 * land, in space, or at sea.
 *
 * Must have at least one element in [geographicIdentifiers], or [boundingBoxes], or [boundingGeometries], or
 * [postalAddresses], or [verticalExtents].
 *
 * @param geographicIdentifiers A list of [geographic identifiers][GeographicIdentifier] that relate to the containing
 * [DdmsResource][org.codice.ddms.DdmsResource].
 * @param boundingBoxes A list of [bounding boxes][BoundingBox] that relate to the containing
 * [DdmsResource][org.codice.ddms.DdmsResource].
 * @param boundingGeometries A list of [bounding geometries][BoundingGeometry] that relate to the containing
 * [DdmsResource][org.codice.ddms.DdmsResource].
 * @param postalAddresses A list of [postal addresses][PostalAddress] that relate to the containing
 * [DdmsResource][org.codice.ddms.DdmsResource].
 * @param verticalExtents A list of [vertical extents][VerticalExtent] that relate to the containing
 * [DdmsResource][org.codice.ddms.DdmsResource].
 * @throws IllegalArgumentException If [geographicIdentifiers], [boundingBoxes], [boundingGeometries],
 * [postalAddresses], and [verticalExtents] are all empty.
 */
data class GeospatialCoverage(
    val geographicIdentifiers: List<GeographicIdentifier>,
    val boundingBoxes: List<BoundingBox>,
    val boundingGeometries: List<BoundingGeometry>,
    val postalAddresses: List<PostalAddress>,
    val verticalExtents: List<VerticalExtent>
) {
    init {
        require(geographicIdentifiers.isNotEmpty() ||
                boundingBoxes.isNotEmpty() ||
                boundingGeometries.isNotEmpty() ||
                postalAddresses.isNotEmpty() ||
                verticalExtents.isNotEmpty()) {
            "ddms:GeospatialCoverage must contain at least one of the following: ddms:geographicIdentifiers, " +
                    "ddms:boundBox, ddms:boundingGeometry, ddms:postalAddress, ddms:verticalExtent"
        }
    }
}
