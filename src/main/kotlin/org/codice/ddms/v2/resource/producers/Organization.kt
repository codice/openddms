/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource.producers

/**
 * Information about an organization.
 *
 * [names] must not be empty.
 *
 * @throws IllegalArgumentException If [names] is empty.
 */
data class Organization(
    override val names: List<String>,
    override val phones: List<String> = emptyList(),
    override val emails: List<String> = emptyList()
) : Producer {
    init {
        require(names.isNotEmpty()) {
            "ddms:Organization must contain at least one name"
        }
    }
}
