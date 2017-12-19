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
import android.graphics.Canvas
import android.graphics.Matrix
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CanvasTest {
    private val values = FloatArray(9)
    private val canvas = Canvas(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))

    @Test fun withTranslation() {
        val beforeCount = canvas.saveCount
        canvas.withTranslation(x = 16.0f, y = 32.0f) {
            assertThat(beforeCount).isLessThan(saveCount)

            @Suppress("DEPRECATION")
            matrix.getValues(values) // will work for a software canvas

            assertEquals(16.0f, values[Matrix.MTRANS_X])
            assertEquals(32.0f, values[Matrix.MTRANS_Y])

        }
        assertEquals(beforeCount, canvas.saveCount)
    }

    @Test fun withRotation() {
        val beforeCount = canvas.saveCount
        canvas.withRotation(degrees = 90.0f, pivotX = 16.0f, pivotY = 32.0f) {
            assertThat(beforeCount).isLessThan(saveCount)

            @Suppress("DEPRECATION")
            matrix.getValues(values) // will work for a software canvas

            assertEquals(48.0f, values[Matrix.MTRANS_X])
            assertEquals(16.0f, values[Matrix.MTRANS_Y])
            assertEquals(-1.0f, values[Matrix.MSKEW_X])
            assertEquals(1.0f, values[Matrix.MSKEW_Y])

        }
        assertEquals(beforeCount, canvas.saveCount)
    }

    @Test fun withScale() {
        val beforeCount = canvas.saveCount
        canvas.withScale(x = 2.0f, y = 4.0f, pivotX = 16.0f, pivotY = 32.0f) {
            assertThat(beforeCount).isLessThan(saveCount)

            @Suppress("DEPRECATION")
            matrix.getValues(values) // will work for a software canvas

            assertEquals(-16.0f, values[Matrix.MTRANS_X])
            assertEquals(-96.0f, values[Matrix.MTRANS_Y])
            assertEquals(2.0f, values[Matrix.MSCALE_X])
            assertEquals(4.0f, values[Matrix.MSCALE_Y])

        }
        assertEquals(beforeCount, canvas.saveCount)
    }

    @Test fun withSkew() {
        val beforeCount = canvas.saveCount
        canvas.withSkew(x = 2.0f, y = 4.0f) {
            assertThat(beforeCount).isLessThan(saveCount)

            @Suppress("DEPRECATION")
            matrix.getValues(values) // will work for a software canvas

            assertEquals(2.0f, values[Matrix.MSKEW_X])
            assertEquals(4.0f, values[Matrix.MSKEW_Y])

        }
        assertEquals(beforeCount, canvas.saveCount)
    }
}
