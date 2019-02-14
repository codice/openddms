/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

import org.codice.ddms.v2.resource.producers.Producer
import org.codice.ddms.v2.security.ism.SecurityAttributes

/**
 * Generic class for contacting an entity.
 *
 * @param producer The entity used for contacting.
 * @param securityAttributes The [SecurityAttributes] applicable for the [producer].
 */
data class Contact(
    val producer: Producer,
    val securityAttributes: SecurityAttributes = SecurityAttributes()
)
