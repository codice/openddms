/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.producers

import org.codice.ddms.v2.resource.producers.Service
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ServiceBuilderTest {
    private val service = Service(listOf("names"), listOf("phones"), listOf("emails"))

    @Test
    fun `building with methods`() {
        val result = ServiceBuilder()
                .names(service.names)
                .phones(service.phones)
                .emails(service.emails)
                .build()

        assertThat(result, equalTo(service))
    }

    @Test
    fun `building with lambda`() {
        val result = ServiceBuilder.service {
            names(service.names)
            phones(service.phones)
            emails(service.emails)
        }

        assertThat(result, equalTo(service))
    }
}
