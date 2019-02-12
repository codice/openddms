/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.format

data class Format(
    val mimeType: String,
    val extent: Extent = Extent(),
    val medium: String = ""
)

data class Extent(
    val qualifier: String = "",
    val value: String = ""
)
