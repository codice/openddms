/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.xml.util

import org.codice.ddms.xml.util.XmlConstants.xlinkNamespace
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
