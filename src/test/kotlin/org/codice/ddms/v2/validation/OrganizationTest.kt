/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.resource.producers.Organization
import org.junit.Test

class OrganizationTest {
    @Test
    fun `an Organization with a name is valid`() {
        Organization(listOf("name"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `an Organization with no name is invalid`() {
        Organization(emptyList())
    }
}
