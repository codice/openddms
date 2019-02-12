/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.writer

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter
import org.codice.ddms.DdmsDate
import org.codice.ddms.gml.v3.SrsAttributes
import org.codice.ddms.gml.v3.builder.LinearRingBuilder
import org.codice.ddms.gml.v3.builder.PointBuilder
import org.codice.ddms.gml.v3.builder.PolygonBuilder
import org.codice.ddms.gml.v3.builder.PositionBuilder
import org.codice.ddms.gml.v3.builder.SrsAttributesBuilder.Companion.srsAttributes
import org.codice.ddms.v2.Ddms20Resource
import org.codice.ddms.v2.builder.resource.ContactBuilder
import org.codice.ddms.v2.builder.Ddms20ResourceBuilder
import org.codice.ddms.v2.builder.summary.GeospatialCoverageBuilder
import org.codice.ddms.v2.builder.summary.RelatedResourceBuilder
import org.codice.ddms.v2.builder.summary.RelatedResourcesBuilder
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder
import org.codice.ddms.v2.builder.summary.SubjectCoverageBuilder
import org.codice.ddms.v2.builder.Ddms20ResourceBuilder.Companion.ddms20
import org.codice.ddms.v2.builder.summary.geospatial.BoundingGeometryBuilder
import org.codice.ddms.v2.builder.summary.geospatial.GeographicIdentifierBuilder
import org.codice.ddms.v2.builder.summary.geospatial.PostalAddressBuilder
import org.codice.ddms.v2.builder.summary.geospatial.VerticalExtentBuilder
import org.codice.ddms.v2.builder.producers.OrganizationBuilder
import org.codice.ddms.v2.builder.producers.PersonBuilder
import org.codice.ddms.v2.builder.producers.ServiceBuilder
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder.Companion.securityAttributes
import org.codice.ddms.v2.format.Extent
import org.codice.ddms.v2.resource.Dates
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.Description
import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResources
import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import javax.xml.stream.XMLOutputFactory

class Ddms20XmlWriterTest {
    private val xmlFactory = XMLOutputFactory.newFactory()
    private val ddms20Snapshot = javaClass.getResourceAsStream("/snapshots/DDMS_20_Full_Snapshot.xml")
            .bufferedReader().use { it.readText() }
            .replace("\r\n", "\n") //temporary fix for Windows

    @Test
    fun `all elements and attributes using builders`() {
        val securityMarkings = SecurityAttributeBuilder()
                .classification(Classification.U)
                .ownerProducers("ownerProducer")
                .sciControls("sciControl")
                .sarIdentifiers("sarIdentifier")
                .disseminationControls("disseminationControl")
                .fgiSourceOpen("fgiSourceOpen")
                .fgiSourceProtected("fgiSourceProtected")
                .releasableTo("releasableTo")
                .nonIcMarkings("nonIcMarkings")
                .classifiedBy("classifiedBy")
                .derivativelyClassifiedBy("derivativelyClassifiedBy")
                .classificationReason("classificationReason")
                .derivedFrom("derivedFrom")
                .declassDate(DdmsDate("2017-11-02"))
                .declassEvent("declassEvent")
                .declassException("declassException")
                .typeOfExemptedSource("typeOfExemptedSource")
                .dateOfExemptedSource(DdmsDate("2017-11-02"))
                .declassManualReview(true)
                .build()

        val date = DdmsDate("2017-11-02T10:55:24.225-07:00")
        val srsAttributes = SrsAttributes("srsName", 2, listOf("axisLabels"), listOf("uomLabels"))

        val ddms20 = Ddms20ResourceBuilder()
                .identifier("org:codice:ddms", "test")
                .titles(Title(securityMarkings, "Full DDMS 2.0 Document"))
                .subtitles(Title(securityMarkings, "A Testing Document"))
                .description(Description(securityMarkings, "Testing every element/attribute"))
                .language("ISO 639-1", "EN")
                .dates(Dates(date, date, date, date))
                .rights(privacyAct = true, intellectualProperty = true, copyright = true)
                .source("qualifier", "value", "schema", "href")
                .type("qualifier", "value")
                .creators(ContactBuilder()
                        .producer(PersonBuilder()
                                .names("John")
                                .surname("Smith")
                                .userId("userId")
                                .affiliation("Employee")
                                .phones("555-555-5555")
                                .emails("john.smith@codice.org").build()
                        ).securityAttributes(securityMarkings)
                        .build())
                .contributors(ContactBuilder()
                        .producer(ServiceBuilder()
                                .names("Development")
                                .phones("555-555-5555")
                                .emails("dev@codice.org")
                                .build())
                        .securityAttributes(securityMarkings)
                        .build())
                .publishers(ContactBuilder()
                        .producer(OrganizationBuilder()
                                .names("Codice")
                                .phones("555-555-5555")
                                .emails("info@codice.org")
                                .build())
                        .securityAttributes(securityMarkings)
                        .build())
                .pointOfContacts(ContactBuilder()
                        .producer(PersonBuilder()
                                .names("John")
                                .surname("Smith")
                                .userId("userId")
                                .affiliation("Employee")
                                .phones("555-555-5555")
                                .emails("john.smith@codice.org").build()
                        ).securityAttributes(securityMarkings)
                        .build())
                .format("application/xml", Extent("qualifier", "value"), "digital")
                .subjectCoverage(SubjectCoverageBuilder()
                        .categories(Category("test", "org:codice:ddms", "code"))
                        .keywords("testing")
                        .build())
                .virtualCoverage("address", "protocol")
                .temporalCoverage("Current Time", date, date)
                .geospatialCoverages(GeospatialCoverageBuilder()
                        .geographicIdentifiers(GeographicIdentifierBuilder()
                                .names("name")
                                .regions("region")
                                .countryCode("qualifier", "value")
                                .facilityIdentifier("beNumber", "osuffix")
                                .build())
                        .boundingBox(4.0, 3.0, 2.0, 1.0)
                        .boundingGeometries(BoundingGeometryBuilder()
                                .polygons(PolygonBuilder()
                                        .srsAttributes(srsAttributes)
                                        .id("polygon")
                                        .exterior(LinearRingBuilder()
                                                .positions((1..4).map {
                                                    PositionBuilder()
                                                            .srsAttributes(srsAttributes)
                                                            .points(0.0, 0.0)
                                                            .build()
                                                }.toList())
                                                .build())
                                        .build())
                                .points(PointBuilder()
                                        .id("point")
                                        .srsAttributes(srsAttributes)
                                        .position(PositionBuilder()
                                                .srsAttributes(srsAttributes)
                                                .points(0.0, 0.0)
                                                .build())
                                        .build())
                                .build())
                        .postalAddresses(PostalAddressBuilder()
                                .streets("street")
                                .city("city")
                                .state("state")
                                .postalCode("postalCode")
                                .countryCode("qualifier", "value")
                                .build(),
                                PostalAddressBuilder()
                                        .streets("street")
                                        .city("city")
                                        .province("province")
                                        .postalCode("postalCode")
                                        .countryCode("qualifier", "value")
                                        .build())
                        .verticalExtents(VerticalExtentBuilder()
                                .unit(UnitOfMeasure.Foot)
                                .datum(Datum.MSL)
                                .minimum(0.0, UnitOfMeasure.Foot, Datum.MSL)
                                .maximum(10.0, UnitOfMeasure.Foot, Datum.MSL)
                                .build())
                        .build())
                .relatedResources(RelatedResourcesBuilder()
                        .relationship("relationship")
                        .direction(RelatedResources.Direction.Outbound)
                        .securityAttributes(securityMarkings)
                        .resources(RelatedResourceBuilder()
                                .qualifier("qualifier")
                                .value("value")
                                .links(Link("href", "role", "title", "label"))
                                .build())
                        .build())
                .security(Security(securityMarkings))
                .build()

        val result = getDdms20String(ddms20)
        assertThat(result, equalTo(ddms20Snapshot))
    }

