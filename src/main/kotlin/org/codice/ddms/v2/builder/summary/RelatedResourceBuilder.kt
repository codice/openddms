/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResource

class RelatedResourceBuilder : Builder<RelatedResource> {
    companion object {
        fun relatedResource(init: RelatedResourceBuilder.() -> Unit) =
                RelatedResourceBuilder().apply(init).build()
    }

    private lateinit var qualifier: String
    private lateinit var value: String
    private val links: ArrayList<Link> = arrayListOf()

    fun qualifier(qualifier: String): RelatedResourceBuilder {
        this.qualifier = qualifier
        return this
    }

    fun value(value: String): RelatedResourceBuilder {
        this.value = value
        return this
    }

    fun links(vararg link: Link): RelatedResourceBuilder {
        links.addAll(link)
        return this
    }

    fun links(link: List<Link>): RelatedResourceBuilder {
        links.addAll(link)
        return this
    }

    fun link(init: LinkBuilder.() -> Unit): RelatedResourceBuilder {
        val link = LinkBuilder().apply(init).build()
        return links(link)
    }

    override fun build(): RelatedResource = RelatedResource(qualifier, value, links)
}
