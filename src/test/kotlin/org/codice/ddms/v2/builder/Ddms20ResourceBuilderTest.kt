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
package org.codice.ddms.v2.builder

import org.codice.ddms.v2.Ddms20Resource
import org.codice.ddms.v2.format.Extent
import org.codice.ddms.v2.format.Format
import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.Dates
import org.codice.ddms.v2.resource.Identifier
import org.codice.ddms.v2.resource.Language
import org.codice.ddms.v2.resource.Rights
import org.codice.ddms.v2.resource.Source
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.resource.Type
import org.codice.ddms.v2.resource.producers.Organization
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Description
import org.codice.ddms.v2.summary.GeospatialCoverage
import org.codice.ddms.v2.summary.RelatedResources
import org.codice.ddms.v2.summary.SubjectCoverage
import org.codice.ddms.v2.summary.TemporalCoverage
import org.codice.ddms.v2.summary.VirtualCoverage
import org.codice.ddms.v2.summary.geospatial.BoundingBox
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.time.OffsetDateTime

class Ddms20ResourceBuilderTest {
    private val date = OffsetDateTime.now().toString()

    val ddms20 = Ddms20Resource(
            listOf(Identifier("qualifier", "value")),
            listOf(Title(SecurityAttributes(Classification.U, listOf("USA")), "Title")),
            SubjectCoverage(listOf(), listOf("keywords")),
            Security(SecurityAttributes(Classification.U, listOf("USA"))),
            listOf(Title(SecurityAttributes(Classification.U, listOf("USA")), "Subtitle")),
            Description(SecurityAttributes(Classification.U, listOf("USA")), "Description"),
            listOf(Language("qualifier")),
            Dates(date),
            Rights(true),
            listOf(Source("qualifier")),
            listOf(Type("qualifier")),
            listOf(Contact(Organization(listOf("Codice")))),
            listOf(Contact(Organization(listOf("Codice")))),
            listOf(Contact(Organization(listOf("Codice")))),
            listOf(Contact(Organization(listOf("Codice")))),
            Format("mimeType"),
            listOf(VirtualCoverage("address")),
            listOf(TemporalCoverage("name")),
            listOf(GeospatialCoverage(listOf(),
                    listOf(BoundingBox(0.0, 0.0, 0.0, 0.0)),
                    listOf(), listOf(), listOf())),
            listOf(RelatedResources("relationship")))

    @Test
    fun `building with methods`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with identifier method`() {
        val result = Ddms20ResourceBuilder()
                .identifier("qualifier", "value")
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with title method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .title("Title") {
                    classification(Classification.U)
                    ownerProducers("USA")
                }
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with subtitle method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitle("Subtitle") {
                    classification(Classification.U)
                    ownerProducers("USA")
                }
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with description method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description("Description") {
                    classification(Classification.U)
                    ownerProducers("USA")
                }
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with language method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .language("qualifier", "")
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with dates method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(date, "", "", "")
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with rights method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(true, false, false)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with source method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .source("qualifier", "", "", "")
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with type method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .type("qualifier", "")
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with creator method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creator {
                    organization { names("Codice") }
                }
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with publisher method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publisher { organization { names("Codice") } }
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with contributor method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributor { organization { names("Codice") } }
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with pointOfContact method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContact { organization { names("Codice") } }
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with format method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format("mimeType", Extent(), "")
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with subjectCoverage method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage { keywords("keywords") }
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with virtualCoverage method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverage("address", "")
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with temporalCoverage method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverage("name", "Unknown", "Unknown")
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with geospatialCoverage method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverage { boundingBox(0.0, 0.0, 0.0, 0.0) }
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with relatedResources method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security(ddms20.security)
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources { relationship("relationship") }
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with security method`() {
        val result = Ddms20ResourceBuilder()
                .identifiers(ddms20.identifiers)
                .titles(ddms20.titles)
                .subjectCoverage(ddms20.subjectCoverage)
                .security {
                    classification(Classification.U)
                    ownerProducers("USA")
                }
                .subtitles(ddms20.subtitles)
                .description(ddms20.description!!)
                .languages(ddms20.languages)
                .dates(ddms20.dates!!)
                .rights(ddms20.rights!!)
                .sources(ddms20.sources)
                .types(ddms20.types)
                .creators(ddms20.creators)
                .publishers(ddms20.publishers)
                .contributors(ddms20.contributors)
                .pointOfContacts(ddms20.pointOfContacts)
                .format(ddms20.format!!)
                .virtualCoverages(ddms20.virtualCoverages)
                .temporalCoverages(ddms20.temporalCoverages)
                .geospatialCoverages(ddms20.geospatialCoverages)
                .relatedResources(ddms20.relatedResources)
                .build()

        assertThat(result, equalTo(ddms20))
    }

    @Test
    fun `building with lambda`() {
        val result = ddms20 {
            identifiers(ddms20.identifiers)
            titles(ddms20.titles)
            subjectCoverage(ddms20.subjectCoverage)
            security(ddms20.security)
            subtitles(ddms20.subtitles)
            description(ddms20.description!!)
            languages(ddms20.languages)
            dates(ddms20.dates!!)
            rights(ddms20.rights!!)
            sources(ddms20.sources)
            types(ddms20.types)
            creators(ddms20.creators)
            publishers(ddms20.publishers)
            contributors(ddms20.contributors)
            pointOfContacts(ddms20.pointOfContacts)
            format(ddms20.format!!)
            virtualCoverages(ddms20.virtualCoverages)
            temporalCoverages(ddms20.temporalCoverages)
            geospatialCoverages(ddms20.geospatialCoverages)
            relatedResources(ddms20.relatedResources)
        }

        assertThat(result, equalTo(ddms20))
    }
}