    @Test
    fun `all elements and attributes using dsl`() {
        val securityMarkings = securityAttributes {
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

        val srsAttributes = srsAttributes {
            srsName("srsName")
            srsDimension(2)
            axisLabels("axisLabels")
            uomLabels("uomLabels")
        }

        val date = DdmsDate("2017-11-02T10:55:24.225-07:00")

        val ddms20 = ddms20 {
            identifier("org:codice:ddms", "test")
            title("Full DDMS 2.0 Document", securityMarkings)
            subtitle("A Testing Document", securityMarkings)
            description("Testing every element/attribute", securityMarkings)
            language("ISO 639-1", "EN")
            dates(Dates(date, date, date, date))
            rights(privacyAct = true, intellectualProperty = true, copyright = true)
            source("qualifier", "value", "schema", "href")
            type("qualifier", "value")
            creator {
                person {
                    names("John")
                    surname("Smith")
                    userId("userId")
                    affiliation("Employee")
                    phones("555-555-5555")
                    emails("john.smith@codice.org")
                    securityAttributes(securityMarkings)
                }
            }
            contributor {
                service {
                    names("Development")
                    phones("555-555-5555")
                    emails("dev@codice.org")
                    securityAttributes(securityMarkings)
                }
            }
            publisher {
                organization {
                    names("Codice")
                    phones("555-555-5555")
                    emails("info@codice.org")
                    securityAttributes(securityMarkings)
                }
            }
            pointOfContact {
                person {
                    names("John")
                    surname("Smith")
                    userId("userId")
                    affiliation("Employee")
                    phones("555-555-5555")
                    emails("john.smith@codice.org")
                    securityAttributes(securityMarkings)
                }
            }
            format("application/xml", Extent("qualifier", "value"), "digital")
            subjectCoverage {
                category("test", "org:codice:ddms", "code")
                keywords("testing")
            }
            virtualCoverage("address", "protocol")
            temporalCoverage("Current Time", date, date)
            geospatialCoverage {
                geographicIdentifier {
                    names("name")
                    regions("region")
                    countryCode("qualifier", "value")
                    facilityIdentifier("beNumber", "osuffix")
                }
                boundingBox(4.0, 3.0, 2.0, 1.0)
                boundingGeometry {
                    polygon {
                        srsAttributes(srsAttributes)
                        id("polygon")
                        exterior {
                            (1..4).map {
                                position {
                                    srsAttributes(srsAttributes)
                                    points(0.0, 0.0)
                                }
                            }
                        }
                    }
                    point {
                        id("point")
                        srsAttributes(srsAttributes)
                        position {
                            srsAttributes(srsAttributes)
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
            }
            relatedResources {
                relationship("relationship")
                direction(RelatedResources.Direction.Outbound)
                securityAttributes(securityMarkings)
                resource {
                    qualifier("qualifier")
                    value("value")
                    link {
                        role("role")
                        label("label")
                        title("title")
                        href("href")
                    }
                }
            }
            security(securityMarkings)
        }

        val result = getDdms20String(ddms20)
        assertThat(result, equalTo(ddms20Snapshot))
    }

    private fun getDdms20String(ddms20: Ddms20Resource): String {
        val output = ByteArrayOutputStream()
        val xmlWriter = IndentingXMLStreamWriter(xmlFactory.createXMLStreamWriter(output, "UTF-8"))
        xmlWriter.setIndentStep("    ")
        Ddms20XmlWriter(ddms20, xmlWriter).write()
        return String(output.toByteArray(), StandardCharsets.UTF_8)
    }
}
