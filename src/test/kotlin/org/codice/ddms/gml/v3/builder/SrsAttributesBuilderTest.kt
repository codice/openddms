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
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SrsAttributesBuilderTest {
    private val srsAttributes = SrsAttributes("wgs84",
            2,
            listOf("axisLabel"),
            listOf("uomLabel"))

    @Test
    fun `building with methods`() {
        val result = SrsAttributesBuilder()
                .srsName(srsAttributes.srsName)
                .srsDimension(srsAttributes.srsDimension)
                .axisLabels(srsAttributes.axisLabels)
                .uomLabels(srsAttributes.uomLabels)
                .build()

        assertThat(result, equalTo(srsAttributes))
    }

    @Test
    fun `building with lambda`() {
        val result = srsAttributes {
            srsName(srsAttributes.srsName)
            srsDimension(srsAttributes.srsDimension)
            axisLabels(srsAttributes.axisLabels)
            uomLabels(srsAttributes.uomLabels)
        }

        assertThat(result, equalTo(srsAttributes))
    }
}
