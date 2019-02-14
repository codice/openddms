/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary

import org.codice.ddms.DdmsDate

/**
 * Subject matter coverage expressed in terms of one or more periods of time.
 *
 * @param name An interval of time, which can be expressed as a named era.
 * @param start The start date of a period of time.
 * @param end The end date of a period of time.
 */
data class TemporalCoverage(
    val name: String = "Unknown",
    val start: DdmsDate = DdmsDate.unknown,
    val end: DdmsDate = DdmsDate.unknown
)
