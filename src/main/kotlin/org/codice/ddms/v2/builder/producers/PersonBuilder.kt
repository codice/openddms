/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.producers

import org.codice.ddms.v2.resource.producers.Person

class PersonBuilder : ProducerBuilder<PersonBuilder>() {
    companion object {
        fun person(init: PersonBuilder.() -> Unit) = PersonBuilder().apply(init).build()
    }

    private lateinit var surname: String
    private var userId: String = ""
    private var affiliation: String = ""

    fun surname(surname: String): PersonBuilder {
        this.surname = surname
        return this
    }

    fun userId(userId: String): PersonBuilder {
        this.userId = userId
        return this
    }

    fun affiliation(affiliation: String): PersonBuilder {
        this.affiliation = affiliation
        return this
    }

    override fun build() = Person(nameList, surname, userId, affiliation, phonesList, emailsList)
}
