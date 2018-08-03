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
package org.codice.ddms

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

interface DdmsResource {
    val identifiers: List<Identifier>
    val titles: List<Title>
    val subtitles: List<Title>
    val description: Description?
    val languages: List<Language>
    val dates: Dates?
    val rights: Rights?
    val sources: List<Source>
    val types: List<Type>
    val creators: List<Contact>
    val publishers: List<Contact>
    val contributors: List<Contact>
    val pointOfContacts: List<Contact>
    val format: Format?
    val subjectCoverage: SubjectCoverage
    val virtualCoverages: List<VirtualCoverage>
    val temporalCoverages: List<TemporalCoverage>
    val geospatialCoverages: List<GeospatialCoverage>
    val relatedResources: List<RelatedResources>
    val security: Security
}
