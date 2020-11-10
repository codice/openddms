/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.validation

import java.lang.IllegalArgumentException
import org.codice.ddms.v2.summary.Link
import org.junit.Test

class LinkTest {
    @Test(expected = IllegalArgumentException::class)
    fun `a link with a blank href is invalid`() {
        Link("")
    }

    @Test
    fun `a link with a non blank href is valid`() {
        Link("href")
    }
}
