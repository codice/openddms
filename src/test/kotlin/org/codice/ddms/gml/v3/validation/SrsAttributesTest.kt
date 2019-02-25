/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.validation

import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.lang.IllegalArgumentException

class SrsAttributesTest {
    @Test
    fun `SrsAttributes create valid xml attributes`() {
        val xml = """srsName="srsName" srsDimension="2" axisLabels="axis labels" uomLabels="uom labels""""
        val srsAttributes = SrsAttributes("srsName", 2, listOf("axis", "labels"), listOf("uom", "labels"))

        assertThat(srsAttributes.toString(), equalTo(xml))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `SrsAttributes is invalid with a negative srsDimension`() {
        SrsAttributes("srsName", -1, listOf("axis", "labels"), listOf("uom", "labels"))
    }
}
