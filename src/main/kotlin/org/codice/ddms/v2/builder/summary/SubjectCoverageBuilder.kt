/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

import org.codice.ddms.Builder
import org.codice.ddms.v2.summary.Category
import org.codice.ddms.v2.summary.SubjectCoverage

class SubjectCoverageBuilder : Builder<SubjectCoverage> {
    companion object {
        fun subjectCoverage(init: SubjectCoverageBuilder.() -> Unit) =
                SubjectCoverageBuilder().apply(init).build()
    }

    private val categories: ArrayList<Category> = arrayListOf()
    private val keywords: ArrayList<String> = arrayListOf()

    fun categories(vararg category: Category): SubjectCoverageBuilder {
        categories.addAll(category)
        return this
    }

    fun categories(category: List<Category>): SubjectCoverageBuilder {
        categories.addAll(category)
        return this
    }

    fun category(label: String, qualifier: String = "", code: String = "") =
            categories(Category(label, qualifier, code))

    fun keywords(vararg keyword: String): SubjectCoverageBuilder {
        keywords.addAll(keyword)
        return this
    }

    fun keywords(keyword: List<String>): SubjectCoverageBuilder {
        keywords.addAll(keyword)
        return this
    }

    override fun build(): SubjectCoverage = SubjectCoverage(categories, keywords)
}
