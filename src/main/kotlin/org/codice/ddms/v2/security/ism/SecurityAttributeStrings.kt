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
package org.codice.ddms.v2.security.ism

class SecurityAttributeStrings(securityAttributes: SecurityAttributes) {
    val classification = securityAttributes.classification
    val ownerProducer = toString(securityAttributes.ownerProducer)
    val sciControls = toString(securityAttributes.sciControls)
    val sarIdentifier = toString(securityAttributes.sarIdentifier)
    val disseminationControls = toString(securityAttributes.disseminationControls)
    val fgiSourceOpen = toString(securityAttributes.fgiSourceOpen)
    val fgiSourceProtected = toString(securityAttributes.fgiSourceProtected)
    val releasableTo = toString(securityAttributes.releasableTo)
    val nonIcMarkings = toString(securityAttributes.nonIcMarkings)
    val classifiedBy = securityAttributes.classifiedBy
    val derivativelyClassifiedBy = securityAttributes.derivativelyClassifiedBy
    val classificationReason = securityAttributes.classificationReason
    val derivedFrom = securityAttributes.derivedFrom
    val declassDate = securityAttributes.declassDate
    val declassEvent = securityAttributes.declassEvent
    val declassException = toString(securityAttributes.declassException)
    val typeOfExemptedSource = toString(securityAttributes.typeOfExemptedSource)
    val dateOfExemptedSource = securityAttributes.dateOfExemptedSource
    val declassManualReview = securityAttributes.declassManualReview?.toString() ?: ""

    private fun toString(list: List<String>): String {
        return list.filter { it.isNotEmpty() }
                .joinToString(" ")
    }
}
