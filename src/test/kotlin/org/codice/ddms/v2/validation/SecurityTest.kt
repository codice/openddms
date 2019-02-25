/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.junit.Test

class SecurityTest {
    @Test(expected = IllegalArgumentException::class)
    fun `a Security with no classification is invalid`() {
        Security(SecurityAttributes(ownerProducer = listOf("")))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Security with no ownerProducer is invalid`() {
        Security(SecurityAttributes(classification = Classification.U))
    }

    @Test
    fun `a Security with a classification and ownerProducer is valid`() {
        Security(SecurityAttributes(classification = Classification.U, ownerProducer = listOf("USA")))
    }
}
