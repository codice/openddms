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
package org.codice.ddms.v2.builder.producers

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
