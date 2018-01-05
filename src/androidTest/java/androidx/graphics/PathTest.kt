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

import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import androidx.fail
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PathTest {
    @Test fun testFlattenEmptyPath() {
        Path().flatten().forEach { fail("An empty path should not have segments: " + it) }
    }

    @Test fun testFlatten() {
        val p = Path()

        // Single line
        p.lineTo(10.0f, 10.0f)
        assertEquals(
                PathSegment(PointF(), 0.0f, PointF(10.0f, 10.0f), 1.0f),
                p.flatten().iterator().next())

        // Only moves
        p.reset()
        p.moveTo(10.0f, 10.0f)
        p.moveTo(20.0f, 20.0f)
        p.flatten().forEach { fail("A path with only moves should not have segments: " + it) }

        // Mix of moves/lines
        p.reset()
        p.moveTo(10.0f, 10.0f)
        p.lineTo(20.0f, 20.0f)
        p.lineTo(60.0f, 20.0f)

        var count = 0
        p.flatten().forEach {
            count++
            assertNotEquals(it.startFraction, it.endFraction)
        }
        assertEquals(2, count)

        // Mix of moves/lines, starts with moves, ends with moves
        p.reset()
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

        count = 0
        p.flatten().forEach {
            count++
            assertNotEquals(it.startFraction, it.endFraction)
        }
        assertEquals(3, count)
    }

    @Test fun testUnion() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 + r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 15.0f, 15.0f), r)
    }

    @Test fun testAnd() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 and r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 15.0f, 15.0f), r)
    }

    @Test fun testDifference() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 - r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 5.0f, 10.0f), r)
    }

    @Test fun testIntersection() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 0.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 or r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(5.0f, 0.0f, 10.0f, 10.0f), r)
    }

    @Test fun testEmptyIntersection() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 2.0f, 2.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 5.0f, 7.0f, 7.0f, Path.Direction.CW) }

        val p = r1 or r2
        assertTrue(p.isEmpty)
    }

    @Test fun testXor() {
        val r1 = Path().apply { addRect(0.0f, 0.0f, 10.0f, 10.0f, Path.Direction.CW) }
        val r2 = Path().apply { addRect(5.0f, 5.0f, 15.0f, 15.0f, Path.Direction.CW) }

        val p = r1 xor r2
        val r = RectF()
        p.computeBounds(r, true)

        assertEquals(RectF(0.0f, 0.0f, 15.0f, 15.0f), r)
    }
}
