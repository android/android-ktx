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

package androidx.graphics.drawable

import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Icon
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import okio.Okio.buffer
import okio.Okio.sink
import okio.Okio.source
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

@SdkSuppress(minSdkVersion = 26)
class IconTest {
    private val context = InstrumentationRegistry.getContext()

    @Test fun fromBitmapAdaptive() {
        val density = context.resources.displayMetrics.density

        val edge = (108 * density).toInt()
        val bitmap = Bitmap.createBitmap(edge, edge, ARGB_8888).apply {
            eraseColor(Color.RED)
        }
        val icon = bitmap.toAdaptiveIcon()

        val rendered = icon.toIntrinsicBitmap()
        val masked = (72 * density).toInt()
        assertEquals(masked, rendered.width)
        assertEquals(masked, rendered.height)
        // Grab a pixel from the middle to ensure we are not being masked.
        assertEquals(Color.RED, rendered.getPixel(masked / 2, masked / 2))
    }

    @Test fun fromBitmap() {
        val bitmap = Bitmap.createBitmap(1, 1, ARGB_8888).apply {
            eraseColor(Color.RED)
        }
        val icon = bitmap.toIcon()

        val rendered = icon.toIntrinsicBitmap()
        assertEquals(1, rendered.width)
        assertEquals(1, rendered.height)
        assertEquals(Color.RED, rendered.getPixel(0, 0))
    }

    @Test fun fromUri() {
        // Icon can't read from file:///android_asset/red.png so copy to a real file.

        val cacheFile = File(context.cacheDir, "red.png")
        buffer(sink(cacheFile)).use { sink ->
            buffer(source(context.assets.open("red.png"))).use { source ->
                sink.writeAll(source)
            }
        }
        val uri = Uri.fromFile(cacheFile)
        val icon = uri.toIcon()

        val rendered = icon.toIntrinsicBitmap()
        assertEquals(1, rendered.width)
        assertEquals(1, rendered.height)
        assertEquals(Color.RED, rendered.getPixel(0, 0))
    }

    @Test fun fromByteArray() {
        val bytes = buffer(source(context.assets.open("red.png"))).readByteArray()
        val icon = bytes.toIcon()

        val rendered = icon.toIntrinsicBitmap()
        assertEquals(1, rendered.width)
        assertEquals(1, rendered.height)
        assertEquals(Color.RED, rendered.getPixel(0, 0))
    }

    private fun Icon.toIntrinsicBitmap(): Bitmap {
        val drawable = loadDrawable(context)
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, ARGB_8888)
        drawable.setBounds(0, 0, drawable.intrinsicHeight, drawable.intrinsicHeight)
        drawable.draw(Canvas(bitmap))
        return bitmap
    }
}
