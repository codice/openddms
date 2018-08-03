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

import org.codice.ddms.gml.v3.LinearRing
import org.codice.ddms.gml.v3.Position
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class LinearRingTest {
    @Test
    fun `a LinearRing with four positions is valid`() {
        LinearRing((1..4).map { Position(listOf(0.0)) })
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a LinearRing with less than four positions is invalid`() {
        LinearRing((1..3).map { Position(listOf(0.0)) })
    }

    @Test
    fun `a LinearRing with more than four positions is valid`() {
        LinearRing((1..5).map { Position(listOf(0.0)) })
    }

    @Test
    fun `a valid LinearRing creates correct xml`() {
        val xml = "<gml:LinearRing xmlns:gml=\"http://www.opengis.net/gml\">" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">0.0</gml:pos>" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">0.0</gml:pos>" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">0.0</gml:pos>" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">0.0</gml:pos>" +
                "<gml:pos xmlns:gml=\"http://www.opengis.net/gml\">0.0</gml:pos>" +
                "</gml:LinearRing>"
        val linearRing = LinearRing((1..5).map { Position(listOf(0.0)) })

        assertThat(linearRing.toString(), equalTo(xml))
    }
}
