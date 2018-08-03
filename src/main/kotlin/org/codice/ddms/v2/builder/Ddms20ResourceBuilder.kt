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

import org.codice.ddms.Builder
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
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Description
import org.codice.ddms.v2.summary.GeospatialCoverage
import org.codice.ddms.v2.summary.RelatedResources
import org.codice.ddms.v2.summary.SubjectCoverage
import org.codice.ddms.v2.summary.TemporalCoverage
import org.codice.ddms.v2.summary.VirtualCoverage

fun ddms20(init: Ddms20ResourceBuilder.() -> Unit): Ddms20Resource =
        Ddms20ResourceBuilder().apply(init).build()

class Ddms20ResourceBuilder : Builder<Ddms20Resource> {
    private val identifiers: ArrayList<Identifier> = arrayListOf()
    private val titles: ArrayList<Title> = arrayListOf()
    private val subtitles: ArrayList<Title> = arrayListOf()
    private var description: Description? = null
    private val languages: ArrayList<Language> = arrayListOf()
    private var dates: Dates? = null
    private var rights: Rights? = null
    private val sources: ArrayList<Source> = arrayListOf()
    private val types: ArrayList<Type> = arrayListOf()
    private val creators: ArrayList<Contact> = arrayListOf()
    private val publishers: ArrayList<Contact> = arrayListOf()
    private val contributors: ArrayList<Contact> = arrayListOf()
    private val pointOfContacts: ArrayList<Contact> = arrayListOf()
    private var format: Format? = null
    private lateinit var subjectCoverage: SubjectCoverage
    private val virtualCoverages: ArrayList<VirtualCoverage> = arrayListOf()
    private val temporalCoverages: ArrayList<TemporalCoverage> = arrayListOf()
    private val geospatialCoverages: ArrayList<GeospatialCoverage> = arrayListOf()
    private val relatedResources: ArrayList<RelatedResources> = arrayListOf()
    private lateinit var security: Security

    fun identifiers(vararg identifier: Identifier): Ddms20ResourceBuilder {
        identifiers.addAll(identifier)
        return this
    }

    fun identifiers(identifier: List<Identifier>): Ddms20ResourceBuilder {
        identifiers.addAll(identifier)
        return this
    }

    fun identifier(qualifier: String, value: String) = identifiers(Identifier(qualifier, value))

    fun titles(vararg title: Title): Ddms20ResourceBuilder {
        titles.addAll(title)
        return this
    }

    fun titles(title: List<Title>): Ddms20ResourceBuilder {
        titles.addAll(title)
        return this
    }

    fun title(title: String, init: SecurityAttributeBuilder.() -> Unit): Ddms20ResourceBuilder {
        val securityAttributes = SecurityAttributeBuilder().apply(init).build()
        return titles(Title(securityAttributes, title))
    }

    fun title(title: String, securityAttributes: SecurityAttributes): Ddms20ResourceBuilder {
        return titles(Title(securityAttributes, title))
    }

    fun subtitles(vararg subtitle: Title): Ddms20ResourceBuilder {
        subtitles.addAll(subtitle)
        return this
    }

    fun subtitles(subtitle: List<Title>): Ddms20ResourceBuilder {
        subtitles.addAll(subtitle)
        return this
    }

    fun subtitle(subtitle: String, init: SecurityAttributeBuilder.() -> Unit): Ddms20ResourceBuilder {
        val securityAttributes = SecurityAttributeBuilder().apply(init).build()
        return subtitles(Title(securityAttributes, subtitle))
    }

    fun subtitle(subtitle: String, securityAttributes: SecurityAttributes): Ddms20ResourceBuilder {
        return subtitles(Title(securityAttributes, subtitle))
    }

    fun description(description: Description): Ddms20ResourceBuilder {
        this.description = description
        return this
    }

    fun description(description: String, init: SecurityAttributeBuilder.() -> Unit): Ddms20ResourceBuilder {
        val securityAttributes = SecurityAttributeBuilder().apply(init).build()
        return description(Description(securityAttributes, description))
    }

    fun description(description: String, securityAttributes: SecurityAttributes): Ddms20ResourceBuilder {
        return description(Description(securityAttributes, description))
    }

    fun languages(vararg language: Language): Ddms20ResourceBuilder {
        languages.addAll(language)
        return this
    }

    fun languages(language: List<Language>): Ddms20ResourceBuilder {
        languages.addAll(language)
        return this
    }

    fun language(qualifier: String, value: String) = languages(Language(qualifier, value))

    fun dates(dates: Dates): Ddms20ResourceBuilder {
        this.dates = dates
        return this
    }

    fun dates(created: String, posted: String, validTil: String, infoCutOff: String) =
            dates(Dates(created, posted, validTil, infoCutOff))

    fun rights(rights: Rights): Ddms20ResourceBuilder {
        this.rights = rights
        return this
    }

    fun rights(privacyAct: Boolean, intellectualProperty: Boolean, copyright: Boolean) =
            rights(Rights(privacyAct, intellectualProperty, copyright))

    fun sources(vararg source: Source): Ddms20ResourceBuilder {
        sources.addAll(source)
        return this
    }

    fun sources(source: List<Source>): Ddms20ResourceBuilder {
        sources.addAll(source)
        return this
    }

    fun source(qualifier: String, value: String, schema: String, href: String) =
            sources(Source(qualifier, value, schema, href))

    fun types(vararg type: Type): Ddms20ResourceBuilder {
        types.addAll(type)
        return this
    }

    fun types(type: List<Type>): Ddms20ResourceBuilder {
        types.addAll(type)
        return this
    }

    fun type(qualifier: String, value: String) = types(Type(qualifier, value))

