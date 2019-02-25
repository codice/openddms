/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.resource.producers.Service
import org.junit.Test

class ServiceTest {
    @Test
    fun `a Service with a name is valid`() {
        Service(listOf("name"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Service with no name is invalid`() {
        Service(emptyList())
    }
}
