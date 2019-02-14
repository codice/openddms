/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary

/**
 * The subject-matter coverage of a publication in terms of one or more virtual addresses.
 *
 * @param address A computer or telecommunications network address, or a network name or locale.
 * @param protocol The type of rules for data transfer that apply to the Virtual Address.
 */
data class VirtualCoverage(
    val address: String = "",
    val protocol: String = ""
)
