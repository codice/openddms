/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

/**
 * The nature, genre, or discipline of the content of the resource.
 *
 * @param qualifier The value that specifies the source of the type vocabulary.
 * @param value Type includes terms describing general categories, functions, genres, or aggregation levels for content.
 */
data class Type(
    val qualifier: String = "",
    val value: String = ""
)
