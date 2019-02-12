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
