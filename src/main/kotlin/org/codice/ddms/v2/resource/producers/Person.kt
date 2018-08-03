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
package org.codice.ddms.v2.resource.producers

data class Person(
    override val names: List<String>,
    val surname: String,
    val userId: String = "",
    val affiliation: String = "",
    override val phones: List<String> = emptyList(),
    override val emails: List<String> = emptyList()
) : Producer {
    init {
        require(names.isNotEmpty()) {
            "ddms:Person must contain at least one name"
        }
    }
}
