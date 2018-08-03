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
package org.codice.ddms.v2.writer

import org.codice.ddms.DdmsResource
import org.codice.ddms.DdmsWriter
import org.codice.ddms.v2.format.Extent
import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.resource.producers.Organization
import org.codice.ddms.v2.resource.producers.Person
import org.codice.ddms.v2.resource.producers.Service
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributeStrings
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResource
import org.codice.ddms.v2.summary.RelatedResources
import org.codice.ddms.v2.summary.geospatial.BoundingBox
import org.codice.ddms.v2.summary.geospatial.BoundingGeometry
import org.codice.ddms.v2.summary.geospatial.CountryCode
import org.codice.ddms.v2.summary.geospatial.FacilityIdentifier
import org.codice.ddms.v2.summary.geospatial.GeographicIdentifier
import org.codice.ddms.v2.summary.geospatial.PostalAddress
import org.codice.ddms.v2.summary.geospatial.Province
import org.codice.ddms.v2.summary.geospatial.State
import org.codice.ddms.v2.summary.geospatial.VerticalDistance
import org.codice.ddms.v2.summary.geospatial.VerticalExtent
import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.codice.ddms.xml.util.XmlConstants.schemaLocation
import org.codice.ddms.xml.util.XmlConstants.xlinkNamespace
import org.codice.ddms.xml.util.XmlConstants.xlinkPrefix
import org.codice.ddms.xml.util.XmlConstants.xsiNamespace
import org.codice.ddms.xml.util.XmlConstants.xsiPrefix
import org.codice.ddms.xml.util.element
import org.codice.ddms.xml.util.emptyElement
import org.codice.ddms.xml.util.namespace
import org.codice.ddms.xml.util.xlinkAttribute
import javax.xml.stream.XMLStreamWriter

