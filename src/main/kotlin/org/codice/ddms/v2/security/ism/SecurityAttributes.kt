/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.security.ism

import org.codice.ddms.DdmsDate

/**
 * All possible security attributes that a secure element can have.
 */
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
