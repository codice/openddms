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
package org.codice.ddms.v2.reader

import org.codice.ddms.DdmsResource
import org.codice.ddms.v2.builder.resource.ContactBuilder.Companion.contact
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder.Companion.securityAttributes
import org.codice.ddms.v2.builder.summary.SubjectCoverageBuilder.Companion.subjectCoverage
import org.codice.ddms.v2.resource.Identifier
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import javax.xml.stream.XMLInputFactory

class MinimalDdms20ReaderTest {
    private val ddms20: DdmsResource

    init {
        val inputStream = javaClass.getResourceAsStream("/valid/DDMS_20_Minimal.xml")
        val xmlFactory = XMLInputFactory.newFactory()
        val xmlReader = xmlFactory.createXMLStreamReader(inputStream)
        ddms20 = Ddms20XmlReader(xmlReader).read()
    }

    @Test
    fun `has identifiers`() {
        assertThat(ddms20.identifiers.size, equalTo(1))
        assertThat(ddms20.identifiers.first(), equalTo(Identifier("qualifier", "value")))
    }

    @Test
    fun `has title`() {
        assertThat(ddms20.titles.size, equalTo(1))
        assertThat(ddms20.titles.first(),
                equalTo(Title(
                        securityAttributes {
                            classification(Classification.U)
                            ownerProducers("USA")
                        },
                        "Minimal DDMS 2.0 Document")))
    }

    @Test
    fun `has no subtitles`() {
        assertThat(ddms20.subtitles.size, equalTo(0))
    }

    @Test
    fun `has no description`() {
        assertThat(ddms20.description, nullValue())
    }

    @Test
    fun `has no languages`() {
        assertThat(ddms20.languages.size, equalTo(0))
    }

    @Test
    fun `has no dates`() {
        assertThat(ddms20.dates, nullValue())
    }

    @Test
    fun `has no rights`() {
        assertThat(ddms20.rights, nullValue())
    }

    @Test
    fun `has no sources`() {
        assertThat(ddms20.sources.size, equalTo(0))
    }

    @Test
    fun `has no types`() {
        assertThat(ddms20.types.size, equalTo(0))
    }

    @Test
    fun `has a creator`() {
        assertThat(ddms20.creators.size, equalTo(1))
        assertThat(ddms20.creators.first(), equalTo(
                contact {
                    organization {
                        names("Codice")
                    }
                }))
    }

    @Test
    fun `has no publishers`() {
        assertThat(ddms20.publishers.size, equalTo(0))
    }

    @Test
    fun `has no contributors`() {
        assertThat(ddms20.contributors.size, equalTo(0))
    }

    @Test
    fun `has no pointOfContacts`() {
        assertThat(ddms20.pointOfContacts.size, equalTo(0))
    }

    @Test
    fun `has no format`() {
        assertThat(ddms20.format, nullValue())
    }

    @Test
    fun `has a subjectCoverage`() {
        assertThat(ddms20.subjectCoverage, equalTo(
                subjectCoverage {
                    keywords("keyword")
                }))
    }

    @Test
    fun `has no virtualCoverages`() {
        assertThat(ddms20.virtualCoverages.size, equalTo(0))
    }

    @Test
    fun `has no temporalCoverages`() {
        assertThat(ddms20.temporalCoverages.size, equalTo(0))
    }

    @Test
    fun `has no geospatialCoverages`() {
        assertThat(ddms20.geospatialCoverages.size, equalTo(0))
    }

    @Test
    fun `has no relatedResources`() {
        assertThat(ddms20.relatedResources.size, equalTo(0))
    }

    @Test
    fun `has security`() {
        assertThat(ddms20.security, equalTo(Security(
                securityAttributes {
                    classification(Classification.U)
                    ownerProducers("USA")
                })))
    }
}
