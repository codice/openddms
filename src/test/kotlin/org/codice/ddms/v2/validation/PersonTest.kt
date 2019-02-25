/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.resource.producers.Person
import org.junit.Test

class PersonTest {
    @Test
    fun `a Person with a name is valid`() {
        Person(listOf("name"), "surname")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Person with no name is invalid`() {
        Person(emptyList(), "surname")
    }
}
