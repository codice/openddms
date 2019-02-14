/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.reader

import org.codice.ddms.DdmsDate
import org.codice.ddms.DdmsReader
import org.codice.ddms.DdmsResource
import org.codice.ddms.gml.v3.SrsAttributes
import org.codice.ddms.gml.v3.builder.SrsAttributesBuilder
import org.codice.ddms.v2.builder.Ddms20ResourceBuilder
import org.codice.ddms.v2.builder.producers.ProducerBuilder
import org.codice.ddms.v2.builder.resource.ContactBuilder
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder
import org.codice.ddms.v2.builder.summary.GeospatialCoverageBuilder
import org.codice.ddms.v2.builder.summary.geospatial.BoundingGeometryBuilder
import org.codice.ddms.v2.format.Extent
import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Description
import org.codice.ddms.v2.summary.RelatedResources
import org.codice.ddms.v2.summary.geospatial.Datum
import org.codice.ddms.v2.summary.geospatial.UnitOfMeasure
import org.codice.ddms.v2.summary.geospatial.VerticalDistance
import org.codice.ddms.xml.util.XmlConstants
import org.slf4j.LoggerFactory
import javax.xml.stream.XMLStreamConstants
import javax.xml.stream.XMLStreamReader

private const val DDMS_20_NAMESPACE = "http://metadata.dod.mil/mdr/ns/DDMS/2.0/"

@Suppress("LargeClass", "TooManyFunctions")
/**
 * An XML reader that reads a DDMS 2.0 XML document and creates a [DdmsResource].
 *
 * @param reader A preconfigured [XMLStreamReader] used to read the DDMS 2.0 XML document.
 */
class Ddms20XmlReader(private val reader: XMLStreamReader) : DdmsReader, XMLStreamReader by reader {
    private val logger = LoggerFactory.getLogger(Ddms20XmlReader::class.java)

    private val ddms20Builder = Ddms20ResourceBuilder()

    /**
     * Read the DDMS 2.0 XML document and create a [DdmsResource].
     *
     * @return An immutable [DdmsResource] from the [reader].
     */
    override fun read(): DdmsResource {
        while (hasNext()) {
            val event = next()
            when (event) {
                XMLStreamConstants.START_DOCUMENT -> logger.trace("Start Document Event")
                XMLStreamConstants.END_DOCUMENT -> logger.trace("End Document Event")
                XMLStreamConstants.START_ELEMENT -> {
                    logger.trace("Start Element Event")
                    startElement()
                }
                XMLStreamConstants.END_ELEMENT -> logger.trace("End Element Event")
                XMLStreamConstants.PROCESSING_INSTRUCTION -> logger.trace("Processing Instruction Event")
                XMLStreamConstants.CHARACTERS -> logger.trace("Characters Event")
                XMLStreamConstants.ENTITY_REFERENCE -> logger.trace("Entity Reference Event")
                XMLStreamConstants.ATTRIBUTE -> logger.trace("Attribute Event")
                XMLStreamConstants.COMMENT -> logger.trace("Comment Event")
                XMLStreamConstants.SPACE -> logger.trace("Space Event")
                XMLStreamConstants.DTD -> logger.trace("DTD Event")
                XMLStreamConstants.CDATA -> logger.trace("CDATA Event")
                XMLStreamConstants.NAMESPACE -> logger.trace("Namespace Event")
                else -> logger.trace("Unhandled XML event: $event")
            }
        }

        return ddms20Builder.build()
    }

    private fun startElement() {
        if (namespaceURI == DDMS_20_NAMESPACE) {
            logger.debug("Found start element: '$localName'")
            with(ddms20Builder) {
                when (localName) {
                    "Resource" -> return
                    "identifier" -> identifier(getDdmsAttribute("qualifier"), getDdmsAttribute("value"))
                    "title" -> titles(Title(getSecurityAttributes(), elementText))
                    "subtitle" -> subtitles(Title(getSecurityAttributes(), elementText))
                    "description" -> description(Description(getSecurityAttributes(), elementText))
                    "language" -> language(getDdmsAttribute("qualifier"), getDdmsAttribute("value"))
                    "dates" -> dates(getDdmsAttribute("created"),
                            getDdmsAttribute("posted"),
                            getDdmsAttribute("validTil"),
                            getDdmsAttribute("infoCutOff"))
                    "rights" -> rights(getDdmsAttribute("privacyAct").toBoolean(),
                            getDdmsAttribute("intellectualProperty").toBoolean(),
                            getDdmsAttribute("copyright").toBoolean())
                    "source" -> source(getDdmsAttribute("qualifier"), getDdmsAttribute("value"),
                            getDdmsAttribute("schemaQualifier"), getDdmsAttribute("schemaHref"))
                    "type" -> type(getDdmsAttribute("qualifier"), getDdmsAttribute("value"))
                    "creator" -> creators(getContact())
                    "publisher" -> publishers(getContact())
                    "contributor" -> contributors(getContact())
                    "pointOfContact" -> pointOfContacts(getContact())
                    "format" -> parseFormat()
                    "subjectCoverage" -> parseSubjectCoverage()
                    "virtualCoverage" -> virtualCoverage(getDdmsAttribute("address"),
                            getDdmsAttribute("protocol"))
                    "temporalCoverage" -> parseTemporalCoverage()
                    "geospatialCoverage" -> parseGeospatialCoverage()
                    "relatedResources" -> parseRelatedResources()
                    "security" -> security(Security(getSecurityAttributes()))
                    else -> logger.debug("Unhandled DDMS element $localName")
                }
            }
        } else {
            logger.debug("Unhandled element $namespaceURI:$localName")
        }
    }

