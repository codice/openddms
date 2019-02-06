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
package org.codice.ddms.v2.summary

import org.codice.ddms.v2.security.ism.SecurityAttributes

data class RelatedResources(
    val relationship: String,
    val direction: Direction = Direction.Outbound,
    val resources: List<RelatedResource> = emptyList(),
    val securityAttributes: SecurityAttributes = SecurityAttributes()
) {
    enum class Direction {
        Outbound,
        Inbound,
        Bidirectional;

        override fun toString(): String = name.toLowerCase()
    }
}

data class RelatedResource(
    val qualifier: String,
    val value: String,
    val links: List<Link>
) {
    init {
        require(links.isNotEmpty()) {
            "ddms:RelatedResource must contain at least one ddms:link"
        }
    }
}

data class Link(
    val href: String,
    val role: String = "",
    val title: String = "",
    val label: String = ""
) {
    init {
        require(href.isNotBlank()) {
            "ddms:link must have a non blank href"
        }
    }
    val type = "locator"
}
