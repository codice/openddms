/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

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
        val result = RelatedResourcesBuilder.relatedResources {
            relationship(relatedResources.relationship)
            direction(relatedResources.direction)
            resources(relatedResources.resources)
            securityAttributes(relatedResources.securityAttributes)
        }

        assertThat(result, equalTo(relatedResources))
    }
}
