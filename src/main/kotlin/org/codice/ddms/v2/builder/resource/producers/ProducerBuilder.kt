/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.resource.producers

import org.codice.ddms.Builder
import org.codice.ddms.v2.resource.producers.Producer

abstract class ProducerBuilder<out T : ProducerBuilder<T>> : Builder<Producer> {
    protected val nameList: MutableList<String> = mutableListOf()
    protected val phonesList: MutableList<String> = mutableListOf()
    protected val emailsList: MutableList<String> = mutableListOf()

    @Suppress("UNCHECKED_CAST")
    fun names(vararg names: String): T {
        nameList.addAll(names)
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun names(names: List<String>): T {
        nameList.addAll(names)
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun phones(vararg phones: String): T {
        phonesList.addAll(phones)
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun phones(phones: List<String>): T {
        phonesList.addAll(phones)
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun emails(vararg emails: String): T {
        emailsList.addAll(emails)
        return this as T
    }

    @Suppress("UNCHECKED_CAST")
    fun emails(emails: List<String>): T {
        emailsList.addAll(emails)
        return this as T
    }
}
