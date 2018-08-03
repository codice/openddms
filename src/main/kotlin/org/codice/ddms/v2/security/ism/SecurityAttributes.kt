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

import org.codice.ddms.DateFormat

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
    val declassDate: String = "",
    val declassEvent: String = "",
    val declassException: List<String> = emptyList(),
    val typeOfExemptedSource: List<String> = emptyList(),
    val dateOfExemptedSource: String = "",
    val declassManualReview: Boolean? = null
) {
    init {
        if (dateOfExemptedSource.isNotBlank()) {
            require(DateFormat.isValid(dateOfExemptedSource, true)) {
                "ism:dateOfExemptedSource is an invalid date"
            }
        }
        if (declassDate.isNotBlank()) {
            require(DateFormat.isValid(declassDate, true)) {
                "ism:declassDate is an invalid date"
            }
        }
    }
}
