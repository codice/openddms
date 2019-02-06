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
