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

package androidx.core.graphics

import android.graphics.Canvas
import android.graphics.Matrix
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class CanvasTest {
    private val values = FloatArray(9)
    private val canvas = Canvas(createBitmap(1, 1))

    @Suppress("DEPRECATION")
    @Test fun withSave() {
        val beforeCount = canvas.saveCount

        canvas.matrix.getValues(values)
        val x = values[Matrix.MTRANS_X]
        val y = values[Matrix.MTRANS_Y]

        canvas.withSave {
            assertThat(beforeCount).isLessThan(saveCount)
            translate(10.0f, 10.0f)
        }

        canvas.matrix.getValues(values)
        assertEquals(x, values[Matrix.MTRANS_X])
        assertEquals(y, values[Matrix.MTRANS_Y])

        assertEquals(beforeCount, canvas.saveCount)
    }

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

    @Suppress("DEPRECATION")
    @Test fun withMatrix() {
        val originMatrix = canvas.matrix

        val inputMatrix = Matrix()
        inputMatrix.postTranslate(16.0f, 32.0f)
        inputMatrix.postRotate(90.0f, 16.0f, 32.0f)
        inputMatrix.postScale(2.0f, 4.0f, 16.0f, 32.0f)

        val beforeCount = canvas.saveCount
        canvas.withMatrix(inputMatrix) {
            assertThat(beforeCount).isLessThan(saveCount)
            assertEquals(inputMatrix, matrix)
        }

        assertEquals(originMatrix, canvas.matrix)
        assertEquals(beforeCount, canvas.saveCount)
    }
}
