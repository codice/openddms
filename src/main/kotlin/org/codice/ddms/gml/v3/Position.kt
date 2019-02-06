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

data class Position(
    val points: List<Double>,
    val srsAttributes: SrsAttributes = SrsAttributes()
) {
    override fun toString(): String {
        val srs = if (srsAttributes.toString().isNotBlank()) {
            " $srsAttributes"
        } else ""
        return """<gml:pos$srs xmlns:gml="http://www.opengis.net/gml">
            |${points.joinToString(" ")}
            |</gml:pos>
        """.trimMargin().replace("\n", "")
    }
}
