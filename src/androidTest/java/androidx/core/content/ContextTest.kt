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

package androidx.core.content

import android.content.Intent
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.test.mock.MockContext
import androidx.core.kotlin.test.R
import androidx.core.os.bundleOf
import androidx.testutils.getAttributeSet
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ContextTest {
    private val context = InstrumentationRegistry.getContext()

    @SdkSuppress(minSdkVersion = 23)
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

    @Test fun withStyledAttributes() {
        context.withStyledAttributes(attrs = intArrayOf(android.R.attr.textColorPrimary)) {
            val resourceId = getResourceId(0, -1)
            assertTrue(resourceId != 1)
        }

        context.withStyledAttributes(
            android.R.style.Theme_Light,
            intArrayOf(android.R.attr.textColorPrimary)
        ) {
            val resourceId = getResourceId(0, -1)
            assertTrue(resourceId != 1)
        }

        val attrs = context.getAttributeSet(R.layout.test_attrs)
        context.withStyledAttributes(attrs, R.styleable.SampleAttrs) {
            assertTrue(getInt(R.styleable.SampleAttrs_sample, -1) != -1)
        }

        context.withStyledAttributes(attrs, R.styleable.SampleAttrs, 0, 0) {
            assertTrue(getInt(R.styleable.SampleAttrs_sample, -1) != -1)
        }
    }

    @Test fun intentFor() {
        val data = Uri.parse("uri")

        val intent = context.intentFor<Unit>(
            action = "action",
            data = data,
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK,
            type = "type",
            categories = setOf("category1", "category2"),
            extras = bundleOf("key1" to "value1", "key2" to "value2")
        )

        assertEquals("action", intent.action)
        assertEquals(data, intent.data)
        assertEquals(Intent.FLAG_ACTIVITY_CLEAR_TASK, intent.flags)
        assertEquals("type", intent.type)
        assertTrue(intent.hasCategory("category1"))
        assertTrue(intent.hasCategory("category2"))
        assertTrue(intent.hasExtra("key1"))
        assertTrue(intent.hasExtra("key2"))
        assertEquals(context.packageName, intent.component.packageName)
        assertEquals(Unit::class.java.name, intent.component.className)
    }
}
