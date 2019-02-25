/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.security

import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes

/**
 * Security markings that specify how a data asset shall be stored, protected, and destroyed.
 *
 * [SecurityAttributes.classification] must not be [Classification.NO_CLASSIFICATION] and
 * [SecurityAttributes.ownerProducer] must not be blank.
 *
 * @throws IllegalArgumentException If [SecurityAttributes.classification] equals [Classification.NO_CLASSIFICATION]
 * or [SecurityAttributes.ownerProducer] is blank.
 */
data class Security(val securityAttributes: SecurityAttributes) {
    init {
        with(securityAttributes) {
            require(classification != Classification.NO_CLASSIFICATION) {
                "ddms:security must contain an ism:classification"
            }
            require(ownerProducer.any { it.isNotBlank() }) {
                "ddms:security must contain at least one ism:ownerProducer"
            }
        }
    }
}
