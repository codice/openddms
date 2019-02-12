/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

import org.codice.ddms.v2.summary.Link
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.lang.IllegalStateException

class LinkBuilderTest {
    private val link = Link("http://example.com", "role", "title", "label")

    @Test
    fun `building with methods`() {
        val result = LinkBuilder()
                .href(link.href)
                .label(link.label)
                .role(link.role)
                .title(link.title)
                .build()

        assertThat(result, equalTo(link))
    }

    @Test
    fun `building with lambda`() {
        val result = LinkBuilder.link {
            href(link.href)
            label(link.label)
            role(link.role)
            title(link.title)
        }

        assertThat(result, equalTo(link))
    }

    @Test(expected = IllegalStateException::class)
    fun `building a link without an href throws an exception`() {
        LinkBuilder().build()
    }
}
