/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

data class SrsAttributes @JvmOverloads constructor(
    val srsName: String = "",
    val srsDimension: Int = 0,
    val axisLabels: List<String> = emptyList(),
    val uomLabels: List<String> = emptyList()
) {
    init {
        require(srsDimension >= 0) {
            "srsDimension needs to be a positive integer"
        }
    }

    private fun srsNameToString(): String {
        return if (srsName.isNotBlank()) {
            """srsName="$srsName""""
        } else ""
    }

    private fun srsDimensionToString(): String {
        return if (srsDimension > 0) {
            """ srsDimension="$srsDimension""""
        } else ""
    }

    private fun axisLabelsToString(): String {
        return if (axisLabels.any(String::isNotBlank)) {
            """ axisLabels="${axisLabels.joinToString(" ")}""""
        } else ""
    }

    private fun uomLabelsToString(): String {
        return if (uomLabels.any(String::isNotBlank)) {
            """ uomLabels="${uomLabels.joinToString(" ")}""""
        } else ""
    }

    override fun toString(): String {
        return srsNameToString() +
                srsDimensionToString() +
                axisLabelsToString() +
                uomLabelsToString()
    }
}
