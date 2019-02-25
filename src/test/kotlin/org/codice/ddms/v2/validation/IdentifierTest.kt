/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.resource.Identifier
import org.junit.Test

class IdentifierTest {
    @Test
    fun `an Identifier with a qualifier and a value is valid`() {
        Identifier("qualifier", "value")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `an Identifier with a blank qualifier is invalid`() {
        Identifier("", "value")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `an Identifier with a blank value is invalid`() {
        Identifier("qualifier", "")
    }
}
