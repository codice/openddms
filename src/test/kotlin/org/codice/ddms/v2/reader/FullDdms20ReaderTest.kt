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

import org.codice.ddms.DdmsDate
import org.codice.ddms.DdmsResource
import org.codice.ddms.v2.builder.resource.ContactBuilder.Companion.contact
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder.Companion.securityAttributes
import org.codice.ddms.v2.builder.summary.GeospatialCoverageBuilder.Companion.geospatialCoverage
import org.codice.ddms.v2.builder.summary.RelatedResourcesBuilder.Companion.relatedResources
import org.codice.ddms.v2.builder.summary.SubjectCoverageBuilder.Companion.subjectCoverage
import org.codice.ddms.v2.format.Extent
import org.codice.ddms.v2.format.Format
import org.codice.ddms.v2.resource.Dates
import org.codice.ddms.v2.resource.Identifier
import org.codice.ddms.v2.resource.Language
import org.codice.ddms.v2.resource.Rights
import org.codice.ddms.v2.resource.Source
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.resource.Type
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.summary.Description
import org.codice.ddms.v2.summary.RelatedResources
import org.codice.ddms.v2.summary.TemporalCoverage
import org.codice.ddms.v2.summary.VirtualCoverage
import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import javax.xml.stream.XMLInputFactory

class FullDdms20ReaderTest {
    private val ddms20: DdmsResource

    private val time = DdmsDate("2017-11-02T10:55:24.225-07:00")

    private val securityAttributes = securityAttributes {
        classification(Classification.U)
        ownerProducers("ownerProducer")
        sciControls("sciControl")
        sarIdentifiers("sarIdentifier")
        disseminationControls("disseminationControl")
        fgiSourceOpen("fgiSourceOpen")
        fgiSourceProtected("fgiSourceProtected")
        releasableTo("releasableTo")
        nonIcMarkings("nonIcMarkings")
        classifiedBy("classifiedBy")
        derivativelyClassifiedBy("derivativelyClassifiedBy")
        classificationReason("classificationReason")
        derivedFrom("derivedFrom")
        declassDate(DdmsDate("2017-11-02"))
        declassEvent("declassEvent")
        declassException("declassException")
        typeOfExemptedSource("typeOfExemptedSource")
        dateOfExemptedSource(DdmsDate("2017-11-02"))
        declassManualReview(true)
    }

    init {
        val inputStream = javaClass.getResourceAsStream("/valid/DDMS_20_Full.xml")
        val xmlReader = XMLInputFactory.newFactory().createXMLStreamReader(inputStream)
        ddms20 = Ddms20XmlReader(xmlReader).read()
    }

    @Test
    fun `has identifier`() {
        assertThat(ddms20.identifiers.size, equalTo(1))
        assertThat(ddms20.identifiers.first(), equalTo(Identifier("org:codice:ddms", "test")))
    }

    @Test
    fun `has title`() {
        assertThat(ddms20.titles.size, equalTo(1))
        assertThat(ddms20.titles.first(), equalTo(Title(securityAttributes, "Full DDMS 2.0 Document")))
    }

    @Test
    fun `has subtitle`() {
        assertThat(ddms20.subtitles.size, equalTo(1))
        assertThat(ddms20.subtitles.first(), equalTo(Title(securityAttributes, "A Testing Document")))
    }

    @Test
    fun `has description`() {
        assertThat(ddms20.description,
                equalTo(Description(securityAttributes, "Testing every element/attribute.")))
    }

    @Test
    fun `has language`() {
        assertThat(ddms20.languages.size, equalTo(1))
        assertThat(ddms20.languages.first(), equalTo(Language("ISO 639-1", "EN")))
    }

    @Test
    fun `has dates`() {
        assertThat(ddms20.dates, equalTo(Dates(time, time, time, time)))
    }

    @Test
    fun `has rights`() {
        assertThat(ddms20.rights, equalTo(Rights(privacyAct = true, intellectualProperty = true, copyright = true)))
    }

    @Test
    fun `has source`() {
        assertThat(ddms20.sources.size, equalTo(1))
        assertThat(ddms20.sources.first(),
                equalTo(Source("qualifier", "value", "schema", "href")))
    }

    @Test
    fun `has type`() {
        assertThat(ddms20.types.size, equalTo(1))
        assertThat(ddms20.types.first(), equalTo(Type("qualifier", "value")))
    }

    @Test
    fun `has creator`() {
        assertThat(ddms20.creators.size, equalTo(1))
        assertThat(ddms20.creators.first(),
                equalTo(
                        contact {
                            securityAttributes(securityAttributes)
                            person {
                                names("John")
                                surname("Smith")
                                userId("userID")
                                affiliation("Employee")
                                phones("555-555-5555")
                                emails("john.smith@codice.org")
                            }
                        }))
    }

    @Test
    fun `has publisher`() {
        assertThat(ddms20.publishers.size, equalTo(1))
        assertThat(ddms20.publishers.first(),
                equalTo(
                        contact {
                            securityAttributes(securityAttributes)
                            organization {
                                names("Codice")
                                phones("555-555-5555")
                                emails("info@codice.org")
                            }
                        }))
    }

