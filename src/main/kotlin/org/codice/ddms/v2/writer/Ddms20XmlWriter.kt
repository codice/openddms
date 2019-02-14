/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.writer

import org.codice.ddms.DdmsResource
import org.codice.ddms.DdmsWriter
import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Polygon
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.codice.ddms.v2.format.Extent
import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.resource.producers.Organization
import org.codice.ddms.v2.resource.producers.Person
import org.codice.ddms.v2.resource.producers.Producer
import org.codice.ddms.v2.resource.producers.Service
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributeStrings
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.Link
import org.codice.ddms.v2.summary.RelatedResource
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

private const val DDMS_PREFIX = "ddms"
private const val DDMS_20_NAMESPACE = "http://metadata.dod.mil/mdr/ns/DDMS/2.0/"
private const val DDMS_20_SCHEMA_LOCATION = "http://metadata.dod.mil/mdr/ns/DDMS/2.0/DDMS-v2_0.xsd"

private const val ISM_NAMESPACE = "urn:us:gov:ic:ism:v2"
private const val ISM_PREFIX = "ism"

private const val GML_NAMESPACE = "http://www.opengis.net/gml"
private const val GML_PREFIX = "gml"

@Suppress("LargeClass", "TooManyFunctions")
/**
 * Creates an XML document from a [DdmsResource].
 *
 * @param ddms The DDMS resource to write.
 * @param writer A preconfigured [XMLStreamWriter] to use for creating the XML document.
 */
