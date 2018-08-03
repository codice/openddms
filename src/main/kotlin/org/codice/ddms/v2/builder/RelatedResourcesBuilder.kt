/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.ddms.v2.builder

import org.codice.ddms.Builder
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.codice.ddms.v2.summary.RelatedResource
import org.codice.ddms.v2.summary.RelatedResources

fun relatedResources(init: RelatedResourcesBuilder.() -> Unit) =
        RelatedResourcesBuilder().apply(init).build()

class RelatedResourcesBuilder : Builder<RelatedResources> {
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
