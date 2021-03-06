/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms

/**
 * Generic interface for the [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern)
 */
interface Builder<out T> {
    fun build(): T
}
