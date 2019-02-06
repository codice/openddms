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

import org.codice.ddms.DdmsDate

data class SecurityAttributes(
    val classification: Classification = Classification.NO_CLASSIFICATION,
    val ownerProducer: List<String> = emptyList(),
    val sciControls: List<String> = emptyList(),
    val sarIdentifier: List<String> = emptyList(),
    val disseminationControls: List<String> = emptyList(),
    val fgiSourceOpen: List<String> = emptyList(),
    val fgiSourceProtected: List<String> = emptyList(),
    val releasableTo: List<String> = emptyList(),
    val nonIcMarkings: List<String> = emptyList(),
    val classifiedBy: String = "",
    val derivativelyClassifiedBy: String = "",
    val classificationReason: String = "",
    val derivedFrom: String = "",
    val declassDate: DdmsDate? = null,
    val declassEvent: String = "",
    val declassException: List<String> = emptyList(),
    val typeOfExemptedSource: List<String> = emptyList(),
    val dateOfExemptedSource: DdmsDate? = null,
    val declassManualReview: Boolean? = null
)
