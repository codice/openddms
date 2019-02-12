/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
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
