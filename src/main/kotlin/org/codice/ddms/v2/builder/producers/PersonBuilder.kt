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

fun person(init: PersonBuilder.() -> Unit) = PersonBuilder().apply(init).build()

class PersonBuilder : ProducerBuilder {
    private val names: ArrayList<String> = arrayListOf()
    private lateinit var surname: String
    private var userId: String = ""
    private var affiliation: String = ""
    private val emails: ArrayList<String> = arrayListOf()
    private val phones: ArrayList<String> = arrayListOf()

    override fun names(vararg name: String): PersonBuilder {
        names.addAll(name)
        return this
    }

    override fun names(name: List<String>): PersonBuilder {
        names.addAll(name)
        return this
    }

    override fun phones(vararg phone: String): PersonBuilder {
        phones.addAll(phone)
        return this
    }

    override fun phones(phone: List<String>): PersonBuilder {
        phones.addAll(phone)
        return this
    }

    override fun emails(vararg email: String): PersonBuilder {
        emails.addAll(email)
        return this
    }

    override fun emails(email: List<String>): PersonBuilder {
        emails.addAll(email)
        return this
    }

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

    override fun build() = Person(names, surname, userId, affiliation, phones, emails)
}
