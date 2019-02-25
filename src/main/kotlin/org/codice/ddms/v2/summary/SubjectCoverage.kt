/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.summary

/**
 * Subject keyword(s) that characterize the subject matter of a resource.
 *
 * Must contain at least one [Category] or [keyword][String].
 *
 * @param categories A list of [Categories][Category] that are addressed in the resource.
 * @param keywords A list of important words or concepts that are addressed in the resource.
 * @throws IllegalArgumentException If [categories] or [keywords] are empty.
 */
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
