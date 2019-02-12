/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResource
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RelatedResourceBuilderTest {
    private val relatedResource = RelatedResource("qualifier", "value",
            listOf(Link("http://example.com")))

    @Test
    fun `building with methods`() {
        val result = RelatedResourceBuilder()
                .qualifier(relatedResource.qualifier)
                .value(relatedResource.value)
                .links(relatedResource.links)
                .build()

        assertThat(result, equalTo(relatedResource))
    }

    @Test
    fun `building link lambda`() {
        val result = RelatedResourceBuilder()
                .qualifier(relatedResource.qualifier)
                .value(relatedResource.value)
                .link {
                    href("http://example.com")
                }
                .build()

        assertThat(result, equalTo(relatedResource))
    }

    @Test
    fun `building with lambda`() {
        val result = RelatedResourceBuilder.relatedResource {
            qualifier(relatedResource.qualifier)
            value(relatedResource.value)
            link {
                href("http://example.com")
            }
        }

        assertThat(result, equalTo(relatedResource))
    }
}