class Ddms20XmlWriter(
    private val ddms: DdmsResource,
    private val writer: XMLStreamWriter
) : DdmsWriter, XMLStreamWriter by writer {
    private val ddmsPrefix = "ddms"
    private val ddms20Namespace = "http://metadata.dod.mil/mdr/ns/DDMS/2.0/"
    private val ddms20SchemaLocation = "http://metadata.dod.mil/mdr/ns/DDMS/2.0/DDMS-v2_0.xsd"

    private val ismNamespace = "urn:us:gov:ic:ism:v2"
    private val ismPrefix = "ism"

    private val gmlNamespace = "http://www.opengis.net/gml"
    private val gmlPrefix = "gml"

    override fun write() {
        writeStartDocument()
        ddmsElement("Resource") {
            namespace(ddmsPrefix, ddms20Namespace)
            namespace(ismPrefix, ismNamespace)
            namespace(xsiPrefix, xsiNamespace)

            writeAttribute(xsiNamespace, schemaLocation, ddms20SchemaLocation)

            writeIdentifiers()
            writeTitles()
            writeSubtitles()
            writeDescription()
            writeLanguages()
            writeDates()
            writeRights()
            writeSources()
            writeTypes()
            writeCreators()
            writePublishers()
            writeContributors()
            writePointOfContacts()
            writeFormat()
            writeSubjectCoverage()
            writeVirtualCoverages()
            writeTemporalCoverages()
            writeGeospatialCoverages()
            writeRelatedResources()
            writeSecurity()
        }

        writeEndDocument()
        close()
    }

    private fun writeIdentifiers() {
        for (identifier in ddms.identifiers) {
            ddmsEmptyElement("identifier") {
                ddmsAttribute("qualifier", identifier.qualifier)
                ddmsAttribute("value", identifier.value)
            }
        }
    }

    private fun writeTitles() {
        ddms.titles.forEach { writeTitle(it, "title") }
    }

    private fun writeSubtitles() {
        ddms.subtitles.forEach { writeTitle(it, "subtitle") }
    }

    private fun writeTitle(title: Title, type: String) {
        ddmsElement(type) {
            addSecurityAttributes(title.securityAttributes)
            writeCharacters(title.value)
        }
    }

    private fun writeDescription() {
        ddms.description?.run {
            ddmsElement("description") {
                addSecurityAttributes(securityAttributes)
                writeCharacters(value)
            }
        }
    }

    private fun writeLanguages() {
        for (language in ddms.languages) {
            ddmsEmptyElement("language") {
                ddmsAttribute("qualifier", language.qualifier)
                ddmsAttribute("value", language.value)
            }
        }
    }

    private fun writeDates() {
        ddms.dates?.run {
            ddmsEmptyElement("dates") {
                if (created.isNotBlank()) ddmsAttribute("created", created)
                if (posted.isNotBlank()) ddmsAttribute("posted", posted)
                if (validTil.isNotBlank()) ddmsAttribute("validTil", validTil)
                if (infoCutOff.isNotBlank()) ddmsAttribute("infoCutOff", infoCutOff)
            }
        }
    }

    private fun writeRights() {
        ddms.rights?.run {
            ddmsEmptyElement("rights") {
                ddmsAttribute("privacyAct", privacyAct.toString())
                ddmsAttribute("intellectualProperty", intellectualProperty.toString())
                ddmsAttribute("copyright", privacyAct.toString())
            }
        }
    }

    private fun writeSources() {
        for (source in ddms.sources) {
            ddmsEmptyElement("source") {
                ddmsAttribute("qualifier", source.qualifier)
                ddmsAttribute("value", source.value)
                ddmsAttribute("schemaQualifier", source.schema)
                if (source.href.isNotBlank()) ddmsAttribute("schemaHref", source.href)
            }
        }
    }

    private fun writeTypes() {
        for (type in ddms.types) {
            ddmsEmptyElement("type") {
                ddmsAttribute("qualifier", type.qualifier)
                ddmsAttribute("value", type.value)
            }
        }
    }

    private fun writeCreators() {
        ddms.creators.forEach { writeContact(it, "creator") }
    }

    private fun writePublishers() {
        ddms.publishers.forEach { writeContact(it, "publisher") }
    }

    private fun writeContributors() {
        ddms.contributors.forEach { writeContact(it, "contributor") }
    }

    private fun writePointOfContacts() {
        ddms.pointOfContacts.forEach { writeContact(it, "pointOfContact") }
    }

    private fun writeContact(contact: Contact, type: String) {
        ddmsElement(type) {
            addSecurityAttributes(contact.securityAttributes)

            when (contact.producer) {
                is Organization -> writeOrganization(contact.producer)
                is Person -> writePerson(contact.producer)
                is Service -> writeService(contact.producer)
            }
        }
    }

    private fun writeOrganization(organization: Organization) {
        ddmsElement("Organization") {
            with(organization) {
                names.forEach { ddmsElement("name", it) }
                phones.forEach { ddmsElement("phone", it) }
                emails.forEach { ddmsElement("email", it) }
            }
        }
    }

    private fun writePerson(person: Person) {
        ddmsElement("Person") {
            with(person) {
                names.forEach { ddmsElement("name", it) }
                ddmsElement("surname", surname)

                if (userId.isNotBlank()) {
                    ddmsElement("userID", userId)
                }

                if (affiliation.isNotBlank()) {
                    ddmsElement("affiliation", affiliation)
                }

                phones.forEach { ddmsElement("phone", it) }
                emails.forEach { ddmsElement("email", it) }
            }
        }
    }

    private fun writeService(service: Service) {
        ddmsElement("Service") {
            with(service) {
                names.forEach { ddmsElement("name", it) }
                phones.forEach { ddmsElement("phone", it) }
                emails.forEach { ddmsElement("email", it) }
            }
        }
    }

    private fun writeFormat() {
        ddms.format?.run {
            ddmsElement("format") {
                ddmsElement("Media") {
                    ddmsElement("mimeType", mimeType)
                    if (extent != Extent()) {
                        ddmsEmptyElement("extent") {
                            ddmsAttribute("qualifier", extent.qualifier)
                            ddmsAttribute("value", extent.value)
                        }
                    }
                    if (medium.isNotBlank()) ddmsElement("medium", medium)
                }
            }
        }
    }

    private fun writeSubjectCoverage() {
        ddmsElement("subjectCoverage") {
            ddmsElement("Subject") {
                with(ddms.subjectCoverage) {
                    categories.forEach { writeCategory(it) }
                    keywords.forEach { writeKeyword(it) }
                }
            }
        }
    }

    private fun writeCategory(category: Category) {
        ddmsEmptyElement("category") {
            ddmsAttribute("label", category.label)
            with(category) {
                if (qualifier.isNotBlank()) ddmsAttribute("qualifier", qualifier)
                if (code.isNotBlank()) ddmsAttribute("code", code)
            }
        }
    }

    private fun writeKeyword(keyword: String) {
        ddmsEmptyElement("keyword") {
            ddmsAttribute("value", keyword)
        }
    }

    private fun writeVirtualCoverages() {
        for (virtualCoverage in ddms.virtualCoverages) {
            ddmsEmptyElement("virtualCoverage") {
                with(virtualCoverage) {
                    if (protocol.isNotBlank()) ddmsAttribute("protocol", protocol)
                    if (address.isNotBlank()) ddmsAttribute("address", address)
                }
            }
        }
    }

    private fun writeTemporalCoverages() {
        for (temporalCoverage in ddms.temporalCoverages) {
            ddmsElement("temporalCoverage") {
                ddmsElement("TimePeriod") {
                    ddmsElement("name", temporalCoverage.name)
                    ddmsElement("start", temporalCoverage.start)
                    ddmsElement("end", temporalCoverage.end)
                }
            }
        }
    }

    private fun writeGeospatialCoverages() {
        for (geospatialCoverage in ddms.geospatialCoverages) {
            ddmsElement("geospatialCoverage") {
                ddmsElement("GeospatialExtent") {
                    geospatialCoverage.geographicIdentifiers.forEach(this::writeGeographicIdentifier)
                    geospatialCoverage.boundingBoxes.forEach(this::writeBoundingBox)
                    geospatialCoverage.boundingGeometries.forEach(this::writeBoundingGeometry)
                    geospatialCoverage.postalAddresses.forEach(this::writePostalAddress)
                    geospatialCoverage.verticalExtents.forEach(this::writeVerticalExtent)
                }
            }
        }
    }

    private fun writeGeographicIdentifier(geographicIdentifier: GeographicIdentifier) {
        ddmsElement("geographicIdentifier") {
            with(geographicIdentifier) {
                names.forEach { ddmsElement("name", it) }
                regions.forEach { ddmsElement("region", it) }
                countryCodes.forEach { writeCountryCode(it) }
                facilityIdentifiers.forEach { writeFacilityIdentifier(it) }
            }
        }
    }

    private fun writeCountryCode(countryCode: CountryCode) {
        writeEmptyElement(ddms20Namespace, "countryCode")
        if (countryCode.qualifier.isNotBlank()) {
            ddmsAttribute("qualifier", countryCode.qualifier)
        }
        if (countryCode.value.isNotBlank()) {
            ddmsAttribute("value", countryCode.value)
        }
    }

    private fun writeFacilityIdentifier(facilityIdentifier: FacilityIdentifier) {
        ddmsEmptyElement("facilityIdentifier") {
            ddmsAttribute("beNumber", facilityIdentifier.beNumber)
            if (facilityIdentifier.oSuffix.isNotBlank()) {
                ddmsAttribute("osuffix", facilityIdentifier.oSuffix)
            }
        }
    }

    private fun writeBoundingBox(boundingBox: BoundingBox) {
        ddmsElement("boundingBox") {
            with(boundingBox) {
                ddmsElement("WestBL", west.toString())
                ddmsElement("EastBL", east.toString())
                ddmsElement("SouthBL", south.toString())
                ddmsElement("NorthBL", north.toString())
            }
        }
    }

    private fun writeBoundingGeometry(boundingGeometry: BoundingGeometry) {
        ddmsElement("boundingGeometry") {
            boundingGeometry.polygons.forEach(this::writePolygon)
            boundingGeometry.points.forEach(this::writePoint)
        }
    }

    private fun writePolygon(polygon: Polygon) {
        gmlElement("Polygon") {
            namespace(gmlPrefix, gmlNamespace)
            addSrsAttributes(polygon.srsAttributes)
            gmlAttribute("id", polygon.id)

            gmlElement("exterior") {
                gmlElement("LinearRing") {
                    polygon.exterior.positions.forEach { writePos(it) }
                }
            }
        }
    }

    private fun writePoint(point: Point) {
        gmlElement("Point") {
            namespace(gmlPrefix, gmlNamespace)
            addSrsAttributes(point.srsAttributes)
            gmlAttribute("id", point.id)
            writePos(point.position)
        }
    }

    private fun writePos(position: Position) {
        gmlElement("pos") {
            addSrsAttributes(position.srsAttributes)
            writeCharacters(position.points.joinToString(" "))
        }
    }

    private fun addSrsAttributes(srsAttributes: SrsAttributes) {
        writeAttribute("srsName", srsAttributes.srsName)
        if (srsAttributes.srsDimension > 0) {
            writeAttribute("srsDimension", srsAttributes.srsDimension.toString())
        }

        val axisLabels = srsAttributes.axisLabels.joinToString(" ").trim()
        if (axisLabels.isNotBlank()) {
            writeAttribute("axisLabels", axisLabels)
        }

        val uomLabels = srsAttributes.uomLabels.joinToString(" ").trim()
        if (uomLabels.isNotBlank()) {
            writeAttribute("uomLabels", uomLabels)
        }
    }

    private fun writePostalAddress(postalAddress: PostalAddress) {
        ddmsElement("postalAddress") {
            with(postalAddress) {
                street.forEach { ddmsElement("street", it) }
                if (city.isNotBlank()) ddmsElement("city", city)
                if (stateOrProvince is State) ddmsElement("state", stateOrProvince.value)
                if (stateOrProvince is Province) ddmsElement("province", stateOrProvince.value)
                if (postalCode.isNotBlank()) ddmsElement("postalCode", postalCode)
                if (countryCode.value.isNotBlank() || countryCode.qualifier.isNotBlank()) writeCountryCode(countryCode)
            }
        }
    }

    private fun writeVerticalExtent(verticalExtent: VerticalExtent) {
        ddmsElement("verticalExtent") {
            ddmsAttribute("unitOfMeasure", verticalExtent.unit.name)
            ddmsAttribute("datum", verticalExtent.datum.name)

            writeVerticalDistance(verticalExtent.minVerticalExtent, "MinVerticalExtent")
            writeVerticalDistance(verticalExtent.maxVerticalExtentValue, "MaxVerticalExtent")
        }
    }

    private fun writeVerticalDistance(verticalDistance: VerticalDistance, type: String) {
        ddmsElement(type) {
            with(verticalDistance) {
                if (unit != null) ddmsAttribute("unitOfMeasure", unit.name)
                if (datum != null) ddmsAttribute("datum", datum.name)
            }

            writeCharacters(verticalDistance.value.toString())
        }
    }

    private fun writeRelatedResources() {
        for (relatedResources in ddms.relatedResources) {
            ddmsElement("relatedResources") {
                addSecurityAttributes(relatedResources.securityAttributes)
                ddmsAttribute("relationship", relatedResources.relationship)
                when (relatedResources.direction) {
                    RelatedResources.Direction.Outbound -> ddmsAttribute("direction", "outbound")
                    RelatedResources.Direction.Inbound -> ddmsAttribute("direction", "inbound")
                    RelatedResources.Direction.Bidirectional -> ddmsAttribute("direction", "bidirectional")
                }
                relatedResources.resources.forEach { writeRelatedResource(it) }
            }
        }
    }

    private fun writeRelatedResource(relatedResource: RelatedResource) {
        ddmsElement("RelatedResource") {
            ddmsAttribute("qualifier", relatedResource.qualifier)
            ddmsAttribute("value", relatedResource.value)
            relatedResource.links.forEach { writeLink(it) }
        }
    }

    private fun writeLink(link: Link) {
        ddmsEmptyElement("link") {
            namespace(xlinkPrefix, xlinkNamespace)
            xlinkAttribute("href", link.href)
            with(link) {
                if (type.isNotBlank()) xlinkAttribute("type", type)
                if (role.isNotBlank()) xlinkAttribute("role", role)
                if (title.isNotBlank()) xlinkAttribute("title", title)
                if (label.isNotBlank()) xlinkAttribute("label", label)
            }
        }
    }

    private fun writeSecurity() {
        ddmsEmptyElement("security") {
            addSecurityAttributes(ddms.security.securityAttributes)
        }
    }

    private fun addSecurityAttributes(securityAttributes: SecurityAttributes) {
        val securityStrings = SecurityAttributeStrings(securityAttributes)
        with(securityStrings) {
            if (classification != Classification.NO_CLASSIFICATION) ismAttribute("classification", classification.value)
            if (ownerProducer.isNotBlank()) ismAttribute("ownerProducer", ownerProducer)
            if (sciControls.isNotBlank()) ismAttribute("SCIcontrols", sciControls)
            if (sarIdentifier.isNotBlank()) ismAttribute("SARIdentifier", sarIdentifier)
            if (disseminationControls.isNotBlank()) ismAttribute("disseminationControls", disseminationControls)
            if (fgiSourceOpen.isNotBlank()) ismAttribute("FGIsourceOpen", fgiSourceOpen)
            if (fgiSourceProtected.isNotBlank()) ismAttribute("FGIsourceProtected", fgiSourceProtected)
            if (releasableTo.isNotBlank()) ismAttribute("releasableTo", releasableTo)
            if (nonIcMarkings.isNotBlank()) ismAttribute("nonICmarkings", nonIcMarkings)
            if (classifiedBy.isNotBlank()) ismAttribute("classifiedBy", classifiedBy)
            if (derivativelyClassifiedBy.isNotBlank()) ismAttribute("derivativelyClassifiedBy", derivativelyClassifiedBy)
            if (classificationReason.isNotBlank()) ismAttribute("classificationReason", classificationReason)
            if (derivedFrom.isNotBlank()) ismAttribute("derivedFrom", derivedFrom)
            if (declassDate.isNotBlank()) ismAttribute("declassDate", declassDate)
            if (declassEvent.isNotBlank()) ismAttribute("declassEvent", declassEvent)
            if (declassException.isNotBlank()) ismAttribute("declassException", declassException)
            if (typeOfExemptedSource.isNotBlank()) ismAttribute("typeOfExemptedSource", typeOfExemptedSource)
            if (dateOfExemptedSource.isNotBlank()) ismAttribute("dateOfExemptedSource", dateOfExemptedSource)
            if (declassManualReview.isNotBlank()) ismAttribute("declassManualReview", declassManualReview)
        }
    }

    private fun ddmsElement(name: String, init: () -> Unit) = element(ddms20Namespace, ddmsPrefix, name, init)

    private fun ddmsElement(name: String, body: String) {
        ddmsElement(name) {
            writeCharacters(body)
        }
    }

    private fun ddmsEmptyElement(name: String, init: () -> Unit) = emptyElement(ddms20Namespace, ddmsPrefix, name, init)

    private fun gmlElement(name: String, init: () -> Unit) = element(gmlNamespace, gmlPrefix, name, init)

    private fun ddmsAttribute(name: String, value: String) = writeAttribute(ddms20Namespace, name, value)

    private fun gmlAttribute(name: String, value: String) = writeAttribute(gmlNamespace, name, value)

    private fun ismAttribute(name: String, value: String) = writeAttribute(ismNamespace, name, value)
}
