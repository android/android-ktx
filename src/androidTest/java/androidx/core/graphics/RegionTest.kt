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

import android.graphics.Point
import android.graphics.Rect
import android.graphics.Region
import androidx.testutils.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertTrue
import org.junit.Test

class RegionTest {
    @Test fun containsPoint() {
        assertFalse(Point(1, 1) in Region())
        assertTrue(Point(1, 1) in Region(0, 0, 2, 2))
    }

    @Test fun unionRect() {
        val r = Region(0, 0, 2, 2) + Rect(4, 4, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
        assertTrue(Point(5, 5) in r)
    }

    @Test fun unionRegion() {
        val r = Region(0, 0, 2, 2) + Region(4, 4, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
        assertTrue(Point(5, 5) in r)
    }

    @Test fun unionAsAndRect() {
        val r = Region(0, 0, 2, 2) and Rect(4, 4, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
        assertTrue(Point(5, 5) in r)
    }

    @Test fun unionAsAndRegion() {
        val r = Region(0, 0, 2, 2) and Region(4, 4, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
        assertTrue(Point(5, 5) in r)
    }

    @Test fun differenceRect() {
        val r = Region(0, 0, 4, 4) - Rect(2, 2, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
        assertFalse(Point(5, 5) in r)
    }

    @Test fun differenceRegion() {
        val r = Region(0, 0, 4, 4) - Region(2, 2, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
        assertFalse(Point(5, 5) in r)
    }

    @Test fun unaryMinus() {
        val r = Rect(0, 0, 10, 10) - Rect(4, 4, 6, 6)
        assertTrue(Point(1, 1) in r)
        assertFalse(Point(5, 5) in r)

        val i = -r
        assertFalse(Point(1, 1) in i)
        assertTrue(Point(5, 5) in i)
    }

    @Test fun not() {
        val r = Rect(0, 0, 10, 10) - Rect(4, 4, 6, 6)
        assertTrue(Point(1, 1) in r)
        assertFalse(Point(5, 5) in r)

        val i = !r
        assertFalse(Point(1, 1) in i)
        assertTrue(Point(5, 5) in i)
    }

    @Test fun orRect() {
        val r = Region(0, 0, 4, 4) or Rect(2, 2, 6, 6)
        assertFalse(Point(1, 1) in r)
        assertTrue(Point(3, 3) in r)
    }

    @Test fun orRegion() {
        val r = Region(0, 0, 4, 4) or Region(2, 2, 6, 6)
        assertFalse(Point(1, 1) in r)
        assertTrue(Point(3, 3) in r)
    }

    @Test fun xorRect() {
        val r = Region(0, 0, 4, 4) xor Rect(2, 2, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
    }

    @Test fun xorRegion() {
        val r = Region(0, 0, 4, 4) xor Region(2, 2, 6, 6)
        assertFalse(Point(3, 3) in r)
        assertTrue(Point(1, 1) in r)
    }

    @Test fun iteratorForLoop() {
        val region = Region(0, 0, 4, 4) -
                Rect(2, 2, 6, 6)
        var count = 0
        var r = Rect()
        for (rect in region) {
            count++
            assertNotSame(r, rect)
            r = rect
        }
        assertEquals(2, count)
    }

    @Test fun iteratorOutOfBounds() {
        val region = Region(0, 0, 4, 4) -
                Rect(2, 2, 6, 6)
        val it = region.iterator()
        it.next()
        it.next()
        assertThrows<IndexOutOfBoundsException> {
            it.next()
        }
    }

    @Test fun iteratorForEach() {
        val region = Region(0, 0, 4, 4) -
                Rect(2, 2, 6, 6)
        var count = 0
        var r = Rect()
        region.forEach {
            count++
            assertNotSame(r, it)
            r = it
        }
        assertEquals(2, count)
    }
}
