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

import android.graphics.Path
import android.graphics.RectF
import android.support.test.filters.SdkSuppress
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PathTest {
    @SdkSuppress(minSdkVersion = 26)
    @Test fun testFlatten() {
        val p = Path()

        // Start with several moves
        p.moveTo(5.0f, 5.0f)
        p.moveTo(10.0f, 10.0f)
        p.lineTo(20.0f, 20.0f)
        p.lineTo(30.0f, 10.0f)
        // Several moves in the middle
        p.moveTo(40.0f, 10.0f)
        p.moveTo(50.0f, 10.0f)
        p.lineTo(60.0f, 20.0f)
        // End with several moves
        p.moveTo(10.0f, 10.0f)
        p.moveTo(30.0f, 30.0f)

        var count = 0
        p.flatten().forEach {
            count++
            assertNotEquals(it.startFraction, it.endFraction)
        }
        assertEquals(3, count)
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test fun testUnion() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 + r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 15.0f, 15.0f), r)
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test fun testAnd() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 and r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 15.0f, 15.0f), r)
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test fun testDifference() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 - r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 5.0f, 10.0f), r)
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test fun testIntersection() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 or r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(5.0f, 0.0f, 10.0f, 10.0f), r)
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test fun testEmptyIntersection() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 2.0f, 2.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 5.0f, 7.0f, 7.0f, Path.Direction.CW) }

        val p = r1 or r2
        assertTrue(p.isEmpty)
    }

    @SdkSuppress(minSdkVersion = 19)
    @Test fun testXor() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 5.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 xor r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 15.0f, 15.0f), r)
    }
}
