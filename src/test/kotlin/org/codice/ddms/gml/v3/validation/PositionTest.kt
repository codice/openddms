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
package org.codice.ddms.gml.v3.validation

import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PositionTest {
    @Test
    fun `a Position create correct xml`() {
        val xml = "<gml:pos srsName=\"wgs84\" xmlns:gml=\"http://www.opengis.net/gml\">0.0 0.0</gml:pos>"
        val position = Position(listOf(0.0, 0.0), SrsAttributes(srsName = "wgs84"))

        assertThat(position.toString(), equalTo(xml))
    }
}
