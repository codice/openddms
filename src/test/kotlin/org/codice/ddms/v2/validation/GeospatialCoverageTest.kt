/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.codice.ddms.v2.summary.GeospatialCoverage
import org.codice.ddms.v2.summary.geospatial.BoundingBox
import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.codice.ddms.v2.summary.geospatial.VerticalDistance
import org.codice.ddms.v2.summary.geospatial.VerticalExtent
import org.junit.Test

class GeospatialCoverageTest {
    private val geographicIdentifier = GeographicIdentifier(listOf("name"), emptyList(), emptyList(), emptyList())
    private val boundingBox = BoundingBox(0.0, 0.0, 0.0, 0.0)
    private val boundingGeometry = BoundingGeometry(listOf(Polygon("polygon", LinearRing(
            listOf(Position(emptyList()), Position(emptyList()), Position(emptyList()), Position(emptyList()))),
            SrsAttributes("srsName"))),
            emptyList())
    private val postalAddress = PostalAddress()
    private val verticalExtent = VerticalExtent(UnitOfMeasure.Foot, Datum.MSL, VerticalDistance(0.0), VerticalDistance(10.0))

    @Test
    fun `a GeospatialCoverage with a GeographicIdentifier is valid`() {
        GeospatialCoverage(listOf(geographicIdentifier),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList())
    }

    @Test
    fun `a GeospatialCoverage with a BoundingBox is valid`() {
        GeospatialCoverage(emptyList(),
                listOf(boundingBox),
                emptyList(),
                emptyList(),
                emptyList())
    }

    @Test
    fun `a GeospatialCoverage with a BoundingGeometry is valid`() {
        GeospatialCoverage(emptyList(),
                emptyList(),
                listOf(boundingGeometry),
                emptyList(),
                emptyList())
    }

    @Test
    fun `a GeospatialCoverage with a PostalAddress is valid`() {
        GeospatialCoverage(emptyList(),
                emptyList(),
                emptyList(),
                listOf(postalAddress),
                emptyList())
    }

    @Test
    fun `a GeospatialCoverage with a VerticalExtent is valid`() {
        GeospatialCoverage(emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                listOf(verticalExtent))
    }

    @Test
    fun `a GeospatialCoverage with a GeographicIdentifier, BoundingBox, BoundingGeometry, PostalAddress VerticalExtent is valid`() {
        GeospatialCoverage(listOf(geographicIdentifier),
                listOf(boundingBox),
                listOf(boundingGeometry),
                listOf(postalAddress),
                listOf(verticalExtent))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a GeospatialCoverage with nothing is invalid`() {
        GeospatialCoverage(emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList())
    }
}