class Ddms20XmlWriter(
    private val ddms: DdmsResource,
    private val writer: XMLStreamWriter
) : DdmsWriter, XMLStreamWriter by writer {

    /**
     * Writes out the [DdmsResource] using the configured [writer].
     */
    override fun write() {
        writeStartDocument()
        ddmsElement("Resource") {
            namespace(DDMS_PREFIX, DDMS_20_NAMESPACE)
            namespace(ISM_PREFIX, ISM_NAMESPACE)
            namespace(xsiPrefix, xsiNamespace)

            writeAttribute(xsiNamespace, schemaLocation, DDMS_20_SCHEMA_LOCATION)

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
        ddms.description?.apply {
            ddmsElement("description") {
                addSecurityAttributes(securityAttributes)
                writeCharacters(value)
            }
        }
    }

    private fun writeLanguages() {
        ddms.languages.forEach {
            ddmsEmptyElement("language") {
                ddmsAttribute("qualifier", it.qualifier)
                ddmsAttribute("value", it.value)
            }
        }
    }

    private fun writeDates() {
        ddms.dates?.apply {
            ddmsEmptyElement("dates") {
                if (created != null) ddmsAttribute("created", created.toString())
                if (posted != null) ddmsAttribute("posted", posted.toString())
                if (validTil != null) ddmsAttribute("validTil", validTil.toString())
                if (infoCutOff != null) ddmsAttribute("infoCutOff", infoCutOff.toString())
            }
        }
    }

    private fun writeRights() {
        ddms.rights?.apply {
            ddmsEmptyElement("rights") {
                ddmsAttribute("privacyAct", privacyAct.toString())
                ddmsAttribute("intellectualProperty", intellectualProperty.toString())
                ddmsAttribute("copyright", privacyAct.toString())
            }
        }
    }

    private fun writeSources() {
        ddms.sources.forEach {
            ddmsEmptyElement("source") {
                ddmsAttribute("qualifier", it.qualifier)
                ddmsAttribute("value", it.value)
                ddmsAttribute("schemaQualifier", it.schema)
                if (it.href.isNotBlank()) ddmsAttribute("schemaHref", it.href)
            }
        }
    }

    private fun writeTypes() {
        ddms.types.forEach {
            ddmsEmptyElement("type") {
                ddmsAttribute("qualifier", it.qualifier)
                ddmsAttribute("value", it.value)
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

    private fun writeOrganization(organization: Organization) = writeProducer("Organization", organization)

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

    private fun writeService(service: Service) = writeProducer("Service", service)

    private fun writeProducer(type: String, producer: Producer) {
        ddmsElement(type) {
            with(producer) {
                names.forEach { ddmsElement("name", it) }
                phones.forEach { ddmsElement("phone", it) }
                emails.forEach { ddmsElement("email", it) }
            }
        }
    }

    private fun writeFormat() {
        ddms.format?.apply {
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
        ddms.virtualCoverages.forEach {
            ddmsEmptyElement("virtualCoverage") {
                if (it.protocol.isNotBlank()) ddmsAttribute("protocol", it.protocol)
                if (it.address.isNotBlank()) ddmsAttribute("address", it.address)
            }
        }
    }

    private fun writeTemporalCoverages() {
        ddms.temporalCoverages.forEach {
            ddmsElement("temporalCoverage") {
                ddmsElement("TimePeriod") {
                    ddmsElement("name", it.name)
                    ddmsElement("start", it.start.toString())
                    ddmsElement("end", it.end.toString())
                }
            }
        }
    }

    private fun writeGeospatialCoverages() {
        ddms.geospatialCoverages.forEach {
            ddmsElement("geospatialCoverage") {
                ddmsElement("GeospatialExtent") {
                    it.geographicIdentifiers.forEach(this::writeGeographicIdentifier)
                    it.boundingBoxes.forEach(this::writeBoundingBox)
                    it.boundingGeometries.forEach(this::writeBoundingGeometry)
                    it.postalAddresses.forEach(this::writePostalAddress)
                    it.verticalExtents.forEach(this::writeVerticalExtent)
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
        writeEmptyElement(DDMS_20_NAMESPACE, "countryCode")
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
            namespace(GML_PREFIX, GML_NAMESPACE)
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
            namespace(GML_PREFIX, GML_NAMESPACE)
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
                if (countryCode !== null) writeCountryCode(countryCode)
            }
        }
    }

    private fun writeVerticalExtent(verticalExtent: VerticalExtent) {
        ddmsElement("verticalExtent") {
            ddmsAttribute("unitOfMeasure", verticalExtent.unit.name)
            ddmsAttribute("datum", verticalExtent.datum.name)

            writeVerticalDistance(verticalExtent.minVerticalExtent, "MinVerticalExtent")
            writeVerticalDistance(verticalExtent.maxVerticalExtent, "MaxVerticalExtent")
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
                ddmsAttribute("direction", relatedResources.direction.toString())
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

    // Not sure how we could make this method less complex without it being artificial
    @Suppress("ComplexMethod")
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
            if (derivativelyClassifiedBy.isNotBlank()) ismAttribute("derivativelyClassifiedBy",
                    derivativelyClassifiedBy)
            if (classificationReason.isNotBlank()) ismAttribute("classificationReason", classificationReason)
            if (derivedFrom.isNotBlank()) ismAttribute("derivedFrom", derivedFrom)
            if (declassDate != null) ismAttribute("declassDate", declassDate.toString())
            if (declassEvent.isNotBlank()) ismAttribute("declassEvent", declassEvent)
            if (declassException.isNotBlank()) ismAttribute("declassException", declassException)
            if (typeOfExemptedSource.isNotBlank()) ismAttribute("typeOfExemptedSource", typeOfExemptedSource)
            if (dateOfExemptedSource != null) ismAttribute("dateOfExemptedSource", dateOfExemptedSource.toString())
            if (declassManualReview.isNotBlank()) ismAttribute("declassManualReview", declassManualReview)
        }
    }

    private fun ddmsElement(name: String, init: () -> Unit) = element(DDMS_20_NAMESPACE, DDMS_PREFIX, name, init)

    private fun ddmsElement(name: String, body: String) {
        ddmsElement(name) {
            writeCharacters(body)
        }
    }

    private fun ddmsEmptyElement(name: String, init: () -> Unit) =
            emptyElement(DDMS_20_NAMESPACE, DDMS_PREFIX, name, init)

    private fun gmlElement(name: String, init: () -> Unit) = element(GML_NAMESPACE, GML_PREFIX, name, init)

    private fun ddmsAttribute(name: String, value: String) = writeAttribute(DDMS_20_NAMESPACE, name, value)

    private fun gmlAttribute(name: String, value: String) = writeAttribute(GML_NAMESPACE, name, value)

    private fun ismAttribute(name: String, value: String) = writeAttribute(ISM_NAMESPACE, name, value)
}
