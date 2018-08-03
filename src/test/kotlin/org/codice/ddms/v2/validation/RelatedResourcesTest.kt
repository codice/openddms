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
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResource
import org.codice.ddms.v2.summary.RelatedResources
import org.junit.Test

class RelatedResourcesTest {
    private val relationship = "test:relationship"
    private val qualifier = "test:qualifier"
    private val value = "value"

    @Test
    fun `a RelatedResources with a relationship is valid`() {
        RelatedResources(relationship)
    }

    @Test
    fun `a RelatedResource with a link is valid`() {
        val link = Link("link")
        RelatedResource(qualifier, value, listOf(link))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a related resource without a link is invalid`() {
        RelatedResource(qualifier, value, emptyList())
    }
}
