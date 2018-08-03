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
            "ddms:GeospatialCoverage must contain at least one of the following: ddms:geographicIdentifiers, ddms:boundBox, ddms:boundingGeometry, ddms:postalAddress, ddms:verticalExtent"
        }
    }
}
