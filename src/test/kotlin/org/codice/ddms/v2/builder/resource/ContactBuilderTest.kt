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

import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.producers.Organization
import org.codice.ddms.v2.resource.producers.Person
import org.codice.ddms.v2.resource.producers.Service
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ContactBuilderTest {
    private val contact = Contact(
            Organization(listOf("names"), listOf("phones"), listOf("emails")),
            SecurityAttributes(Classification.U, listOf("USA")))

    @Test
    fun `building with methods`() {
        val result = ContactBuilder()
                .producer(contact.producer)
                .securityAttributes(contact.securityAttributes)
                .build()

        assertThat(result, equalTo(contact))
    }

    @Test
    fun `building with person lambda`() {
        val person = Person(listOf("names"), "surname", "userId",
                "affiliation", listOf("phones"), listOf("emails"))
        val result = ContactBuilder()
                .person {
                    names("names")
                    surname("surname")
                    userId("userId")
                    affiliation("affiliation")
                    phones("phones")
                    emails("emails")
                }
                .securityAttributes(contact.securityAttributes)
                .build()

        assertThat(result, equalTo(Contact(person, contact.securityAttributes)))
    }

    @Test
    fun `building with organization lambda`() {
        val organization = Organization(listOf("names"), listOf("phones"), listOf("emails"))
        val result = ContactBuilder()
                .organization {
                    names("names")
                    phones("phones")
                    emails("emails")
                }
                .securityAttributes(contact.securityAttributes)
                .build()

        assertThat(result, equalTo(Contact(organization, contact.securityAttributes)))
    }

    @Test
    fun `building with service lambda`() {
        val service = Service(listOf("names"), listOf("phones"), listOf("emails"))
        val result = ContactBuilder()
                .service {
                    names("names")
                    phones("phones")
                    emails("emails")
                }
                .securityAttributes(contact.securityAttributes)
                .build()

        assertThat(result, equalTo(Contact(service, contact.securityAttributes)))
    }

    @Test
    fun `building with security lambda`() {
        val result = ContactBuilder()
                .producer(contact.producer)
                .securityAttributes {
                    classification(Classification.U)
                    ownerProducers("USA")
                }
                .build()

        assertThat(result, equalTo(contact))
    }

    @Test
    fun `building with lambda`() {
        val result = ContactBuilder.contact {
            producer(contact.producer)
            securityAttributes(contact.securityAttributes)
        }

        assertThat(result, equalTo(contact))
    }
}
