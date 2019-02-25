/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms

/**
 * An interface for creating a [DdmsResource] from a DDMS document.
 */
interface DdmsReader {

    /**
     * Reads in a DDMS document.
     *
     * @return An immutable [DdmsResource] or throws an IllegalArgumentException.
     * @throws IllegalArgumentException is thrown if the DDMS document is not valid.
     */
    fun read(): DdmsResource
}