    @Test
    fun `has contributor`() {
        assertThat(ddms20.contributors.size, equalTo(1))
        assertThat(ddms20.contributors.first(),
                equalTo(
                        contact {
                            securityAttributes(securityAttributes)
                            service {
                                names("Development")
                                phones("555-555-5555")
                                emails("dev@codice.org")
                            }
                        }))
    }

    @Test
    fun `has pointOfContact`() {
        assertThat(ddms20.pointOfContacts.size, equalTo(1))
        assertThat(ddms20.pointOfContacts.first(),
                equalTo(
                        contact {
                            securityAttributes(securityAttributes)
                            person {
                                names("John")
                                surname("Smith")
                                userId("userID")
                                affiliation("Employee")
                                phones("555-555-5555")
                                emails("john.smith@codice.org")
                            }
                        }))
    }

    @Test
    fun `has format`() {
        assertThat(ddms20.format,
                equalTo(Format("application/xml", Extent("qualifier", "value"), "digital")))
    }

    @Test
    fun `has subjectCoverage`() {
        assertThat(ddms20.subjectCoverage, equalTo(
                subjectCoverage {
                    category("test", "org:codice:ddms", "code")
                    keywords("testing")
                }))
    }

    @Test
    fun `has virtualCoverage`() {
        assertThat(ddms20.virtualCoverages.size, equalTo(1))
        assertThat(ddms20.virtualCoverages.first(), equalTo(VirtualCoverage("address", "protocol")))
    }

    @Test
    fun `has temporalCoverage`() {
        assertThat(ddms20.temporalCoverages.size, equalTo(1))
        assertThat(ddms20.temporalCoverages.first(), equalTo(TemporalCoverage("Current Time", time, time)))
    }

    @Test
    fun `has geospatialCoverage`() {
        assertThat(ddms20.geospatialCoverages.size, equalTo(1))
        assertThat(ddms20.geospatialCoverages.first(), equalTo(
                geospatialCoverage {
                    geographicIdentifier {
                        names("name")
                        regions("region")
                        countryCode("qualifier", "value")
                        facilityIdentifier("beNumber", "osuffix")
                    }
                    boundingBox(1.0, 2.0, 4.0, 3.0)
                    boundingGeometry {
                        polygon {
                            srsAttributes {
                                srsName("srsName")
                                srsDimension(2)
                                axisLabels("axisLabels")
                                uomLabels("uomLabels")
                            }
                            id("polygon")
                            exterior {
                                position {
                                    srsAttributes {
                                        srsName("srsName")
                                        srsDimension(2)
                                        axisLabels("axisLabels")
                                        uomLabels("uomLabels")
                                    }
                                    points(0.0, 0.0)
                                }
                                position {
                                    srsAttributes {
                                        srsName("srsName")
                                        srsDimension(2)
                                        axisLabels("axisLabels")
                                        uomLabels("uomLabels")
                                    }
                                    points(0.0, 0.0)
                                }
                                position {
                                    srsAttributes {
                                        srsName("srsName")
                                        srsDimension(2)
                                        axisLabels("axisLabels")
                                        uomLabels("uomLabels")
                                    }
                                    points(0.0, 0.0)
                                }
                                position {
                                    srsAttributes {
                                        srsName("srsName")
                                        srsDimension(2)
                                        axisLabels("axisLabels")
                                        uomLabels("uomLabels")
                                    }
                                    points(0.0, 0.0)
                                }
                            }
                        }
                        point {
                            srsAttributes {
                                srsName("srsName")
                                srsDimension(2)
                                axisLabels("axisLabels")
                                uomLabels("uomLabels")
                            }
                            id("point")
                            position {
                                srsAttributes {
                                    srsName("srsName")
                                    srsDimension(2)
                                    axisLabels("axisLabels")
                                    uomLabels("uomLabels")
                                }
                                points(0.0, 0.0)
                            }
                        }
                    }
                    postalAddress {
                        streets("street")
                        city("city")
                        state("state")
                        postalCode("postalCode")
                        countryCode("qualifier", "value")
                    }
                    postalAddress {
                        streets("street")
                        city("city")
                        province("province")
                        postalCode("postalCode")
                        countryCode("qualifier", "value")
                    }
                    verticalExtent {
                        unit(UnitOfMeasure.Foot)
                        datum(Datum.MSL)
                        minimum(0.0, UnitOfMeasure.Foot, Datum.MSL)
                        maximum(10.0, UnitOfMeasure.Foot, Datum.MSL)
                    }
                }))
    }

    @Test
    fun `has relatedResources`() {
        assertThat(ddms20.relatedResources.size, equalTo(1))
        assertThat(ddms20.relatedResources.first(), equalTo(
                relatedResources {
                    securityAttributes(securityAttributes)
                    relationship("relationship")
                    direction(RelatedResources.Direction.Outbound)
                    resource {
                        qualifier("qualifier")
                        value("value")
                        link {
                            href("href")
                            role("role")
                            title("title")
                            label("label")
                        }
                    }
                }))
    }

    @Test
    fun `has security`() {
        assertThat(ddms20.security, equalTo(Security(securityAttributes)))
    }
}
