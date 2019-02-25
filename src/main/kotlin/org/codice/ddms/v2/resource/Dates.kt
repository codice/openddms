/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

import org.codice.ddms.DdmsDate

/**
 * A calendar date associated with an event in the life cycle of the resource.
 *
 * Must have at least one non-null date.
 *
 * @param created Date of creation of the resource.
 * @param posted The date a product is posted to a shared network or system.
 * @param validTil The date that a product should be removed from a registry, index, or catalog.
 * @param infoCutOff The cutoff date of information in a product.
 * @throws IllegalArgumentException If [created], [posted], [validTil], and [infoCutOff] are all null.
 */
data class Dates(
    val created: DdmsDate? = null,
    val posted: DdmsDate? = null,
    val validTil: DdmsDate? = null,
    val infoCutOff: DdmsDate? = null
) {
    init {
        require(created !== null || posted !== null || validTil !== null || infoCutOff !== null) {
            "ddms:dates must have at least one non null date"
        }
    }
}
