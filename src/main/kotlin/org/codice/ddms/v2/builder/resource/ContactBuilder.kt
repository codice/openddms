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
package org.codice.ddms.v2.builder.resource

import org.codice.ddms.Builder
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder
import org.codice.ddms.v2.builder.producers.OrganizationBuilder
import org.codice.ddms.v2.builder.producers.PersonBuilder
import org.codice.ddms.v2.builder.producers.ServiceBuilder
import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.producers.Producer
import org.codice.ddms.v2.security.ism.SecurityAttributes

class ContactBuilder : Builder<Contact> {
    companion object {
        fun contact(init: ContactBuilder.() -> Unit): Contact = ContactBuilder().apply(init).build()
    }

    private lateinit var producer: Producer
    private var securityAttributes: SecurityAttributes = SecurityAttributes()

    fun producer(producer: Producer): ContactBuilder {
        this.producer = producer
        return this
    }

    fun person(init: PersonBuilder.() -> Unit): ContactBuilder {
        producer = PersonBuilder().apply(init).build()
        return this
    }

    fun organization(init: OrganizationBuilder.() -> Unit): ContactBuilder {
        producer = OrganizationBuilder().apply(init).build()
        return this
    }

    fun service(init: ServiceBuilder.() -> Unit): ContactBuilder {
        producer = ServiceBuilder().apply(init).build()
        return this
    }

    fun securityAttributes(securityAttributes: SecurityAttributes): ContactBuilder {
        this.securityAttributes = securityAttributes
        return this
    }

    fun securityAttributes(init: SecurityAttributeBuilder.() -> Unit): ContactBuilder {
        securityAttributes = SecurityAttributeBuilder().apply(init).build()
        return this
    }

    override fun build() = Contact(producer, securityAttributes)
}
