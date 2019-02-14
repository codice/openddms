/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

/**
 * An unambiguous reference to a resource within a given context.
 *
 * [qualifier] and [value] must not be blank.
 *
 * @param qualifier A [String] specifying the formal identification system or encoding scheme by which the identifier
 * value is to be interpreted. Must not be blank.
 * @param value An unambiguous reference to the resource within a given context. An internal, external, and/or universal
 * identification number for a data asset or resource. Must not be blank.
 * @throws IllegalArgumentException If [qualifier] or [value] are blank.
 */
data class Identifier(
    val qualifier: String,
    val value: String
) {
    init {
        require(qualifier.isNotBlank()) {
            "ddms:identifier must have a non blank qualifier"
        }
        require(value.isNotBlank()) {
            "ddms:identifier must have a non blank value"
        }
    }
}
