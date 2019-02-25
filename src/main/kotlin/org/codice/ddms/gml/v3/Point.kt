/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

/**
 * Data class representing a [gml:Point](http://schemas.opengis.net/gml/3.1.1/base/geometryBasic0d1d.xsd).
 *
 * @param id A non-blank [String] of the id attribute.
 * @param position The location of the [Point].
 * @param srsAttributes The [SrsAttributes] of the [Point]. Must at least have a non-blank [SrsAttributes.srsName].
 * @throws IllegalArgumentException If [id] or [SrsAttributes.srsName] are blank.
 */
data class Point(
    val id: String,
    val position: Position,
    val srsAttributes: SrsAttributes
) {
    init {
        require(id.isNotBlank()) {
            "gml:Point must contain a gml:id"
        }
        require(srsAttributes.srsName.isNotBlank()) {
            "gml:Point must contain a srsName"
        }
    }

    /**
     * Converts to an XML string.
     * @return XML string.
     */
    override fun toString(): String {
        return "<gml:Point gml:id=\"$id\" $srsAttributes xmlns:gml=\"http://www.opengis.net/gml\">$position</gml:Point>"
    }
}
