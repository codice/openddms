/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
