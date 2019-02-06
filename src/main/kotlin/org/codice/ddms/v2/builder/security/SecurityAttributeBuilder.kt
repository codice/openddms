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
package org.codice.ddms.v2.builder.security

import org.codice.ddms.Builder
import org.codice.ddms.DdmsDate
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes

@Suppress("TooManyFunctions") // TODO: Not sure how we can make this 'smaller'
class SecurityAttributeBuilder : Builder<SecurityAttributes> {
    companion object {
        fun securityAttributes(init: SecurityAttributeBuilder.() -> Unit): SecurityAttributes =
                SecurityAttributeBuilder().apply(init).build()
    }

    private var classification = Classification.NO_CLASSIFICATION
    private val ownerProducers: ArrayList<String> = arrayListOf()
    private val sciControls: ArrayList<String> = arrayListOf()
    private val sarIdentifier: ArrayList<String> = arrayListOf()
    private val disseminationControls: ArrayList<String> = arrayListOf()
    private val fgiSourceOpen: ArrayList<String> = arrayListOf()
    private val fgiSourceProtected: ArrayList<String> = arrayListOf()
    private val releasableTo: ArrayList<String> = arrayListOf()
    private val nonIcMarkings: ArrayList<String> = arrayListOf()
    private var classifiedBy: String = ""
    private var derivativelyClassifiedBy: String = ""
    private var classificationReason: String = ""
    private var derivedFrom: String = ""
    private var declassDate: DdmsDate? = null
    private var declassEvent: String = ""
    private val declassException: ArrayList<String> = arrayListOf()
    private val typeOfExemptedSource: ArrayList<String> = arrayListOf()
    private var dateOfExemptedSource: DdmsDate? = null
    private var declassManualReview: Boolean? = null

    fun classification(classification: Classification): SecurityAttributeBuilder {
        this.classification = classification
        return this
    }

    fun ownerProducers(vararg ownerProducer: String): SecurityAttributeBuilder {
        ownerProducers.addAll(ownerProducer)
        return this
    }

    fun ownerProducers(ownerProducer: List<String>): SecurityAttributeBuilder {
        ownerProducers.addAll(ownerProducer)
        return this
    }

    fun sciControls(vararg sciControl: String): SecurityAttributeBuilder {
        sciControls.addAll(sciControl)
        return this
    }

    fun sciControls(sciControl: List<String>): SecurityAttributeBuilder {
        sciControls.addAll(sciControl)
        return this
    }

    fun sarIdentifiers(vararg sarIdentifier: String): SecurityAttributeBuilder {
        this.sarIdentifier.addAll(sarIdentifier)
        return this
    }

    fun sarIdentifiers(sarIdentifier: List<String>): SecurityAttributeBuilder {
        this.sarIdentifier.addAll(sarIdentifier)
        return this
    }

    fun disseminationControls(vararg disseminationControl: String): SecurityAttributeBuilder {
        disseminationControls.addAll(disseminationControl)
        return this
    }

    fun disseminationControls(disseminationControl: List<String>): SecurityAttributeBuilder {
        disseminationControls.addAll(disseminationControl)
        return this
    }

    fun fgiSourceOpen(vararg fgiSourceOpen: String): SecurityAttributeBuilder {
        this.fgiSourceOpen.addAll(fgiSourceOpen)
        return this
    }

    fun fgiSourceOpen(fgiSourceOpen: List<String>): SecurityAttributeBuilder {
        this.fgiSourceOpen.addAll(fgiSourceOpen)
        return this
    }

    fun fgiSourceProtected(vararg fgiSourceProtected: String): SecurityAttributeBuilder {
        this.fgiSourceProtected.addAll(fgiSourceProtected)
        return this
    }

    fun fgiSourceProtected(fgiSourceProtected: List<String>): SecurityAttributeBuilder {
        this.fgiSourceProtected.addAll(fgiSourceProtected)
        return this
    }

    fun releasableTo(vararg releasableTo: String): SecurityAttributeBuilder {
        this.releasableTo.addAll(releasableTo)
        return this
    }

    fun releasableTo(releasableTo: List<String>): SecurityAttributeBuilder {
        this.releasableTo.addAll(releasableTo)
        return this
    }

    fun nonIcMarkings(vararg nonIcMarking: String): SecurityAttributeBuilder {
        nonIcMarkings.addAll(nonIcMarking)
        return this
    }

    fun nonIcMarkings(nonIcMarking: List<String>): SecurityAttributeBuilder {
        nonIcMarkings.addAll(nonIcMarking)
        return this
    }

    fun classifiedBy(classifiedBy: String): SecurityAttributeBuilder {
        this.classifiedBy = classifiedBy
        return this
    }

    fun derivativelyClassifiedBy(derivativelyClassifiedBy: String): SecurityAttributeBuilder {
        this.derivativelyClassifiedBy = derivativelyClassifiedBy
        return this
    }

    fun classificationReason(classificationReason: String): SecurityAttributeBuilder {
        this.classificationReason = classificationReason
        return this
    }

    fun derivedFrom(derivedFrom: String): SecurityAttributeBuilder {
        this.derivedFrom = derivedFrom
        return this
    }

    fun declassDate(declassDate: DdmsDate): SecurityAttributeBuilder {
        this.declassDate = declassDate
        return this
    }

    fun declassEvent(declassEvent: String): SecurityAttributeBuilder {
        this.declassEvent = declassEvent
        return this
    }

    fun declassException(vararg declassException: String): SecurityAttributeBuilder {
        this.declassException.addAll(declassException)
        return this
    }

    fun declassException(declassException: List<String>): SecurityAttributeBuilder {
        this.declassException.addAll(declassException)
        return this
    }

    fun typeOfExemptedSource(vararg typeOfExemptedSource: String): SecurityAttributeBuilder {
        this.typeOfExemptedSource.addAll(typeOfExemptedSource)
        return this
    }

    fun typeOfExemptedSource(typeOfExemptedSource: List<String>): SecurityAttributeBuilder {
        this.typeOfExemptedSource.addAll(typeOfExemptedSource)
        return this
    }

    fun dateOfExemptedSource(dateOfExemptedSource: DdmsDate): SecurityAttributeBuilder {
        this.dateOfExemptedSource = dateOfExemptedSource
        return this
    }

    fun declassManualReview(declassManualReview: Boolean): SecurityAttributeBuilder {
        this.declassManualReview = declassManualReview
        return this
    }

    override fun build(): SecurityAttributes = SecurityAttributes(classification,
            ownerProducers,
            sciControls,
            sarIdentifier,
            disseminationControls,
            fgiSourceOpen,
            fgiSourceProtected,
            releasableTo,
            nonIcMarkings,
            classifiedBy,
            derivativelyClassifiedBy,
            classificationReason,
            derivedFrom,
            declassDate,
            declassEvent,
            declassException,
            typeOfExemptedSource,
            dateOfExemptedSource,
            declassManualReview)
}
