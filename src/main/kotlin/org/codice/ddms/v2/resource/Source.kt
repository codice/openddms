/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

/**
 * References to assets or resources from which the tagged data asset is derived.
 *
 * @param qualifier The value that specifies a formal identification system used to reference a source.
 * @param value The identifier of a referenced source.
 * @param schema The schema type used to identify the format of the resource.
 * @param href A resolvable reference to the schema for the data asset.
 */
data class Source(
    val qualifier: String = "",
    val value: String = "",
    val schema: String = "",
    val href: String = ""
)
