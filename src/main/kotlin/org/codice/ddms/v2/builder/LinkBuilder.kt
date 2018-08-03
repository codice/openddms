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

fun link(init: LinkBuilder.() -> Unit) = LinkBuilder().apply(init).build()

class LinkBuilder : Builder<Link> {
    private lateinit var href: String
    private var role = ""
    private var title = ""
    private var label = ""

    fun href(href: String): LinkBuilder {
        this.href = href
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

    override fun build(): Link = Link(href, role, title, label)
}