    fun creators(vararg creator: Contact): Ddms20ResourceBuilder {
        creators.addAll(creator)
        return this
    }

    fun creators(creator: List<Contact>): Ddms20ResourceBuilder {
        creators.addAll(creator)
        return this
    }

    fun creator(init: ContactBuilder.() -> Unit): Ddms20ResourceBuilder {
        val contact = ContactBuilder().apply(init).build()
        return creators(contact)
    }

    fun publishers(vararg publisher: Contact): Ddms20ResourceBuilder {
        publishers.addAll(publisher)
        return this
    }

    fun publishers(publisher: List<Contact>): Ddms20ResourceBuilder {
        publishers.addAll(publisher)
        return this
    }

    fun publisher(init: ContactBuilder.() -> Unit): Ddms20ResourceBuilder {
        val contact = ContactBuilder().apply(init).build()
        return publishers(contact)
    }

    fun contributors(vararg contributor: Contact): Ddms20ResourceBuilder {
        contributors.addAll(contributor)
        return this
    }

    fun contributors(contributor: List<Contact>): Ddms20ResourceBuilder {
        contributors.addAll(contributor)
        return this
    }

    fun contributor(init: ContactBuilder.() -> Unit): Ddms20ResourceBuilder {
        val contact = ContactBuilder().apply(init).build()
        return contributors(contact)
    }

    fun pointOfContacts(vararg pointOfContact: Contact): Ddms20ResourceBuilder {
        pointOfContacts.addAll(pointOfContact)
        return this
    }

    fun pointOfContacts(pointOfContact: List<Contact>): Ddms20ResourceBuilder {
        pointOfContacts.addAll(pointOfContact)
        return this
    }

    fun pointOfContact(init: ContactBuilder.() -> Unit): Ddms20ResourceBuilder {
        val contact = ContactBuilder().apply(init).build()
        return pointOfContacts(contact)
    }

    fun format(format: Format): Ddms20ResourceBuilder {
        this.format = format
        return this
    }

    fun format(mimeType: String, extent: Extent, medium: String) =
            format(Format(mimeType, extent, medium))

    fun subjectCoverage(subjectCoverage: SubjectCoverage): Ddms20ResourceBuilder {
        this.subjectCoverage = subjectCoverage
        return this
    }

    fun subjectCoverage(init: SubjectCoverageBuilder.() -> Unit): Ddms20ResourceBuilder {
        val subjectCoverage = SubjectCoverageBuilder().apply(init).build()
        return subjectCoverage(subjectCoverage)
    }

    fun virtualCoverages(vararg virtualCoverage: VirtualCoverage): Ddms20ResourceBuilder {
        virtualCoverages.addAll(virtualCoverage)
        return this
    }

    fun virtualCoverages(virtualCoverage: List<VirtualCoverage>): Ddms20ResourceBuilder {
        virtualCoverages.addAll(virtualCoverage)
        return this
    }

    fun virtualCoverage(address: String, protocol: String) =
            virtualCoverages(VirtualCoverage(address, protocol))

    fun temporalCoverages(vararg temporalCoverage: TemporalCoverage): Ddms20ResourceBuilder {
        temporalCoverages.addAll(temporalCoverage)
        return this
    }

    fun temporalCoverages(temporalCoverage: List<TemporalCoverage>): Ddms20ResourceBuilder {
        temporalCoverages.addAll(temporalCoverage)
        return this
    }

    fun temporalCoverage(name: String, start: String, end: String) =
            temporalCoverages(TemporalCoverage(name, start, end))

    fun geospatialCoverages(vararg geospatialCoverage: GeospatialCoverage): Ddms20ResourceBuilder {
        geospatialCoverages.addAll(geospatialCoverage)
        return this
    }

    fun geospatialCoverages(geospatialCoverage: List<GeospatialCoverage>): Ddms20ResourceBuilder {
        geospatialCoverages.addAll(geospatialCoverage)
        return this
    }

    fun geospatialCoverage(init: GeospatialCoverageBuilder.() -> Unit): Ddms20ResourceBuilder {
        val geospatialCoverage = GeospatialCoverageBuilder().apply(init).build()
        return geospatialCoverages(geospatialCoverage)
    }

    fun relatedResources(vararg resources: RelatedResources): Ddms20ResourceBuilder {
        relatedResources.addAll(resources)
        return this
    }

    fun relatedResources(resources: List<RelatedResources>): Ddms20ResourceBuilder {
        relatedResources.addAll(resources)
        return this
    }

    fun relatedResources(init: RelatedResourcesBuilder.() -> Unit): Ddms20ResourceBuilder {
        val relatedResources = RelatedResourcesBuilder().apply(init).build()
        return relatedResources(relatedResources)
    }

    fun security(security: Security): Ddms20ResourceBuilder {
        this.security = security
        return this
    }

    fun security(securityAttributes: SecurityAttributes): Ddms20ResourceBuilder {
        this.security = Security(securityAttributes)
        return this
    }

    fun security(init: SecurityAttributeBuilder.() -> Unit): Ddms20ResourceBuilder {
        val securityAttributes = SecurityAttributeBuilder().apply(init).build()
        security = Security(securityAttributes)
        return this
    }

    override fun build(): Ddms20Resource =
            Ddms20Resource(identifiers,
                    titles,
                    subjectCoverage,
                    security,
                    subtitles,
                    description,
                    languages,
                    dates,
                    rights,
                    sources,
                    types,
                    creators,
                    publishers,
                    contributors,
                    pointOfContacts,
                    format,
                    virtualCoverages,
                    temporalCoverages,
                    geospatialCoverages,
                    relatedResources)
}
