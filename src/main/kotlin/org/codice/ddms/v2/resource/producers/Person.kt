/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource.producers

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
