/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
