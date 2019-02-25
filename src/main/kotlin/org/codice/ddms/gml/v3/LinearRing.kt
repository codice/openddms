/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

private const val NUMBER_OF_POSITION_ELEMENTS = 4

/**
 * Data class representing a [gml:LinearRing](http://schemas.opengis.net/gml/3.1.1/base/geometryBasic2d.xsd).
 *
 * Must contain at least four [Positions][Position].
 *
 * @param positions List of [Positions][Position] creating the [LinearRing]. Must be at least of size four.
 * @throws IllegalArgumentException If there is not a minimum of four [Position] elements.
 */
data class LinearRing(val positions: List<Position>) {
    init {
        require(positions.size >= NUMBER_OF_POSITION_ELEMENTS) {
            "gml:LinearRing must contain at least four gml:position"
        }
    }

    /**
     * Converts to an XML string.
     * @return XML string.
     */
    override fun toString(): String {
        return "<gml:LinearRing xmlns:gml=\"http://www.opengis.net/gml\">${positions.joinToString("")}</gml:LinearRing>"
    }
}
