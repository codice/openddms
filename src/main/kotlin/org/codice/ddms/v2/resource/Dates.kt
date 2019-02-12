/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

import org.codice.ddms.DdmsDate

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
