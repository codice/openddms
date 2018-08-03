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
package org.codice.ddms.v2

import org.codice.ddms.DdmsResource
import org.codice.ddms.v2.format.Format
import org.codice.ddms.v2.resource.Contact
import org.codice.ddms.v2.resource.Dates
import org.codice.ddms.v2.resource.Identifier
import org.codice.ddms.v2.resource.Language
import org.codice.ddms.v2.resource.Rights
import org.codice.ddms.v2.resource.Source
import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.resource.Type
import org.codice.ddms.v2.security.Security
import org.codice.ddms.v2.summary.Description
import org.codice.ddms.v2.summary.GeospatialCoverage
import org.codice.ddms.v2.summary.RelatedResources
import org.codice.ddms.v2.summary.SubjectCoverage
import org.codice.ddms.v2.summary.TemporalCoverage
import org.codice.ddms.v2.summary.VirtualCoverage

data class Ddms20Resource(
    override val identifiers: List<Identifier>,
    override val titles: List<Title>,
    override val subjectCoverage: SubjectCoverage,
    override val security: Security,
    override val subtitles: List<Title> = emptyList(),
    override val description: Description? = null,
    override val languages: List<Language> = emptyList(),
    override val dates: Dates? = null,
    override val rights: Rights? = null,
    override val sources: List<Source> = emptyList(),
    override val types: List<Type> = emptyList(),
    override val creators: List<Contact> = emptyList(),
    override val publishers: List<Contact> = emptyList(),
    override val contributors: List<Contact> = emptyList(),
    override val pointOfContacts: List<Contact> = emptyList(),
    override val format: Format? = null,
    override val virtualCoverages: List<VirtualCoverage> = emptyList(),
    override val temporalCoverages: List<TemporalCoverage> = emptyList(),
    override val geospatialCoverages: List<GeospatialCoverage> = emptyList(),
    override val relatedResources: List<RelatedResources> = emptyList()
) : DdmsResource {
    init {
        require(identifiers.isNotEmpty()) {
            "ddms:Resource must contain at least one ddms:identifier"
        }
        require(titles.isNotEmpty()) {
            "ddms:Resource must contain at least one ddms:title"
        }
        require(creators.isNotEmpty() || publishers.isNotEmpty() ||
                contributors.isNotEmpty() || pointOfContacts.isNotEmpty()) {
            "ddms:Resource must contain at least one of the following: ddms:creator, ddms:publisher, ddms:contributor, ddms:pointOfContact"
        }
    }
}
