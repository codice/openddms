/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary

import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes

/**
 * An account of the content of the resource.
 *
 * @param securityAttributes The [SecurityAttributes] applicable to the description. [SecurityAttributes.classification]
 * must not be [Classification.NO_CLASSIFICATION] and must contain a non-blank [SecurityAttributes.ownerProducer]
 * @param value The content of the description.
 * @throws IllegalArgumentException If [SecurityAttributes.classification] is [Classification.NO_CLASSIFICATION] or
 * [SecurityAttributes.ownerProducer] is blank.
 */
data class Description(
    val securityAttributes: SecurityAttributes,
    val value: String
) {
    init {
        with(securityAttributes) {
            require(classification != Classification.NO_CLASSIFICATION) {
                "ddms:description must contain an ism:classification"
            }
            require(ownerProducer.any { it.isNotBlank() }) {
                "ddms:description must contain at least one ism:ownerProducer"
            }
        }
    }
}
