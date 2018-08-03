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
package org.codice.ddms.v2.builder

import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResource
import org.codice.ddms.v2.summary.RelatedResources
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RelatedResourcesBuilderTest {
    private val relatedResources = RelatedResources("relationship",
            RelatedResources.Direction.Outbound,
            listOf(RelatedResource("qualifier", "value",
                    listOf(Link("http://example.com")))),
            SecurityAttributes(Classification.U, listOf("USA")))

    @Test
    fun `building with methods`() {
        val result = RelatedResourcesBuilder()
                .relationship(relatedResources.relationship)
                .direction(relatedResources.direction)
                .resources(relatedResources.resources)
                .securityAttributes(relatedResources.securityAttributes)
                .build()

        assertThat(result, equalTo(relatedResources))
    }

    @Test
    fun `building with resource builder lambda`() {
        val result = RelatedResourcesBuilder()
                .relationship(relatedResources.relationship)
                .direction(relatedResources.direction)
                .resource {
                    qualifier("qualifier")
                    value("value")
                    link {
                        href("http://example.com")
                    }
                }
                .securityAttributes(relatedResources.securityAttributes)
                .build()

        assertThat(result, equalTo(relatedResources))
    }

    @Test
    fun `building with security attributes builder lambda`() {
        val result = RelatedResourcesBuilder()
                .relationship(relatedResources.relationship)
                .direction(relatedResources.direction)
                .resources(relatedResources.resources)
                .securityAttributes {
                    classification(Classification.U)
                    ownerProducers("USA")
                }
                .build()

        assertThat(result, equalTo(relatedResources))
    }

    @Test
    fun `building with lambda`() {
        val result = relatedResources {
            relationship(relatedResources.relationship)
            direction(relatedResources.direction)
            resources(relatedResources.resources)
            securityAttributes(relatedResources.securityAttributes)
        }

        assertThat(result, equalTo(relatedResources))
    }
}
