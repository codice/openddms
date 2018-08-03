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
        val result = relatedResource {
            qualifier(relatedResource.qualifier)
            value(relatedResource.value)
            link {
                href("http://example.com")
            }
        }

        assertThat(result, equalTo(relatedResource))
    }
}
