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
