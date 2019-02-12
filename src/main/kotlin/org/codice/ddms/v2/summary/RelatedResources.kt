/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
