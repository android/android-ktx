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

import android.graphics.Matrix
import org.junit.Assert.assertEquals
import org.junit.Test

class MatrixTest {
    @Test fun translationMatrix() {
        val r = translationMatrix(2.0f, 3.0f).values()
        assertEquals(1.0f, r[Matrix.MSCALE_X])
        assertEquals(0.0f, r[Matrix.MSKEW_X])
        assertEquals(2.0f, r[Matrix.MTRANS_X])
        assertEquals(0.0f, r[Matrix.MSKEW_Y])
        assertEquals(1.0f, r[Matrix.MSCALE_Y])
        assertEquals(3.0f, r[Matrix.MTRANS_Y])
    }

    @Test fun scaleMatrix() {
        val r = scaleMatrix(2.0f, 3.0f).values()
        assertEquals(2.0f, r[Matrix.MSCALE_X])
        assertEquals(0.0f, r[Matrix.MSKEW_X])
        assertEquals(0.0f, r[Matrix.MTRANS_X])
        assertEquals(0.0f, r[Matrix.MSKEW_Y])
        assertEquals(3.0f, r[Matrix.MSCALE_Y])
        assertEquals(0.0f, r[Matrix.MTRANS_Y])
    }

    @Test fun rotationMatrix() {
        val r = rotationMatrix(90.0f, 2.0f, 3.0f).values()
        assertEquals(0.0f, r[Matrix.MSCALE_X])
        assertEquals(-1.0f, r[Matrix.MSKEW_X])
        assertEquals(5.0f, r[Matrix.MTRANS_X])
        assertEquals(1.0f, r[Matrix.MSKEW_Y])
        assertEquals(0.0f, r[Matrix.MSCALE_Y])
        assertEquals(1.0f, r[Matrix.MTRANS_Y])
    }

    @Test fun multiply() {
        val t = translationMatrix(2.0f, 3.0f)
        val s = scaleMatrix(2.0f, 3.0f)
        val r = (s * t).values()

        assertEquals(4.0f, r[Matrix.MTRANS_X], 1e-4f)
        assertEquals(9.0f, r[Matrix.MTRANS_Y], 1e-4f)
    }
}
