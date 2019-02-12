/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.producers

import org.codice.ddms.v2.resource.producers.Service

class ServiceBuilder : ProducerBuilder<ServiceBuilder>() {
    companion object {
        fun service(init: ServiceBuilder.() -> Unit) = ServiceBuilder().apply(init).build()
    }

    override fun build() = Service(nameList, phonesList, emailsList)
}
