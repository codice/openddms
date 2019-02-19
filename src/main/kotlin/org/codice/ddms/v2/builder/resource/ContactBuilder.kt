/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.resource

import org.codice.ddms.Builder
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder
import org.codice.ddms.v2.builder.resource.producers.OrganizationBuilder
import org.codice.ddms.v2.builder.resource.producers.PersonBuilder
import org.codice.ddms.v2.builder.resource.producers.ServiceBuilder
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
