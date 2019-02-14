/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource.producers

/**
 * Information about a person.
 *
 * [names] must not be empty.
 *
 * @param surname A name shared in common to identify members of a family, also known as a last name.
 * @param userId Unique identifier applied by a community or organization to an author, coauthor, POC, tasking requester
 * or addressee.
 * @param affiliation The identification of an organization or community with which an individual has an affiliation.
 * @throws IllegalArgumentException if [names] is empty.
 */
data class Person(
    override val names: List<String>,
    val surname: String,
    val userId: String = "",
    val affiliation: String = "",
    override val phones: List<String> = emptyList(),
    override val emails: List<String> = emptyList()
) : Producer {
    init {
        require(names.isNotEmpty()) {
            "ddms:Person must contain at least one name"
        }
    }
}
