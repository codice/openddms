/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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

/**
 * An immutable interface for interacting with a DDMS document.
 *
 * Currently it's using DDMS 2.0 objects for it's fields. It will need to be generic for any version of DDMS.
 */
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
