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

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResource

fun relatedResource(init: RelatedResourceBuilder.() -> Unit) =
        RelatedResourceBuilder().apply(init).build()

class RelatedResourceBuilder : Builder<RelatedResource> {
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
