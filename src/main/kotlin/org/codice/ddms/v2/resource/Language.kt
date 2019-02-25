/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

/**
 * The primary language of the intellectual content of the resource.
 *
 * @param qualifier The value that specifies the originating agency or discipline of the language vocabulary.
 * @param value The identification of the content language.
 */
data class Language(
    val qualifier: String = "",
    val value: String = ""
)
