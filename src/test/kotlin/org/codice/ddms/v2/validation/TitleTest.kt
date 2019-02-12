/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.junit.Test

class TitleTest {
    @Test(expected = IllegalArgumentException::class)
    fun `a Title with no classification is invalid`() {
        Title(SecurityAttributes(ownerProducer = listOf("")), "Title")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Title with no ownerProducer is invalid`() {
        Title(SecurityAttributes(classification = Classification.U), "Title")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Title with a blank value is invalid`() {
        Title(SecurityAttributes(classification = Classification.U, ownerProducer = listOf("USA")), "")
    }

    @Test
    fun `a Title with a classification and ownerProducer is valid`() {
        Title(SecurityAttributes(classification = Classification.U, ownerProducer = listOf("USA")), "Title")
    }
}
