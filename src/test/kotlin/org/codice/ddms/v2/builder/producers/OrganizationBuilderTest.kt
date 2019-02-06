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
