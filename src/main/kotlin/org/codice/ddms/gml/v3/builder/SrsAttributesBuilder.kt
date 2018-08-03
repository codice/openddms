/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.ddms.gml.v3.builder

import org.codice.ddms.Builder
import org.codice.ddms.gml.v3.SrsAttributes

fun srsAttributes(init: SrsAttributesBuilder.() -> Unit) =
        SrsAttributesBuilder().apply(init).build()

class SrsAttributesBuilder : Builder<SrsAttributes> {
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
