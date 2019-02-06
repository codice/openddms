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
            "srsName=\"$srsName\""
        } else ""
    }

    private fun srsDimensionToString(): String {
        return if (srsDimension > 0) {
            " srsDimension=\"$srsDimension\""
        } else ""
    }

    private fun axisLabelsToString(): String {
        return if (axisLabels.any(String::isNotBlank)) {
            " axisLabels=\"${axisLabels.joinToString(" ")}\""
        } else ""
    }

    private fun uomLabelsToString(): String {
        return if (uomLabels.any(String::isNotBlank)) {
            " uomLabels=\"${uomLabels.joinToString(" ")}\""
        } else ""
    }

    override fun toString(): String {
        return srsNameToString() +
                srsDimensionToString() +
                axisLabelsToString() +
                uomLabelsToString()
    }
}
