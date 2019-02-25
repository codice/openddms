/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary

import org.codice.ddms.v2.security.ism.SecurityAttributes

/**
 * A set of [resources][RelatedResource] related to the resource being described by a specified relationship and
 * direction.
 *
 * @param relationship The relationship of some relationship type between the resource being described and other
 * resources.
 * @param direction Used to indicate the direction of the relationship between the resource being described and the
 * target related resource.
 * @param resources A list of [related resources][RelatedResource] identified being related to the resource described
 * by the containing [DdmsResource][org.codice.ddms.DdmsResource].
 * @param securityAttributes The [SecurityAttributes] applicable for the related resources.
 */
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

/**
 * An identifier for the resource being related to the resource described in the containing
 * [DdmsResource][org.codice.ddms.DdmsResource].
 *
 * @param qualifier The formal identification system or encoding scheme by which the identifier value is to be
 * interpreted.
 * @param value An unambiguous reference to the resource within a given context. An internal, external, and/or universal
 * identification number for a data asset or resource.
 * @param links A list of [links][Link] for locating the [RelatedResource].
 */
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

/**
 * A wrapper for an XLink locator element for the resource being described.
 *
 * @param href The location of the target [RelatedResource].
 * @param role Identifies some resource that describes the intended property.
 * @param title Describes the meaning of a link or resource in a human-readable fashion.
 * @param label Provides a name for the link.
 */
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
