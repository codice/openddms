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

import org.codice.ddms.v2.resource.producers.Service

fun service(init: ServiceBuilder.() -> Unit) = ServiceBuilder().apply(init).build()

class ServiceBuilder : ProducerBuilder {
    private val names: ArrayList<String> = arrayListOf()
    private val phones: ArrayList<String> = arrayListOf()
    private val emails: ArrayList<String> = arrayListOf()

    override fun names(vararg name: String): ServiceBuilder {
        names.addAll(name)
        return this
    }

    override fun names(name: List<String>): ServiceBuilder {
        names.addAll(name)
        return this
    }

    override fun phones(vararg phone: String): ServiceBuilder {
        phones.addAll(phone)
        return this
    }

    override fun phones(phone: List<String>): ServiceBuilder {
        phones.addAll(phone)
        return this
    }

    override fun emails(vararg email: String): ServiceBuilder {
        emails.addAll(email)
        return this
    }

    override fun emails(email: List<String>): ServiceBuilder {
        emails.addAll(email)
        return this
    }

    override fun build() = Service(names, phones, emails)
}
