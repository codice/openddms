/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.codice.ddms.v2.summary.geospatial.VerticalDistance
import org.codice.ddms.v2.summary.geospatial.VerticalExtent
import org.junit.Test

class VerticalExtentTest {
    @Test
    fun `a VerticalExtent with a min distance less than max is valid`() {
        VerticalExtent(UnitOfMeasure.Foot, Datum.MSL, VerticalDistance(0.0), VerticalDistance(10.0))
    }

    @Test
    fun `a VerticalExtent with a min distance equal to the max is valid`() {
        VerticalExtent(UnitOfMeasure.Foot, Datum.MSL, VerticalDistance(10.0), VerticalDistance(10.0))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a VerticalExtent with a min distance greater than the max is valid`() {
        VerticalExtent(UnitOfMeasure.Foot, Datum.MSL, VerticalDistance(10.0), VerticalDistance(0.0))
    }
}
