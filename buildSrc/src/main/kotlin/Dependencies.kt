/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
// Default package
object Versions {

    const val project = "1.2-SNAPSHOT"

    const val javaTarget = "1.8"
    const val dokkaJvmVersion = 8

    const val kotlin = "1.7.22"
    const val dokka = "1.7.20"
    const val spotless = "6.12.0"
    const val detekt = "1.22.0"
    const val slf4j = "1.7.25"
    const val mockito = "2.24.0"
}

object Libs {
    const val slf4j = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
}
