/*
 * Copyright (C) 2017 The Android Open Source Project
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

package androidx.content

import android.test.mock.MockContext
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class ContextTest {
    @Test fun systemService() {
        var lookup: Class<*>? = null
        val context = object : MockContext() {
            override fun getSystemServiceName(serviceClass: Class<*>): String? {
                lookup = serviceClass
                return if (serviceClass == Unit::class.java) "unit" else null
            }

            override fun getSystemService(name: String): Any? {
                return if (name == "unit") Unit else null
            }
        }
        val actual = context.systemService<Unit>()
        assertEquals(Unit::class.java, lookup)
        assertSame(Unit, actual)
    }
}
