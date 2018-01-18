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

package androidx.graphics

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PorterDuffTest {
    @Test fun xfermode() {
        val p = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).applyCanvas {
            val p = Paint().apply { color = 0xffffffff.toInt() }
            drawRect(0f, 0f, 1f, 1f, p)

            p.color = 0x7f00ff00
            p.xfermode = PorterDuff.Mode.SRC.toXfermode()
            drawRect(0f, 0f, 1f, 1f, p)
        }.getPixel(0, 0)

        assertEquals(0x7f00ff00, p)
    }

    @Test fun colorFilter() {
        val p = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).applyCanvas {
            val p = Paint().apply { color = 0xffffffff.toInt() }
            drawRect(0f, 0f, 1f, 1f, p)

            p.color = 0xff000000.toInt()
            p.colorFilter = PorterDuff.Mode.SRC.toColorFilter(0xff00ff00.toInt())
            drawRect(0f, 0f, 1f, 1f, p)
        }.getPixel(0, 0)

        assertEquals(0xff00ff00.toInt(), p)
    }
}
