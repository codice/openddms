/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
// Default package
object Versions {
    const val project = "1.0-SNAPSHOT"

    const val javaTarget = "1.8"

    const val kotlin = "1.3.21"
    const val dokka = "0.9.17"
    const val spotless = "3.17.0"
    const val detekt = "1.0.0-RC12"
    const val slf4j = "1.7.25"
    const val mockito = "2.24.0"
}

object Libs {
    const val slf4j = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
}
