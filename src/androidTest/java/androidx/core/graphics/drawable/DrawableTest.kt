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

package androidx.core.graphics.drawable

import android.graphics.Bitmap.Config
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.support.test.InstrumentationRegistry
import androidx.core.graphics.createBitmap
import org.junit.Assert.assertEquals
import org.junit.Test

class DrawableTest {
    private val context = InstrumentationRegistry.getContext()
    private val resources = context.resources

    @Test fun bitmapDrawableNoSizeNoConfig() {
        val original = createBitmap(10, 10).apply {
            eraseColor(Color.RED)
        }
        val drawable = BitmapDrawable(resources, original)

        val bitmap = drawable.toBitmap()
        assertEquals(10, bitmap.width)
        assertEquals(10, bitmap.height)
        assertEquals(Config.ARGB_8888, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(5, 5))
    }

    @Test fun bitmapDrawableNoSizeRetainedConfig() {
        val original = createBitmap(10, 10).apply {
            eraseColor(Color.RED)
        }
        val drawable = BitmapDrawable(resources, original)

        val bitmap = drawable.toBitmap(config = Config.ARGB_8888)
        assertEquals(10, bitmap.width)
        assertEquals(10, bitmap.height)
        assertEquals(Config.ARGB_8888, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(5, 5))
    }

    @Test fun bitmapDrawableNoSizeDifferentConfig() {
        val original = createBitmap(10, 10).apply {
            eraseColor(Color.RED)
        }
        val drawable = BitmapDrawable(resources, original)

        val bitmap = drawable.toBitmap(config = Config.ARGB_8888)
        assertEquals(10, bitmap.width)
        assertEquals(10, bitmap.height)
        assertEquals(Config.ARGB_8888, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(5, 5))
    }

    @Test fun bitmapDrawableDifferentSizeNoConfig() {
        val original = createBitmap(10, 10).apply {
            eraseColor(Color.RED)
        }
        val drawable = BitmapDrawable(resources, original)

        val bitmap = drawable.toBitmap(20, 20)
        assertEquals(20, bitmap.width)
        assertEquals(20, bitmap.height)
        assertEquals(Config.ARGB_8888, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(10, 10))
    }

    @Test fun bitmapDrawableDifferentSizeDifferentConfig() {
        val original = createBitmap(10, 10).apply {
            eraseColor(Color.RED)
        }
        val drawable = BitmapDrawable(resources, original)

        val bitmap = drawable.toBitmap(20, 20, Config.ARGB_8888)
        assertEquals(20, bitmap.width)
        assertEquals(20, bitmap.height)
        assertEquals(Config.ARGB_8888, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(10, 10))
    }

    @Test fun drawableNoConfig() {
        val drawable = object : ColorDrawable(Color.RED) {
            override fun getIntrinsicWidth() = 10
            override fun getIntrinsicHeight() = 10
        }

        val bitmap = drawable.toBitmap()
        assertEquals(10, bitmap.width)
        assertEquals(10, bitmap.height)
        assertEquals(Config.ARGB_8888, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(5, 5))
    }

    @Test fun drawableConfig() {
        val drawable = object : ColorDrawable(Color.RED) {
            override fun getIntrinsicWidth() = 10
            override fun getIntrinsicHeight() = 10
        }

        val bitmap = drawable.toBitmap(config = Config.RGB_565)
        assertEquals(10, bitmap.width)
        assertEquals(10, bitmap.height)
        assertEquals(Config.RGB_565, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(5, 5))
    }

    @Test fun drawableSize() {
        val drawable = object : ColorDrawable(Color.RED) {
            override fun getIntrinsicWidth() = 10
            override fun getIntrinsicHeight() = 10
        }

        val bitmap = drawable.toBitmap(20, 20)
        assertEquals(20, bitmap.width)
        assertEquals(20, bitmap.height)
        assertEquals(Config.ARGB_8888, bitmap.config)
        assertEquals(Color.RED, bitmap.getPixel(10, 10))
    }

    @Test fun oldBoundsRestored() {
        val drawable = object : ColorDrawable(Color.RED) {
            override fun getIntrinsicWidth() = 10
            override fun getIntrinsicHeight() = 10
        }
        drawable.setBounds(2, 2, 8, 8)

        val bitmap = drawable.toBitmap()
        assertEquals(10, bitmap.width)
        assertEquals(10, bitmap.height)

        assertEquals(2, drawable.bounds.left)
        assertEquals(2, drawable.bounds.top)
        assertEquals(8, drawable.bounds.right)
        assertEquals(8, drawable.bounds.bottom)
    }

    @Test fun updateBoundsTest() {
        val drawable = object : ColorDrawable(Color.RED) {
            override fun getIntrinsicWidth() = 10
            override fun getIntrinsicHeight() = 10
        }
        drawable.setBounds(0, 0, 10, 10)

        drawable.updateBounds(1, 2, 3, 4)

        assertEquals(1, drawable.bounds.left)
        assertEquals(2, drawable.bounds.top)
        assertEquals(3, drawable.bounds.right)
        assertEquals(4, drawable.bounds.bottom)
    }
}
