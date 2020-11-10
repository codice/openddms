/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.xml.util

import javax.xml.stream.XMLStreamWriter
import org.codice.ddms.xml.util.XmlConstants.xlinkNamespace

/**
 * Extension function for making namespaces easier to write.
 * If the prefix isn't defined, it will set the prefix.
 *
 * @param prefix the prefix to bind this namespace to.
 * @param uri the uri to bind the prefix to.
 *
 * @see XMLStreamWriter.writeNamespace
 */
fun XMLStreamWriter.namespace(prefix: String, uri: String) {
    if (getPrefix(uri) == null) {
        setPrefix(prefix, uri)
    }

    writeNamespace(prefix, uri)
}

/**
 * Extension function for making elements easier to write.
 *
 * If the prefix isn't defined, it will set the prefix.
 * Automatically ends an element when done.
 *
 * @param namespaceUri the namespaceURI of the prefix to use.
 * @param prefix the prefix to bind this namespace to.
 * @param localName the local name of the tag.
 * @receiver constructs the body and children of this element.
 *
 * @see XMLStreamWriter.writeStartElement
 * @see XMLStreamWriter.writeEndElement
 */
fun XMLStreamWriter.element(namespaceUri: String, prefix: String, localName: String, init: () -> Unit) {
    if (getPrefix(namespaceUri) == null) {
        setPrefix(prefix, namespaceUri)
    }

    writeStartElement(namespaceUri, localName)
    init()
    writeEndElement()
}

/**
 * Extension function for making empty elements easier to write.
 *
 * If the prefix isn't defined, it will set the prefix.
 * Automatically ends an element when done.
 *
 * @param namespaceUri the namespaceURI of the prefix to use.
 * @param prefix the prefix to bind this namespace to.
 * @param localName the local name of the tag.
 * @receiver constructs the body and children of this element.
 *
 * @see XMLStreamWriter.writeEmptyElement
 */
fun XMLStreamWriter.emptyElement(namespaceUri: String, prefix: String, localName: String, init: () -> Unit) {
    if (getPrefix(namespaceUri) == null) {
        setPrefix(prefix, namespaceUri)
    }

    writeEmptyElement(namespaceUri, localName)
    init()
}

/**
 * Extension function to write [XmlConstants.xlinkNamespace] attributes.
 *
 * @param name the local name of the attribute.
 * @param value the value of the attribute.
 *
 * @see XMLStreamWriter.writeAttribute
 */
fun XMLStreamWriter.xlinkAttribute(name: String, value: String) = writeAttribute(xlinkNamespace, name, value)
