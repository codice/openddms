/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

/**
 * Data class representing a [gml:Polygon](http://schemas.opengis.net/gml/3.1.1/base/geometryBasic2d.xsd).
 *
 * @param id A non-blank [String] of the id attribute.
 * @param exterior A [LinearRing] encompassing the [Polygon].
 * @param srsAttributes The [SrsAttributes] of the [Polygon]. Must at least have a non-blank [SrsAttributes.srsName].
 * @throws IllegalArgumentException If [id] or [SrsAttributes.srsName] are blank.
 */
data class Polygon(
    val id: String,
    val exterior: LinearRing,
    val srsAttributes: SrsAttributes
) {
    init {
        require(id.isNotBlank()) {
            "gml:Polygon must contain a gml:id"
        }
        require(srsAttributes.srsName.isNotBlank()) {
            "gml:Polygon must contain a srsName"
        }
    }

    /**
     * Converts to an XML string.
     * @return XML string.
     */
    override fun toString(): String {
        return "<gml:Polygon gml:id=\"$id\" $srsAttributes xmlns:gml=\"http://www.opengis.net/gml\">" +
                "<gml:exterior>$exterior</gml:exterior></gml:Polygon>"
    }
}
