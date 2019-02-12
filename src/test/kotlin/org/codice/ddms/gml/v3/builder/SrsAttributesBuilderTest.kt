/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
        val result = SrsAttributesBuilder.srsAttributes {
            srsName(srsAttributes.srsName)
            srsDimension(srsAttributes.srsDimension)
            axisLabels(srsAttributes.axisLabels)
            uomLabels(srsAttributes.uomLabels)
        }

        assertThat(result, equalTo(srsAttributes))
    }
}
