/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

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

    override fun toString(): String {
        return "<gml:Point gml:id=\"$id\" $srsAttributes xmlns:gml=\"http://www.opengis.net/gml\">$position</gml:Point>"
    }
}
