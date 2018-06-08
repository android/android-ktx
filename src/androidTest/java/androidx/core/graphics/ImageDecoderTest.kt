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

package androidx.core.graphics

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

class ImageDecoderTest {

    @Test fun decodeBitmap() {
        val src = ImageDecoder.createSource(buffer)
        val decodedBitmap = src.decodeBitmap { _, _ -> setTargetSize(10, 10) }
        assertEquals(10, decodedBitmap.width)
        assertEquals(10, decodedBitmap.height)
    }

    @Test fun decodeDrawable() {
        val src = ImageDecoder.createSource(buffer)
        val decodedDrawable = src.decodeDrawable { _, _ -> setTargetSize(10, 10) }
        assertEquals(10, decodedDrawable.intrinsicWidth)
        assertEquals(10, decodedDrawable.intrinsicHeight)
    }

    companion object {
        private lateinit var buffer: ByteBuffer

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            val stream = ByteArrayOutputStream().apply {
                Bitmap
                    .createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                    .compress(Bitmap.CompressFormat.JPEG, 100, this)
            }

            buffer = ByteBuffer.wrap(stream.toByteArray())
        }
    }
}
