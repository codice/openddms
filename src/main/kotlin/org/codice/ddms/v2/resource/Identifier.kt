/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

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
