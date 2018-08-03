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
package org.codice.ddms.v2.builder

import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.SubjectCoverage
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SubjectCoverageBuilderTest {
    private val subjectCoverage = SubjectCoverage(listOf(Category("label")), listOf("keywords"))

    @Test
    fun `building with methods`() {
        val result = SubjectCoverageBuilder()
                .categories(subjectCoverage.categories)
                .keywords(subjectCoverage.keywords)
                .build()

        assertThat(result, equalTo(subjectCoverage))
    }

    @Test
    fun `building with category method`() {
        val result = SubjectCoverageBuilder()
                .category("label")
                .keywords(subjectCoverage.keywords)
                .build()

        assertThat(result, equalTo(subjectCoverage))
    }

    @Test
    fun `building with lambda`() {
        val result = subjectCoverage {
            categories(subjectCoverage.categories)
            keywords(subjectCoverage.keywords)
        }

        assertThat(result, equalTo(subjectCoverage))
    }
}
