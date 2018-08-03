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
package org.codice.ddms.v2.summary

data class SubjectCoverage(
    val categories: List<Category>,
    val keywords: List<String>
) {
    init {
        require(categories.isNotEmpty() || keywords.isNotEmpty()) {
            "ddms:subjectCoverage must have at least one of the following: ddms:category, ddms:keyword"
        }
    }
}

data class Category(
    val label: String,
    val qualifier: String = "",
    val code: String = ""
)
