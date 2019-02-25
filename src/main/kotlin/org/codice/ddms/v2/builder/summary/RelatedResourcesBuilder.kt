/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.summary

import org.codice.ddms.Builder
import org.codice.ddms.v2.builder.security.SecurityAttributeBuilder
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.RelatedResource
import org.codice.ddms.v2.summary.RelatedResources

class RelatedResourcesBuilder : Builder<RelatedResources> {
    companion object {
        fun relatedResources(init: RelatedResourcesBuilder.() -> Unit) =
                RelatedResourcesBuilder().apply(init).build()
    }

    private lateinit var relationship: String
    private var direction: RelatedResources.Direction = RelatedResources.Direction.Outbound
    private val resources: ArrayList<RelatedResource> = arrayListOf()
    private var securityAttributes: SecurityAttributes = SecurityAttributes()

    fun relationship(relationship: String): RelatedResourcesBuilder {
        this.relationship = relationship
        return this
    }

    fun direction(direction: RelatedResources.Direction): RelatedResourcesBuilder {
        this.direction = direction
        return this
    }

    fun resources(vararg resource: RelatedResource): RelatedResourcesBuilder {
        resources.addAll(resource)
        return this
    }

    fun resources(resource: List<RelatedResource>): RelatedResourcesBuilder {
        resources.addAll(resource)
        return this
    }

    fun resource(init: RelatedResourceBuilder.() -> Unit): RelatedResourcesBuilder {
        val resource = RelatedResourceBuilder().apply(init).build()
        return resources(resource)
    }

    fun securityAttributes(securityAttributes: SecurityAttributes): RelatedResourcesBuilder {
        this.securityAttributes = securityAttributes
        return this
    }

    fun securityAttributes(init: SecurityAttributeBuilder.() -> Unit): RelatedResourcesBuilder {
        securityAttributes = SecurityAttributeBuilder().apply(init).build()
        return this
    }

    override fun build(): RelatedResources = RelatedResources(relationship,
            direction,
            resources,
            securityAttributes)
}
