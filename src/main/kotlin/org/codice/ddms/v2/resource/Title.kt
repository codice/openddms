/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes

data class Title(
    val securityAttributes: SecurityAttributes,
    val value: String
) {
    init {
        require(value.isNotBlank()) {
            "ddms:title and ddms:subtitle must have a non blank value"
        }
        with(securityAttributes) {
            require(classification != Classification.NO_CLASSIFICATION) {
                "ddms:title and ddms:subtitle must contain an ism:classification"
            }
            require(ownerProducer.any { it.isNotBlank() }) {
                "ddms:title and ddms:subtitle must contain at least one ism:ownerProducer"
            }
        }
    }
}
