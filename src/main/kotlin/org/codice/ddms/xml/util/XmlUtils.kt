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
package org.codice.ddms.xml.util

import org.codice.ddms.xml.util.XmlConstants.xlinkNamespace
import javax.xml.stream.XMLStreamReader
import javax.xml.stream.XMLStreamWriter

fun XMLStreamWriter.namespace(prefix: String, uri: String) {
    if (getPrefix(uri) == null) {
        setPrefix(prefix, uri)
    }

    writeNamespace(prefix, uri)
}

fun XMLStreamWriter.element(namespaceUri: String, prefix: String, name: String, init: () -> Unit) {
    if (getPrefix(namespaceUri) == null) {
        setPrefix(prefix, namespaceUri)
    }

    writeStartElement(namespaceUri, name)
    init()
    writeEndElement()
}

fun XMLStreamWriter.emptyElement(namespaceUri: String, prefix: String, name: String, init: () -> Unit) {
    if (getPrefix(namespaceUri) == null) {
        setPrefix(prefix, namespaceUri)
    }

    writeEmptyElement(namespaceUri, name)
    init()
}

fun XMLStreamWriter.xlinkAttribute(name: String, value: String) = writeAttribute(xlinkNamespace, name, value)

/**
 * Pass through to nextTag() but includes the name of the next tag for documenting / logging
 * instead of having to make a comment
 *
 * @param documentedTag The name of what the next tag should be.
 */
@Suppress("UnusedPrivateMember") // TODO: I think we should keep suppressing this one, or we could log it
fun XMLStreamReader.nextTag(documentedTag: String) = nextTag()
