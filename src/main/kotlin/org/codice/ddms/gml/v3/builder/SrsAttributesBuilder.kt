/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.Builder
import org.codice.ddms.gml.v3.SrsAttributes

class SrsAttributesBuilder : Builder<SrsAttributes> {
    companion object {
        fun srsAttributes(init: SrsAttributesBuilder.() -> Unit) =
                SrsAttributesBuilder().apply(init).build()
    }

    private var srsName: String = ""
    private var srsDimension: Int = 0
    private val axisLabels: ArrayList<String> = arrayListOf()
    private val uomLabels: ArrayList<String> = arrayListOf()

    fun srsName(srsName: String): SrsAttributesBuilder {
        this.srsName = srsName
        return this
    }

    fun srsDimension(srsDimension: Int): SrsAttributesBuilder {
        this.srsDimension = srsDimension
        return this
    }

    fun axisLabels(vararg axisLabel: String): SrsAttributesBuilder {
        axisLabels.addAll(axisLabel)
        return this
    }

    fun axisLabels(axisLabel: List<String>): SrsAttributesBuilder {
        axisLabels.addAll(axisLabel)
        return this
    }

    fun uomLabels(vararg uomLabel: String): SrsAttributesBuilder {
        uomLabels.addAll(uomLabel)
        return this
    }

    fun uomLabels(uomLabel: List<String>): SrsAttributesBuilder {
        uomLabels.addAll(uomLabel)
        return this
    }

    override fun build(): SrsAttributes = SrsAttributes(srsName,
            srsDimension,
            axisLabels,
            uomLabels)
}
