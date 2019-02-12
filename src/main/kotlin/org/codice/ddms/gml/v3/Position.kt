/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
