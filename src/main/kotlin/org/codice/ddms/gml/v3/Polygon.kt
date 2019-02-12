/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

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

    override fun toString(): String {
        return "<gml:Polygon gml:id=\"$id\" $srsAttributes xmlns:gml=\"http://www.opengis.net/gml\">" +
                "<gml:exterior>$exterior</gml:exterior></gml:Polygon>"
    }
}
