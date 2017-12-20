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

import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import org.junit.Assert.assertEquals
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

    @Test fun unionFloat() {
        val (l, t, r, b) = RectF(0.0f, 0.0f, 4.0f, 4.0f) + RectF(-1.0f, -1.0f, 6.0f, 6.0f)
        assertEquals(-1.0f, l)
        assertEquals(-1.0f, t)
        assertEquals(6.0f, r)
        assertEquals(6.0f, b)
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
        assertEquals(
                RectF(0f, 1f, 2f, 3f),
                Rect(0, 1, 2, 3).toRectF())
    }
}
