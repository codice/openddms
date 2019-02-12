/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
