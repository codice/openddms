/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.resource

/**
 * Information about rights held in and over the resource.
 *
 * @param privacyAct An indicator that this product is categorized as containing personal information subject to
 * protection by the Privacy Act.
 * @param intellectualProperty An indicator identifying products under protection against reproduction and distribution
 * without the express written permission of the intellectual property rights owner.
 * @param copyright An indicator identifying products under protection against reproduction and distribution without
 * the express written permission of the copyright owner.
 */
data class Rights(
    val privacyAct: Boolean = false,
    val intellectualProperty: Boolean = false,
    val copyright: Boolean = false
)
