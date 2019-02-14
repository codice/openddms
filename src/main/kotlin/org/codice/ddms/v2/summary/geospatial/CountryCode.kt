/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

/**
 * A wrapper for a country code identifier.
 *
 * Must have a non-blank [qualifier] and [value].
 *
 * @param qualifier A vocabulary notation specifying the source of the country abbreviations.
 * @param value A standards-based abbreviation of a country name.
 * @throws IllegalArgumentException If [qualifier] or [value] are blank.
 */
data class CountryCode(
    val qualifier: String,
    val value: String
) {
    init {
        require(qualifier.isNotBlank() && value.isNotBlank()) {
            "ddms:CountryCode must have a qualifier and a value"
        }
    }
}
