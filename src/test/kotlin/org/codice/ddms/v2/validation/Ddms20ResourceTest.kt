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
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.Ddms20Resource
import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.Identifier
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.resource.producers.Organization
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.SubjectCoverage
import org.junit.Test

class Ddms20ResourceTest {
    private val identifier = Identifier("org:codice:ddms", "test")
    private val title = Title(SecurityAttributes(Classification.U, listOf("USA")), "Title")
    private val contact = Contact(Organization(listOf("Codice")))
    private val subjectCoverage = SubjectCoverage(listOf(Category("label")), emptyList())
    private val security = Security(SecurityAttributes(Classification.U, listOf("USA")))

    @Test
    fun `a Ddms20Resource with an identifier, Title, Creator is valid`() {
        Ddms20Resource(listOf(identifier),
                listOf(title),
                subjectCoverage,
                security,
                creators = listOf(contact))
    }

    @Test
    fun `a Ddms20Resource with an identifier, Title, Publisher is valid`() {
        Ddms20Resource(listOf(identifier),
                listOf(title),
                subjectCoverage,
                security,
                publishers = listOf(contact))
    }

    @Test
    fun `a Ddms20Resource with an identifier, Title, Contributor is valid`() {
        Ddms20Resource(listOf(identifier),
                listOf(title),
                subjectCoverage,
                security,
                contributors = listOf(contact))
    }

    @Test
    fun `a Ddms20Resource with an identifier, Title, PointOfContact is valid`() {
        Ddms20Resource(listOf(identifier),
                listOf(title),
                subjectCoverage,
                security,
                pointOfContacts = listOf(contact))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Ddms20Resource without an Identifier is invalid`() {
        Ddms20Resource(emptyList(),
                listOf(title),
                subjectCoverage,
                security,
                creators = listOf(contact))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Ddms20Resource without a Title is invalid`() {
        Ddms20Resource(listOf(identifier),
                emptyList(),
                subjectCoverage,
                security,
                creators = listOf(contact))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Ddms20Resource without a Contact is invalid`() {
        Ddms20Resource(listOf(identifier),
                listOf(title),
                subjectCoverage,
                security)
    }
}
