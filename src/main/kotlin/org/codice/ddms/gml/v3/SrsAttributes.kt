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
    override fun toString(): String {
        val sb = StringBuffer()
        if (srsName.isNotBlank())
            sb.append("srsName=\"$srsName\"")
        if (srsDimension > 0)
            sb.append(" srsDimension=\"$srsDimension\"")
        if (axisLabels.any(String::isNotBlank)) {
            sb.append(" axisLabels=\"")
                    .append(axisLabels.joinToString(" "))
                    .append("\"")
        }
        if (uomLabels.any(String::isNotBlank)) {
            sb.append(" uomLabels=\"")
                    .append(uomLabels.joinToString(" "))
                    .append("\"")
        }

        return sb.toString()
    }
}
