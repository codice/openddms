/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

/**
 * Data class representing a [gml:pos](http://schemas.opengis.net/gml/3.1.1/base/geometryBasic0d1d.xsd).
 *
 * @param points List of doubles representing a single location.
 * @param srsAttributes Optional [SrsAttributes] of the [Position].
 */
data class Position(
    val points: List<Double>,
    val srsAttributes: SrsAttributes = SrsAttributes()
) {
    /**
     * Converts to an XML string.
     * @return XML string.
     */
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
