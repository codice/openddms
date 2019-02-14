/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.format

/**
 * The physical or digital manifestation of the resource.
 *
 * @param mimeType The MIME type for the resource.
 * @param extent The extent of the resource.
 * @param medium The physical medium or instantiation of the resource.
 */
data class Format(
    val mimeType: String,
    val extent: Extent = Extent(),
    val medium: String = ""
)

/**
 * A wrapper for the resources extent.
 *
 * @param qualifier A vocabulary that specifies the type of format extent that will be supplied.
 * @param value A related data size, compression rate, or pixel size (etc) of the resource.
 */
data class Extent(
    val qualifier: String = "",
    val value: String = ""
)
