/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource.producers

interface Producer {
    val names: List<String>
    val phones: List<String>
    val emails: List<String>
}
