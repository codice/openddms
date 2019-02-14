/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource.producers

/**
 * Generic interface for sharing common attributes between entities.
 *
 * @property names A name given to a [Person], [Organization], or [Service].
 * @property phones The phone numbers associated with a [Person], [Organization], or [Service].
 * @property emails The emails associated with a [Person], [Organization], or [Service].
 */
interface Producer {
    val names: List<String>
    val phones: List<String>
    val emails: List<String>
}
