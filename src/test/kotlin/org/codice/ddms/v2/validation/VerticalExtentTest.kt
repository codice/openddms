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
