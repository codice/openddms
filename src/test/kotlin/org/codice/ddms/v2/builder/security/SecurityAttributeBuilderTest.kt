/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.security

import org.codice.ddms.DdmsDate
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
            DdmsDate("2017-11-02"),
            "event",
            listOf("exception"),
            listOf("source"),
            DdmsDate("2017-11-02"),
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
                .declassDate(securityAttributes.declassDate!!)
                .declassEvent(securityAttributes.declassEvent)
                .declassException(securityAttributes.declassException)
                .typeOfExemptedSource(securityAttributes.typeOfExemptedSource)
                .dateOfExemptedSource(securityAttributes.dateOfExemptedSource!!)
                .declassManualReview(securityAttributes.declassManualReview!!)
                .build()

        assertThat(result, equalTo(securityAttributes))
    }

    @Test
    fun `building with lambda`() {
        val result = SecurityAttributeBuilder.securityAttributes {
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
            declassDate(securityAttributes.declassDate!!)
            declassEvent(securityAttributes.declassEvent)
            declassException(securityAttributes.declassException)
            typeOfExemptedSource(securityAttributes.typeOfExemptedSource)
            dateOfExemptedSource(securityAttributes.dateOfExemptedSource!!)
            declassManualReview(securityAttributes.declassManualReview!!)
        }

        assertThat(result, equalTo(securityAttributes))
    }
}
