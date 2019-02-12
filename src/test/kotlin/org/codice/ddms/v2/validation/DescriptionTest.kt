/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.Description
import org.junit.Test

class DescriptionTest {
    @Test(expected = IllegalArgumentException::class)
    fun `a Description with no classification is invalid`() {
        Description(SecurityAttributes(ownerProducer = listOf("")), "description")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Description with no ownerProducer is invalid`() {
        Description(SecurityAttributes(classification = Classification.U), "description")
    }

    @Test
    fun `a Description with a classification and ownerProducer is valid`() {
        Description(SecurityAttributes(classification = Classification.U, ownerProducer = listOf("USA")), "description")
    }
}
