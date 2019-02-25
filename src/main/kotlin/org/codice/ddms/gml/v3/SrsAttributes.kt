/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3

/**
 * Data class representing all SRS attributes a gml element could have.
 *
 * [srsName](http://schemas.opengis.net/gml/3.1.1/base/referenceSystems.xsd)
 *
 * [srsDimension, axisLabels, uomLabels](http://schemas.opengis.net/gml/3.1.1/base/geometryBasic0d1d.xsd)
 *
 * @param srsName The name by which this reference system is identified..
 * @param srsDimension The length of coordinate sequence (the number of entries in the list). This dimension is
specified by the coordinate reference system. Must be non-negative.
 * @param axisLabels List of labels for all the axes of this CRS.
 * @param uomLabels List of unit of measure (uom) labels for all the axes of this CRS,
 * @throws IllegalArgumentException If [srsDimension] is negative.
 */
data class SrsAttributes @JvmOverloads constructor(
    val srsName: String = "",
    val srsDimension: Int = 0,
    val axisLabels: List<String> = emptyList(),
    val uomLabels: List<String> = emptyList()
) {
    init {
        require(srsDimension >= 0) {
            "srsDimension needs to be a positive integer"
        }
    }

    private fun srsNameToString(): String {
        return if (srsName.isNotBlank()) {
            """srsName="$srsName""""
        } else ""
    }

    private fun srsDimensionToString(): String {
        return if (srsDimension > 0) {
            """ srsDimension="$srsDimension""""
        } else ""
    }

    private fun axisLabelsToString(): String {
        return if (axisLabels.any(String::isNotBlank)) {
            """ axisLabels="${axisLabels.joinToString(" ")}""""
        } else ""
    }

    private fun uomLabelsToString(): String {
        return if (uomLabels.any(String::isNotBlank)) {
            """ uomLabels="${uomLabels.joinToString(" ")}""""
        } else ""
    }

    /**
     * Used internally in other gml classes to write the XML attribute string.
     */
    override fun toString(): String {
        return srsNameToString() +
                srsDimensionToString() +
                axisLabelsToString() +
                uomLabelsToString()
    }
}
