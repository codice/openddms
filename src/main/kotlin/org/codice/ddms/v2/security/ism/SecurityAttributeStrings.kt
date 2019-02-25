/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.security.ism

/**
 * Utility class to convert [SecurityAttributes] values to [String].
 */
class SecurityAttributeStrings(securityAttributes: SecurityAttributes) {
    val classification = securityAttributes.classification
    val classifiedBy = securityAttributes.classifiedBy
    val derivativelyClassifiedBy = securityAttributes.derivativelyClassifiedBy
    val classificationReason = securityAttributes.classificationReason
    val derivedFrom = securityAttributes.derivedFrom
    val declassDate = securityAttributes.declassDate
    val declassEvent = securityAttributes.declassEvent
    val dateOfExemptedSource = securityAttributes.dateOfExemptedSource

    val ownerProducer = toString(securityAttributes.ownerProducer)
    val sciControls = toString(securityAttributes.sciControls)
    val sarIdentifier = toString(securityAttributes.sarIdentifier)
    val disseminationControls = toString(securityAttributes.disseminationControls)
    val fgiSourceOpen = toString(securityAttributes.fgiSourceOpen)
    val fgiSourceProtected = toString(securityAttributes.fgiSourceProtected)
    val releasableTo = toString(securityAttributes.releasableTo)
    val nonIcMarkings = toString(securityAttributes.nonIcMarkings)
    val declassException = toString(securityAttributes.declassException)
    val typeOfExemptedSource = toString(securityAttributes.typeOfExemptedSource)
    val declassManualReview = securityAttributes.declassManualReview?.toString() ?: ""

    private fun toString(list: List<String>): String {
        return list.filter { it.isNotEmpty() }
                .joinToString(" ")
    }
}
