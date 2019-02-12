/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

data class Source(
    val qualifier: String = "",
    val value: String = "",
    val schema: String = "",
    val href: String = ""
)
