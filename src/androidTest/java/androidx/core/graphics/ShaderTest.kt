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

import android.graphics.Matrix
import android.graphics.Shader
import org.junit.Assert.assertEquals
import org.junit.Test

class ShaderTest {
    @Test
    fun testTransform() {
        @Suppress("DEPRECATION")
        val shader = Shader()
        val values = FloatArray(9)
        val matrix = Matrix()

        shader.transform {
            setTranslate(10f, 30f)
        }

        // Now read matrix from Shader
        shader.getLocalMatrix(matrix)
        matrix.getValues(values)

        // Assert that the values are as expected
        assertEquals(10f, values[Matrix.MTRANS_X])
        assertEquals(30f, values[Matrix.MTRANS_Y])
    }
}
