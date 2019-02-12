/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.producers

import org.codice.ddms.v2.resource.producers.Organization
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class OrganizationBuilderTest {
    private val organization = Organization(listOf("names"), listOf("phones"), listOf("emails"))

    @Test
    fun `building with methods`() {
        val result = OrganizationBuilder()
                .names(organization.names)
                .phones(organization.phones)
                .emails(organization.emails)
                .build()

        assertThat(result, equalTo(organization))
    }

    @Test
    fun `building with lambda`() {
        val result = OrganizationBuilder.organization {
            names(organization.names)
            phones(organization.phones)
            emails(organization.emails)
        }

        assertThat(result, equalTo(organization))
    }
}
