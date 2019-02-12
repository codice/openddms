/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary.geospatial

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
