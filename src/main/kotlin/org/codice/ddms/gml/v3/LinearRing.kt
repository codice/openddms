/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

private const val NUMBER_OF_POSITION_ELEMENTS = 4

data class LinearRing(val positions: List<Position>) {
    init {
        require(positions.size >= NUMBER_OF_POSITION_ELEMENTS) {
            "gml:LinearRing must contain at least four gml:position"
        }
    }

    override fun toString(): String {
        return "<gml:LinearRing xmlns:gml=\"http://www.opengis.net/gml\">${positions.joinToString("")}</gml:LinearRing>"
    }
}