    private fun getContact(): Contact {
        return ContactBuilder.contact {
            securityAttributes(getSecurityAttributes())
            nextTag("creator/publisher/contributor/point of contact/")
            when (localName) {
                "Organization" -> parseOrganization()
                "Person" -> parsePerson()
                "Service" -> parseService()
            }
        }
    }

    private fun parseFormat() {
        nextTag("Media")
        nextTag("mimeType")
        val mimeType = elementText
        var extent = Extent()
        var medium = ""
        if (XMLStreamConstants.END_ELEMENT != nextTag("extent?")) {
            if (localName == "extent") {
                extent = Extent(getDdmsAttribute("qualifier"), getDdmsAttribute("value"))
                nextTag("end of extent")
                if (XMLStreamConstants.END_ELEMENT != nextTag("medium?")) {
                    medium = elementText
                }
            } else {
                medium = elementText
            }
        }

        ddms20Builder.format(mimeType, extent, medium)
    }

    private fun parseSubjectCoverage() {
        ddms20Builder.subjectCoverage {
            nextTag("Subject")
            nextTag("category/keyword")
            while (localName != "Subject") {
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    when (localName) {
                        "category" ->
                            category(getDdmsAttribute("label"),
                                    getDdmsAttribute("qualifier"),
                                    getDdmsAttribute("code"))
                        "keyword" ->
                            keywords(getDdmsAttribute("value"))
                    }
                }
                nextTag("category/keyword")
            }
        }
    }

    private fun parseTemporalCoverage() {
        var name = ""
        val start: String
        val end: String
        nextTag("TimePeriod")
        nextTag("name?")
        if (localName == "name") {
            name = elementText
            nextTag("start")
            start = elementText
            nextTag("end")
            end = elementText
        } else { // Start
            start = elementText
            nextTag("end")
            end = elementText
        }
        val startDate = try {
            DdmsDate(start)
        } catch (e: IllegalArgumentException) {
            throw IllegalStateException("ddms:temporalCoverage contains invalid start date: $start")
        }
        val endDate = try {
            DdmsDate(end)
        } catch (e: IllegalArgumentException) {
            throw IllegalStateException("ddms:temporalCoverage contains invalid end date: $start")
        }
        ddms20Builder.temporalCoverage(name, startDate, endDate)
    }

    private fun parseGeospatialCoverage() {
        ddms20Builder.geospatialCoverage {
            nextTag("GeospatialExtent")
            nextTag("geographicIdentifier/boundingBox/boundingGeometry/postalAddress/verticalExtent")
            while (localName != "GeospatialExtent") {
                if (eventType != XMLStreamConstants.END_ELEMENT) {
                    when (localName) {
                        "geographicIdentifier" -> parseGeographicIdentifier()
                        "boundingBox" -> parseBoundingBox()
                        "boundingGeometry" -> parseBoundingGeometry()
                        "postalAddress" -> parsePostalAddress()
                        "verticalExtent" -> parseVerticalExtent()
                    }
                }
                nextTag("geographicIdentifier/boundingBox/boundingGeometry/postalAddress/verticalExtent")
            }
        }
    }

    private fun getVerticalDistance(): VerticalDistance {
        val unit = getDdmsAttribute("unitOfMeasure")
        val datum = getDdmsAttribute("datum")
        return VerticalDistance(elementText.toDouble(),
                if (unit.isNotBlank()) UnitOfMeasure.valueOf(unit) else null,
                if (datum.isNotBlank()) Datum.valueOf(datum) else null)
    }

    private fun parseRelatedResources() {
        ddms20Builder.relatedResources {
            relationship(getDdmsAttribute("relationship"))
            direction(RelatedResources.Direction.valueOf(getDdmsAttribute("direction").capitalize()))
            securityAttributes(getSecurityAttributes())

            val relatedResource = "RelatedResource"
            nextTag(relatedResource)
            while (localName != "relatedResources") {
                if (eventType != XMLStreamConstants.END_ELEMENT) {
                    resource {
                        qualifier(getDdmsAttribute("qualifier"))
                        value(getDdmsAttribute("value"))

                        nextTag("link")
                        while (localName != relatedResource) {
                            if (eventType != XMLStreamConstants.END_ELEMENT) {
                                link {
                                    href(getAttributeValue(XmlConstants.xlinkNamespace, "href"))
                                    role(getAttributeValue(XmlConstants.xlinkNamespace, "role"))
                                    title(getAttributeValue(XmlConstants.xlinkNamespace, "title"))
                                    label(getAttributeValue(XmlConstants.xlinkNamespace, "label"))
                                }
                            }
                            nextTag("link")
                        }
                    }
                }
                nextTag(relatedResource)
            }
        }
    }

    override fun getAttributeValue(namespaceURI: String?, localName: String): String {
        return reader.getAttributeValue(namespaceURI, localName) ?: ""
    }

    private fun getSrsAttributes(): SrsAttributes {
        val dim = getAttributeValue(null, "srsDimension")
        return SrsAttributesBuilder.srsAttributes {
            srsName(getAttributeValue(null, "srsName"))
            srsDimension(if (dim.isNotEmpty()) dim.toInt() else 0)
            axisLabels(getAttributeValue(null, "axisLabels").split(" "))
            uomLabels(getAttributeValue(null, "uomLabels").split(" "))
        }
    }

    private fun getSecurityAttributes(): SecurityAttributes {
        val securityAttributeBuilder = SecurityAttributeBuilder()

        for (i in 0 until attributeCount) {
            val name = getAttributeName(i)
            val value = getAttributeValue(i)

            if (name.namespaceURI == "urn:us:gov:ic:ism:v2") {
                when (name.localPart) {
                    "classification" -> securityAttributeBuilder.classification(Classification.getEnum(value))
                    "ownerProducer" -> securityAttributeBuilder.ownerProducers(value.split(" "))
                    "SCIcontrols" -> securityAttributeBuilder.sciControls(value.split(" "))
                    "SARIdentifier" -> securityAttributeBuilder.sarIdentifiers(value.split(" "))
                    "disseminationControls" -> securityAttributeBuilder.disseminationControls(value.split(" "))
                    "FGIsourceOpen" -> securityAttributeBuilder.fgiSourceOpen(value.split(" "))
                    "FGIsourceProtected" -> securityAttributeBuilder.fgiSourceProtected(value.split(" "))
                    "releasableTo" -> securityAttributeBuilder.releasableTo(value.split(" "))
                    "nonICmarkings" -> securityAttributeBuilder.nonIcMarkings(value.split(" "))
                    "classifiedBy" -> securityAttributeBuilder.classifiedBy(value)
                    "derivativelyClassifiedBy" -> securityAttributeBuilder.derivativelyClassifiedBy(value)
                    "classificationReason" -> securityAttributeBuilder.classificationReason(value)
                    "derivedFrom" -> securityAttributeBuilder.derivedFrom(value)
                    "declassDate" -> securityAttributeBuilder.declassDate(getDdmsDate(value,
                            "ism:declassDate is an invalid date"))
                    "declassEvent" -> securityAttributeBuilder.declassEvent(value)
                    "declassException" -> securityAttributeBuilder.declassException(value.split(" "))
                    "typeOfExemptedSource" -> securityAttributeBuilder.typeOfExemptedSource(value.split(" "))
                    "dateOfExemptedSource" -> securityAttributeBuilder.dateOfExemptedSource(getDdmsDate(value,
                            "ism:dateOfExemptedSource is an invalid date"))
                    "declassManualReview" -> securityAttributeBuilder.declassManualReview(value!!.toBoolean())
                    else -> logger.debug("Unhandled security attribute $localName")
                }
            }
        }

        return securityAttributeBuilder.build()
    }

    private fun getDdmsDate(date: String, errorMsg: String): DdmsDate {
        return try {
            DdmsDate(date)
        } catch (e: IllegalArgumentException) {
            throw IllegalStateException(errorMsg)
        }
    }

    private fun getDdmsAttribute(name: String): String {
        return getAttributeValue(DDMS_20_NAMESPACE, name)
    }

    override fun getElementText(): String {
        return reader.elementText.trim()
    }

    /**
     * Pass through to nextTag() but includes the name of the next tag for documenting / logging
     * instead of having to make a comment
     *
     * @param documentedTag The name of what the next tag should be.
     */
    private fun nextTag(documentedTag: String): Int {
        logger.trace("Expecting '$documentedTag' as next text")
        return nextTag()
    }

    private fun <T : ProducerBuilder<T>> ProducerBuilder<T>.parseProducer(type: String) {
        nextTag("$type name/phone/email")
        while (localName != type) {
            when (localName) {
                "name" -> names(elementText)
                "phone" -> phones(elementText)
                "email" -> emails(elementText)
            }
            nextTag("$type name/phone/email")
        }
    }

    private fun ContactBuilder.parsePerson() = person {
        nextTag("person name/phone/email")
        while (localName != "Person") {
            when (localName) {
                "name" -> names(elementText)
                "surname" -> surname(elementText)
                "userID" -> userId(elementText)
                "affiliation" -> affiliation(elementText)
                "phone" -> phones(elementText)
                "email" -> emails(elementText)
            }
            nextTag("person name/phone/email")
        }
    }

    private fun ContactBuilder.parseOrganization() = organization {
        parseProducer("Organization")
    }

    private fun ContactBuilder.parseService() = service {
        parseProducer("Service")
    }

    private fun GeospatialCoverageBuilder.parseGeographicIdentifier() {
        geographicIdentifier {
            nextTag("name/region/country code/facility identifier")
            while (localName != "geographicIdentifier") {
                if (eventType != XMLStreamConstants.END_ELEMENT) {
                    when (localName) {
                        "name" -> names(elementText)
                        "region" -> regions(elementText)
                        "countryCode" -> countryCode(getDdmsAttribute("qualifier"),
                                getDdmsAttribute("value"))
                        "facilityIdentifier" -> facilityIdentifier(getDdmsAttribute("beNumber"),
                                getDdmsAttribute("osuffix"))
                    }
                }
                nextTag("name/region/country code/facility identifier")
            }
        }
    }

    private fun GeospatialCoverageBuilder.parseBoundingBox() {
        nextTag("WestBL")
        val west = elementText.toDouble()
        nextTag("EastBL")
        val east = elementText.toDouble()
        nextTag("SouthBL")
        val south = elementText.toDouble()
        nextTag("NorthBL")
        val north = elementText.toDouble()
        boundingBox(west, east, north, south)
    }

    private fun GeospatialCoverageBuilder.parseBoundingGeometry() {
        boundingGeometry {
            nextTag("Polygon/Point")
            while (localName != "boundingGeometry") {
                if (eventType != XMLStreamConstants.END_ELEMENT) {
                    when (localName) {
                        "Polygon" -> parsePolygon()
                        "Point" -> parsePoint()
                    }
                }
                nextTag("Polygon/Point")
            }
        }
    }

    private fun BoundingGeometryBuilder.parsePolygon() {
        polygon {
            srsAttributes(getSrsAttributes())
            id(getAttributeValue("http://www.opengis.net/gml", "id"))
            nextTag("exterior")
            nextTag("LinearRing")
            exterior {
                nextTag("pos")
                while (localName != "LinearRing") {
                    if (eventType != XMLStreamConstants.END_ELEMENT) {
                        position {
                            srsAttributes(getSrsAttributes())
                            points(elementText
                                    .split(" ")
                                    .map { it.toDouble() }
                                    .toList())
                        }
                    }
                    nextTag("pos")
                }
            }
        }
    }

    private fun BoundingGeometryBuilder.parsePoint() {
        point {
            srsAttributes(getSrsAttributes())
            id(getAttributeValue("http://www.opengis.net/gml", "id"))
            nextTag("pos")
            position {
                srsAttributes(getSrsAttributes())
                points(elementText
                        .split(" ")
                        .map { it.toDouble() }
                        .toList())
            }
        }
    }

    private fun GeospatialCoverageBuilder.parsePostalAddress() {
        postalAddress {
            nextTag("street/city/state or province/postalCode/countryCode")
            while (localName != "postalAddress") {
                if (eventType != XMLStreamConstants.END_ELEMENT) {
                    when (localName) {
                        "street" -> streets(elementText)
                        "city" -> city(elementText)
                        "state" -> state(elementText)
                        "province" -> province(elementText)
                        "postalCode" -> postalCode(elementText)
                        "countryCode" -> countryCode(getDdmsAttribute("qualifier"),
                                getDdmsAttribute("value"))
                    }
                }
                nextTag("street/city/state or province/postalCode/countryCode")
            }
        }
    }

    private fun GeospatialCoverageBuilder.parseVerticalExtent() {
        verticalExtent {
            unit(UnitOfMeasure.valueOf(getDdmsAttribute(("unitOfMeasure"))))
            datum(Datum.valueOf(getDdmsAttribute("datum")))

            nextTag("MinVerticalExtent")
            minimum(getVerticalDistance())

            nextTag("MaxVerticalExtent")
            maximum(getVerticalDistance())
        }
    }
}
