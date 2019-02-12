/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary

import org.codice.ddms.DdmsDate

data class TemporalCoverage(
    val name: String = "Unknown",
    val start: DdmsDate = DdmsDate.unknown,
    val end: DdmsDate = DdmsDate.unknown
)
