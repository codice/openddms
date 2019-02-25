/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.resource.producers

import org.codice.ddms.v2.resource.producers.Organization

class OrganizationBuilder : ProducerBuilder<OrganizationBuilder>() {
    companion object {
        fun organization(init: OrganizationBuilder.() -> Unit) = OrganizationBuilder().apply(init).build()
    }

    override fun build() = Organization(nameList, phonesList, emailsList)
}
