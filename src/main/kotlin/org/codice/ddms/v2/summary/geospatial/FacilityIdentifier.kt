/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

/**
 * A wrapper for a facility identifier or reference to an identifier.
 *
 * Must have a non-blank [beNumber].
 *
 * @param beNumber A DIA-specific identification number of a facility or installation. Must not be blank.
 * @param oSuffix A specific identification number for a facility located at the installation associated by the
 * [beNumber]
 * @throws IllegalArgumentException If [beNumber] is blank.
 */
data class FacilityIdentifier(
    val beNumber: String,
    val oSuffix: String = ""
) {
    init {
        require(beNumber.isNotBlank()) {
            "ddms:facilityIdentifier is missing beNumber."
        }
    }
}
