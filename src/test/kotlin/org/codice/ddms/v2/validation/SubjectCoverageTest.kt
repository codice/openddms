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
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.SubjectCoverage
import org.junit.Test

class SubjectCoverageTest {
    private val category = Category("label")
    private val keyword = "keyword"

    @Test
    fun `a SubjectCoverage with a category is valid`() {
        SubjectCoverage(listOf(category), emptyList())
    }

    @Test
    fun `a SubjectCoverage with a keyword is valid`() {
        SubjectCoverage(emptyList(), listOf(keyword))
    }

    @Test
    fun `a SubjectCoverage with a category and keyword is valid`() {
        SubjectCoverage(listOf(category), listOf(keyword))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a SubjectCoverage with missing categories and keywords is invalid`() {
        SubjectCoverage(emptyList(), emptyList())
    }
}
