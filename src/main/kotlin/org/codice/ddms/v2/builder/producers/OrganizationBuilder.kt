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

import org.codice.ddms.v2.resource.producers.Organization

fun organization(init: OrganizationBuilder.() -> Unit) = OrganizationBuilder().apply(init).build()

class OrganizationBuilder : ProducerBuilder {
    private val names: ArrayList<String> = arrayListOf()
    private val phones: ArrayList<String> = arrayListOf()
    private val emails: ArrayList<String> = arrayListOf()

    override fun names(vararg name: String): OrganizationBuilder {
        names.addAll(name)
        return this
    }

    override fun names(name: List<String>): OrganizationBuilder {
        names.addAll(name)
        return this
    }

    override fun phones(vararg phone: String): OrganizationBuilder {
        phones.addAll(phone)
        return this
    }

    override fun phones(phone: List<String>): OrganizationBuilder {
        phones.addAll(phone)
        return this
    }

    override fun emails(vararg email: String): OrganizationBuilder {
        emails.addAll(email)
        return this
    }

    override fun emails(email: List<String>): OrganizationBuilder {
        emails.addAll(email)
        return this
    }

    override fun build() = Organization(names, phones, emails)
}
