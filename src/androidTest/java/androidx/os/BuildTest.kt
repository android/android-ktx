/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.os

import android.os.Build
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BuildTest {

    companion object {
        /* Used to restore sdk version after all tests in this class have passed */
        private val sdkVersion = Build.VERSION.SDK_INT

        @AfterClass @JvmStatic fun afterAll() {
            setSdkVersion(sdkVersion)
        }

        /* Used to stub sdk version */
        private fun setSdkVersion(version: Int) {
            Build.VERSION::class.java.getField("SDK_INT")?.let {
                it.isAccessible = true

                it.setInt(this, version)
            }
        }
    }

    @Test
    fun fromApi() {
        var name: String? = null

        setSdkVersion(15)
        fromApi(16, {
            name = "ICE_CREAM_SANDWICH_MR1"
        })
        assertNull(name)

        setSdkVersion(16)
        fromApi(16, {
            name = "ICE_CREAM_SANDWICH_MR1"
        })
        assertEquals(name, "ICE_CREAM_SANDWICH_MR1")
    }

    @Test
    fun toApi() {
        var name: String? = null

        setSdkVersion(21)
        toApi(21, {
            name = "KIT_KAT"
        })
        assertNull(name)

        setSdkVersion(19)
        toApi(21, {
            name = "KIT_KAT"
        })
        assertEquals(name, "KIT_KAT")
    }
}