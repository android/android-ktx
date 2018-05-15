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

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.support.test.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File

class ImageDecoderTest {

    @Before fun before() {
        val bStream = ByteArrayOutputStream().apply {
            Bitmap
                .createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                .compress(Bitmap.CompressFormat.JPEG, 100, this)
        }

        context.openFileOutput(TEST_FILE.name, Context.MODE_PRIVATE).use {
            it.write(bStream.toByteArray())
        }
    }

    @After fun after() {
        TEST_FILE.delete()
    }

    @Test fun decodeBitmap() {
        val src = ImageDecoder.createSource(TEST_FILE)
        val decodedBitmap = src.decodeBitmap { _, _ -> setTargetSize(10, 10) }
        assertEquals(10, decodedBitmap.width)
        assertEquals(10, decodedBitmap.height)
    }

    @Test fun decodeDrawable() {
        val src = ImageDecoder.createSource(TEST_FILE)
        val decodedDrawable = src.decodeDrawable { _, _ -> setTargetSize(10, 10) }
        assertEquals(10, decodedDrawable.intrinsicWidth)
        assertEquals(10, decodedDrawable.intrinsicHeight)
    }

    companion object {
        private val context = InstrumentationRegistry.getContext()
        private val TEST_FILE = File(context.filesDir, "test.jpg")
    }
}
