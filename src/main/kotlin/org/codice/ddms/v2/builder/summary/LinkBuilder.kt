/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.Link

class LinkBuilder : Builder<Link> {
    companion object {
        fun link(init: LinkBuilder.() -> Unit) = LinkBuilder().apply(init).build()
    }

    private lateinit var hrefValue: String
    private var role = ""
    private var title = ""
    private var label = ""

    fun href(href: String): LinkBuilder {
        this.hrefValue = href
        return this
    }

    fun role(role: String): LinkBuilder {
        this.role = role
        return this
    }

    fun title(title: String): LinkBuilder {
        this.title = title
        return this
    }

    fun label(label: String): LinkBuilder {
        this.label = label
        return this
    }

    override fun build(): Link {
        check(::hrefValue.isInitialized) {
            "LinkBuilder must have a href value"
        }
        return Link(hrefValue, role, title, label)
    }
}
