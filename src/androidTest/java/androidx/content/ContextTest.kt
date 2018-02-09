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

import android.graphics.Bitmap.Config.ARGB_4444
import android.graphics.BitmapFactory
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.test.mock.MockContext
import androidx.getAttributeSet
import androidx.kotlin.test.R
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

    @Test fun getBitmapFromResources() {
        val original = BitmapFactory.decodeResource(context.resources, R.drawable.box)
        val extension = context.resources.getBitmap(R.drawable.box)

        assertEquals(original, extension)
    }

    @Test fun getBitmapFromContext() {
        val original = BitmapFactory.decodeResource(context.resources, R.drawable.box)
        val extension = context.getBitmap(R.drawable.box)

        assertEquals(original, extension)
    }

    @Test fun getBitmapFromResourcesWithOptions() {
        val opts = BitmapFactory.Options().apply {
            inPreferredConfig = ARGB_4444
            inSampleSize = 2
        }
        val original = BitmapFactory.decodeResource(context.resources, R.drawable.box, opts)
        val extension = context.resources.getBitmap(R.drawable.box, opts)

        assertEquals(original, extension)
    }

    @Test fun getBitmapFromContextWithOptions() {
        val opts = BitmapFactory.Options().apply {
            inPreferredConfig = ARGB_4444
            inSampleSize = 2
        }
        val original = BitmapFactory.decodeResource(context.resources, R.drawable.box, opts)
        val extension = context.getBitmap(R.drawable.box, opts)

        assertEquals(original, extension)
    }
}
