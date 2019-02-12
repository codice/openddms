/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

data class Rights(
    val privacyAct: Boolean = false,
    val intellectualProperty: Boolean = false,
    val copyright: Boolean = false
)
