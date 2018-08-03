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

import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SecurityAttributeBuilderTest {
    private val securityAttributes = SecurityAttributes(
            Classification.U,
            listOf("USA"),
            listOf("sci"),
            listOf("sar"),
            listOf("dissemination"),
            listOf("open"),
            listOf("protected"),
            listOf("rel"),
            listOf("non"),
            "by",
            "derBy",
            "reason",
            "derFrom",
            "2017-11-02",
            "event",
            listOf("exception"),
            listOf("source"),
            "2017-11-02",
            true)

    @Test
    fun `building with methods`() {
        val result = SecurityAttributeBuilder()
                .classification(securityAttributes.classification)
                .ownerProducers(securityAttributes.ownerProducer)
                .sciControls(securityAttributes.sciControls)
                .sarIdentifiers(securityAttributes.sarIdentifier)
                .disseminationControls(securityAttributes.disseminationControls)
                .fgiSourceOpen(securityAttributes.fgiSourceOpen)
                .fgiSourceProtected(securityAttributes.fgiSourceProtected)
                .releasableTo(securityAttributes.releasableTo)
                .nonIcMarkings(securityAttributes.nonIcMarkings)
                .classifiedBy(securityAttributes.classifiedBy)
                .derivativelyClassifiedBy(securityAttributes.derivativelyClassifiedBy)
                .classificationReason(securityAttributes.classificationReason)
                .derivedFrom(securityAttributes.derivedFrom)
                .declassDate(securityAttributes.declassDate)
                .declassEvent(securityAttributes.declassEvent)
                .declassException(securityAttributes.declassException)
                .typeOfExemptedSource(securityAttributes.typeOfExemptedSource)
                .dateOfExemptedSource(securityAttributes.dateOfExemptedSource)
                .declassManualReview(securityAttributes.declassManualReview!!)
                .build()

        assertThat(result, equalTo(securityAttributes))
    }

    @Test
    fun `building with lambda`() {
        val result = securityAttributes {
            classification(securityAttributes.classification)
            ownerProducers(securityAttributes.ownerProducer)
            sciControls(securityAttributes.sciControls)
            sarIdentifiers(securityAttributes.sarIdentifier)
            disseminationControls(securityAttributes.disseminationControls)
            fgiSourceOpen(securityAttributes.fgiSourceOpen)
            fgiSourceProtected(securityAttributes.fgiSourceProtected)
            releasableTo(securityAttributes.releasableTo)
            nonIcMarkings(securityAttributes.nonIcMarkings)
            classifiedBy(securityAttributes.classifiedBy)
            derivativelyClassifiedBy(securityAttributes.derivativelyClassifiedBy)
            classificationReason(securityAttributes.classificationReason)
            derivedFrom(securityAttributes.derivedFrom)
            declassDate(securityAttributes.declassDate)
            declassEvent(securityAttributes.declassEvent)
            declassException(securityAttributes.declassException)
            typeOfExemptedSource(securityAttributes.typeOfExemptedSource)
            dateOfExemptedSource(securityAttributes.dateOfExemptedSource)
            declassManualReview(securityAttributes.declassManualReview!!)
        }

        assertThat(result, equalTo(securityAttributes))
    }
}
