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
import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RectTest {
    @Test fun destructuringInt() {
        val (l, t, r, b) = Rect(4, 8, 16, 24)
        assertEquals(4, l)
        assertEquals(8, t)
        assertEquals(16, r)
        assertEquals(24, b)
    }

    @Test fun destructuringFloat() {
        val (l, t, r, b) = RectF(4.0f, 8.0f, 16.0f, 24.0f)
        assertEquals(4.0f, l)
        assertEquals(8.0f, t)
        assertEquals(16.0f, r)
        assertEquals(24.0f, b)
    }

    @Test fun unionInt() {
        val (l, t, r, b) = Rect(0, 0, 4, 4) + Rect(-1, -1, 6, 6)
        assertEquals(-1, l)
        assertEquals(-1, t)
        assertEquals(6, r)
        assertEquals(6, b)
    }

    @Test fun unionAsAndInt() {
        val (l, t, r, b) = Rect(0, 0, 4, 4) and Rect(-1, -1, 6, 6)
        assertEquals(-1, l)
        assertEquals(-1, t)
        assertEquals(6, r)
        assertEquals(6, b)
    }

    @Test fun unionFloat() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 4.0f, 4.0f) + RectF(-1.0f, -1.0f, 6.0f, 6.0f)
        assertEquals(-1.0f, l)
        assertEquals(-1.0f, t)
        assertEquals(6.0f, r)
        assertEquals(6.0f, b)
    }

    @Test fun unionAsAndFloat() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 4.0f, 4.0f) and RectF(-1.0f, -1.0f, 6.0f, 6.0f)
        assertEquals(-1.0f, l)
        assertEquals(-1.0f, t)
        assertEquals(6.0f, r)
        assertEquals(6.0f, b)
    }

    @Test fun differenceInt() {
        val r = Rect(0, 0, 4, 4) - Rect(-1, 2, 6, 6)
        assertEquals(Rect(0, 0, 4, 2), r.bounds)
    }

    @Test fun differenceFloat() {
        val r = RectF(0.0f, 0.0f, 4.0f, 4.0f) - RectF(-1.0f, 2.0f, 6.0f, 6.0f)
        assertEquals(Rect(0, 0, 4, 2), r.bounds)
    }

    @Test fun intersectionAsOrInt() {
        val (l, t, r, b) = Rect(0, 0, 4, 4) or Rect(2, 2, 6, 6)
        assertEquals(2, l)
        assertEquals(2, t)
        assertEquals(4, r)
        assertEquals(4, b)
    }

    @Test fun intersectionAsOrFloat() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 4.0f, 4.0f) or RectF(2.0f, 2.0f, 6.0f, 6.0f)
        assertEquals(2.0f, l)
        assertEquals(2.0f, t)
        assertEquals(4.0f, r)
        assertEquals(4.0f, b)
    }

    @Test fun xorInt() {
        val r = Rect(0, 0, 4, 4) xor Rect(2, 2, 6, 6)
        assertEquals(Rect(0, 0, 4, 4) and Rect(2, 2, 6, 6), r.bounds)
        assertFalse(r.contains(3, 3))
    }

    @Test fun xorFloat() {
        val r = RectF(0.0f, 0.0f, 4.0f, 4.0f) xor RectF(2.0f, 2.0f, 6.0f, 6.0f)
        assertEquals(Rect(0, 0, 4, 4) and Rect(2, 2, 6, 6), r.bounds)
        assertFalse(r.contains(3, 3))
    }

    @Test fun offsetInt() {
        val (l, t, r, b) = Rect(0, 0, 2, 2) + 2
        assertEquals(l, 2)
        assertEquals(t, 2)
        assertEquals(r, 4)
        assertEquals(b, 4)
    }

    @Test fun offsetPoint() {
        val (l, t, r, b) = Rect(0, 0, 2, 2) + Point(1, 2)
        assertEquals(l, 1)
        assertEquals(t, 2)
        assertEquals(r, 3)
        assertEquals(b, 4)
    }

    @Test fun offsetFloat() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 2.0f, 2.0f) + 2.0f
        assertEquals(l, 2.0f)
        assertEquals(t, 2.0f)
        assertEquals(r, 4.0f)
        assertEquals(b, 4.0f)
    }

    @Test fun offsetPointF() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 2.0f, 2.0f) + PointF(1.0f, 2.0f)
        assertEquals(l, 1.0f)
        assertEquals(t, 2.0f)
        assertEquals(r, 3.0f)
        assertEquals(b, 4.0f)
    }

    @Test fun negativeOffsetInt() {
        val (l, t, r, b) = Rect(0, 0, 2, 2) - 2
        assertEquals(l, -2)
        assertEquals(t, -2)
        assertEquals(r, 0)
        assertEquals(b, 0)
    }

    @Test fun negativeOffsetPoint() {
        val (l, t, r, b) = Rect(0, 0, 2, 2) - Point(1, 2)
        assertEquals(l, -1)
        assertEquals(t, -2)
        assertEquals(r, 1)
        assertEquals(b, 0)
    }

    @Test fun negativeOffsetFloat() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 2.0f, 2.0f) - 2.0f
        assertEquals(l, -2.0f)
        assertEquals(t, -2.0f)
        assertEquals(r, 0.0f)
        assertEquals(b, 0.0f)
    }

    @Test fun negativeOffsetPointF() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 2.0f, 2.0f) - PointF(1.0f, 2.0f)
        assertEquals(l, -1.0f)
        assertEquals(t, -2.0f)
        assertEquals(r, 1.0f)
        assertEquals(b, 0.0f)
    }

    @Test fun pointInside() {
        assertTrue(Point(1, 1) in Rect(0, 0, 2, 2))
        assertTrue(PointF(1.0f, 1.0f) in RectF(0.0f, 0.0f, 2.0f, 2.0f))
    }

    @Test fun toRect() {
        assertEquals(
            Rect(0, 1, 2, 3),
            RectF(0f, 1f, 2f, 3f).toRect())

        assertEquals(
            Rect(0, 1, 2, 3),
            RectF(0.1f, 1.1f, 1.9f, 2.9f).toRect())
    }

    @Test fun toRectF() {
        val rectF = Rect(0, 1, 2, 3).toRectF()
        assertEquals(0f, rectF.left, 0f)
        assertEquals(1f, rectF.top, 0f)
        assertEquals(2f, rectF.right, 0f)
        assertEquals(3f, rectF.bottom, 0f)
    }

    @Test fun toRegionInt() {
        assertEquals(Rect(1, 1, 5, 5), Rect(1, 1, 5, 5).toRegion().bounds)
    }

    @Test fun toRegionFloat() {
        assertEquals(Rect(1, 1, 5, 5), RectF(1.1f, 1.1f, 4.8f, 4.8f).toRegion().bounds)
    }

    @Test fun transformRectToRect() {
        val m = Matrix()
        m.setScale(2.0f, 2.0f)

        val r = RectF(2.0f, 2.0f, 5.0f, 7.0f).transform(m)
        assertEquals(4f, r.left, 0f)
        assertEquals(4f, r.top, 0f)
        assertEquals(10f, r.right, 0f)
        assertEquals(14f, r.bottom, 0f)
    }

    @Test fun transformRectNotPreserved() {
        val m = Matrix()
        m.setRotate(45.0f)

        val (l, t, r, b) = RectF(-1.0f, -1.0f, 1.0f, 1.0f).transform(m)
        assertEquals(-1.414f, l, 1e-3f)
        assertEquals(-1.414f, t, 1e-3f)
        assertEquals( 1.414f, r, 1e-3f)
        assertEquals( 1.414f, b, 1e-3f)
    }
}
