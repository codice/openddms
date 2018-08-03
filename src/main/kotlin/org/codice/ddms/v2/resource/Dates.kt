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
package org.codice.ddms.v2.resource

import org.codice.ddms.DateFormat

data class Dates(
    val created: String = "",
    val posted: String = "",
    val validTil: String = "",
    val infoCutOff: String = ""
) {
    init {
        if (created.isNotBlank()) {
            require(DateFormat.isValid(created)) {
                "ddms:dates created is an invalid date"
            }
        }
        if (posted.isNotBlank()) {
            require(DateFormat.isValid(posted)) {
                "ddms:dates posted is an invalid date"
            }
        }
        if (validTil.isNotBlank()) {
            require(DateFormat.isValid(validTil)) {
                "ddms:dates validTil is an invalid date"
            }
        }
        if (infoCutOff.isNotBlank()) {
            require(DateFormat.isValid(infoCutOff)) {
                "ddms:dates infoCutOff is an invalid date"
            }
        }
    }
}
