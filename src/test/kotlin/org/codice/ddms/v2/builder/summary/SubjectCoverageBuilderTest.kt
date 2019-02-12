/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

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
        val result = SubjectCoverageBuilder.subjectCoverage {
            categories(subjectCoverage.categories)
            keywords(subjectCoverage.keywords)
        }

        assertThat(result, equalTo(subjectCoverage))
    }
}
