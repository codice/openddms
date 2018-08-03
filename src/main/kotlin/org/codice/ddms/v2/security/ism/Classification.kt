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
package org.codice.ddms.v2.security.ism

enum class Classification(val value: String) {
    U("U"),
    C("C"),
    S("S"),
    TS("TS"),
    R("R"),
    CTS("CTS"),
    CTS_B("CTS-B"),
    CTS_BALK("CTS-BALK"),
    NU("NU"),
    NR("NR"),
    NC("NC"),
    NS("NS"),
    NS_S("NS-S"),
    NS_A("NS-A"),
    CTSA("CTSA"),
    NSAT("NSAT"),
    NCA("NCA"),
    NO_CLASSIFICATION("No classification");

    companion object {
        fun getEnum(value: String): Classification {
            return valueOf(value.replace("-", "_"))
        }
    }
}
