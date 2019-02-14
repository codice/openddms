/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms

/**
 * An interface for writing a [DdmsResource][org.codice.ddms.DdmsResource] to some output.
 */
interface DdmsWriter {
    fun write()
}
