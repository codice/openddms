/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
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
