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
import org.codice.ddms.v2.builder.contact
import org.codice.ddms.v2.builder.securityAttributes
import org.codice.ddms.v2.builder.subjectCoverage
import org.codice.ddms.v2.format.Extent
import org.codice.ddms.v2.format.Format
import org.codice.ddms.v2.resource.Identifier
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.resource.Type
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import javax.xml.stream.XMLInputFactory

class SampleDdms20ReaderTest {
    private val ddms20: DdmsResource

    init {
        val inputStream = javaClass.getResourceAsStream("/valid/DDMS_20_Sample.xml")
        val xmlFactory = XMLInputFactory.newFactory()
        val xmlReader = xmlFactory.createXMLStreamReader(inputStream)
        ddms20 = Ddms20XmlReader(xmlReader).read()
    }

    @Test
    fun `has identifiers`() {
        assertThat(ddms20.identifiers.size, equalTo(3))
        ddms20.identifiers.forEachIndexed { index, identifier ->
            assertThat(identifier, equalTo(Identifier("org:codice:ddms", "identifier ${index + 1}")))
        }
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
                        "Sample DDMS 2.0")))
    }

    @Test
    fun `has subtitle`() {
        assertThat(ddms20.subtitles.size, equalTo(1))
        assertThat(ddms20.subtitles.first(),
                equalTo(Title(
                        securityAttributes {
                            classification(Classification.U)
                            ownerProducers("USA")
                        },
                        "Subtitle")))
    }

    @Test
    fun `has no description`() {
        assertThat(ddms20.description, nullValue())
    }

    @Test
    fun `has languages`() {
        assertThat(ddms20.languages.size, equalTo(1))
        assertThat(ddms20.languages.first().qualifier,
                equalTo("urn:iso:std:iso:639:-2:ed-1:v1:en"))
        assertThat(ddms20.languages.first().value,
                equalTo("eng"))
    }

    @Test
    fun `has dates`() {
        assertThat(ddms20.dates?.created,
                equalTo("2016-08-01T09:30:06.613-04:00"))
        assertThat(ddms20.dates?.infoCutOff,
                equalTo("2018-02-07T12:27:30Z"))
        assertThat(ddms20.dates?.posted,
                equalTo("2018-02-07T12:27:30Z"))
        assertThat(ddms20.dates?.validTil,
                equalTo("2017-08-01T09:30:06.613-04:00"))
    }

    @Test
    fun `has no rights`() {
        assertThat(ddms20.rights, nullValue())
    }

    @Test
    fun `has sources`() {
        assertThat(ddms20.sources.size, equalTo(1))
        assertThat(ddms20.sources.first().qualifier,
                equalTo("http://purl.org/dc/terms/URI"))
        assertThat(ddms20.sources.first().href,
                equalTo("http://www.opengis.net/sensorML/1.0.1"))
        assertThat(ddms20.sources.first().schema,
                equalTo("XSD"))
        assertThat(ddms20.sources.first().value,
                equalTo("http://localhost:8080"))
    }

    @Test
    fun `has types`() {
        assertThat(ddms20.types.size, equalTo(3))
        ddms20.types.forEachIndexed { index, type ->
            assertThat(type, equalTo(Type("org:codice:ddms", "type ${index + 1}")))
        }
    }

    @Test
    fun `has a creator`() {
        assertThat(ddms20.creators.size, equalTo(1))
        assertThat(ddms20.creators.first(), equalTo(contact {
            service {
                names("Service")
            }
        }))
    }

    @Test
    fun `has publishers`() {
        assertThat(ddms20.publishers.size, equalTo(2))
        assertThat(ddms20.publishers.first(),
                equalTo(contact {
                    service {
                        names("Service")
                    }
                }))
    }

    @Test
    fun `has contributors`() {
        assertThat(ddms20.contributors.size, equalTo(1))
        assertThat(ddms20.contributors.first(),
                equalTo(contact {
                    organization {
                        names("Codice")
                    }
                }))
    }

    @Test
    fun `has pointOfContacts`() {
        assertThat(ddms20.pointOfContacts.size, equalTo(1))
        assertThat(ddms20.pointOfContacts.first(),
                equalTo(contact {
                    person {
                        names("")
                        surname("")
                        affiliation("Affiliation")
                        phones("555-555-5555")
                        emails("")
                    }
                }))
    }

    @Test
    fun `has format`() {
        assertThat(ddms20.format,
                equalTo(Format("text/xml", Extent(), "digital")))
    }

    @Test
    fun `has a subjectCoverage`() {
        assertThat(ddms20.subjectCoverage, equalTo(subjectCoverage {
            categories(Category("EC16", "Mission Name"))
            keywords("McQ", "OmniSense", "UGS", "unattended", "ground", "sensor", "detector", "camera",
                    "seismic", "image", "audio", "acoustic", "magnetic", "passive", "infrared", "IR", "pir")
        }))
    }

    @Test
    fun `has no virtualCoverages`() {
        assertThat(ddms20.virtualCoverages.size, equalTo(0))
    }

    @Test
    fun `has temporalCoverages`() {
        assertThat(ddms20.temporalCoverages.size, equalTo(1))
        assertThat(ddms20.temporalCoverages.first().name,
                equalTo("sensorDescriptionValidTime"))
        assertThat(ddms20.temporalCoverages.first().start,
                equalTo("2016-08-01T09:30:06.613-04:00"))
        assertThat(ddms20.temporalCoverages.first().end,
                equalTo("2017-08-01T09:30:06.613-04:00"))
    }

    @Test
    fun `has geospatialCoverages`() {
        assertThat(ddms20.geospatialCoverages.size, equalTo(1))
        assertThat(ddms20.geospatialCoverages.first().geographicIdentifiers.size, equalTo(1))
        assertThat(ddms20.geospatialCoverages.first().geographicIdentifiers.first().names.first(),
                equalTo("Location"))
        assertThat(ddms20.geospatialCoverages.first().geographicIdentifiers.first().countryCodes.first(),
                equalTo(
                        CountryCode("org:codice:ddms", "USA")))
        assertThat(ddms20.geospatialCoverages.first().boundingGeometries.size, equalTo(1))
    }

    @Test
    fun `has relatedResources`() {
        assertThat(ddms20.relatedResources.size, equalTo(1))
        assertThat(ddms20.relatedResources.first().resources.size, equalTo(2))
        assertThat(ddms20.relatedResources.first().resources.first().qualifier,
                equalTo("http://purl.org/dc/terms/URI"))
        assertThat(ddms20.relatedResources.first().resources.first().value,
                equalTo("http://localhost:8080"))
        assertThat(ddms20.relatedResources.first().resources.first().links.size, equalTo(1))
        assertThat(ddms20.relatedResources.first().resources.first().links.first().href,
                equalTo("http://localhost:8080"))
        assertThat(ddms20.relatedResources.first().resources.first().links.first().title,
                equalTo("org:codice:ddms"))
        assertThat(ddms20.relatedResources.first().resources.first().links.first().type,
                equalTo("locator"))
    }

    @Test
    fun `has security`() {
        assertThat(ddms20.security, equalTo(Security(securityAttributes {
            classification(Classification.U)
            ownerProducers("USA")
        })))
    }
}
