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
// Default package
object Versions {
    const val project = "1.0-SNAPSHOT"

    const val javaTarget = "1.8"

    const val kotlin = "1.3.21"
    const val spotless = "3.17.0"
    const val detekt = "1.0.0-RC12"
    const val slf4j = "1.7.25"
    const val mockito = "2.24.0"
}

object Libs {
    const val slf4j = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
}
