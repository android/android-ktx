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

package androidx.graphics

import android.graphics.Bitmap
import org.junit.Assert.assertEquals
import org.junit.Test

class BitmapTest {
    @Test fun applyCanvas() {
        val p = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888).applyCanvas {
            drawColor(0x40302010)
        }.getPixel(1, 1)

        assertEquals(0x40302010, p)
    }
}
