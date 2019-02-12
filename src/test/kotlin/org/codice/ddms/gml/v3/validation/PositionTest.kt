/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.validation

import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PositionTest {
    @Test
    fun `a Position creates correct xml`() {
        val xml = "<gml:pos srsName=\"wgs84\" xmlns:gml=\"http://www.opengis.net/gml\">0.0 0.0</gml:pos>"
        val position = Position(listOf(0.0, 0.0), SrsAttributes(srsName = "wgs84"))

        assertThat(position.toString(), equalTo(xml))
    }
}
