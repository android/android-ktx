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

package androidx.core.content

import android.content.ClipData
import android.content.ClipDescription
import androidx.testutils.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ClipDataTest {

    private val clip = ClipData("", arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), ClipData.Item(""))

    @Test fun get() {
        clip.addItem(ClipData.Item(""))
        clip.addItem(ClipData.Item(""))

        for (index in 0 until clip.itemCount) {
            assertSame(clip.getItemAt(index), clip[index])
        }
        assertThrows<IndexOutOfBoundsException> {
            clip[-1]
        }
        assertThrows<IndexOutOfBoundsException> {
            clip[clip.itemCount]
        }
    }

    @Test fun contains() {
        val item1 = ClipData.Item("")
        assertFalse(item1 in clip)
        clip.addItem(item1)
        assertTrue(item1 in clip)

        val item2 = ClipData.Item("")
        assertFalse(item2 in clip)
        clip.addItem(item2)
        assertTrue(item2 in clip)
    }

    @Test fun plusAssign() {
        val items = listOf(ClipData.Item(""), ClipData.Item(""))
        val expectedSize = clip.itemCount + items.size

        items.forEach {
            clip += it
            assertSame(clip.getItemAt(clip.itemCount - 1), it)
        }
        assertEquals(clip.itemCount, expectedSize)
    }

    @Test fun size() {
        assertEquals(clip.itemCount, clip.size)
        clip.addItem(ClipData.Item(""))
        assertEquals(clip.itemCount, clip.size)
        clip.addItem(ClipData.Item(""))
        assertEquals(clip.itemCount, clip.size)
    }

    @Test fun forEach() {
        clip.addItem(ClipData.Item(""))
        clip.addItem(ClipData.Item(""))

        var iterations = 0
        clip.forEach {
            assertSame(clip.getItemAt(iterations), it)
            iterations++
        }
        assertEquals(clip.itemCount, iterations)
    }

    @Test fun forEachIndexed() {
        clip.addItem(ClipData.Item(""))
        clip.addItem(ClipData.Item(""))

        var iterations = 0
        clip.forEachIndexed { index, item ->
            assertEquals(iterations, index)
            assertSame(clip.getItemAt(iterations), item)
            iterations++
        }
        assertEquals(clip.itemCount, iterations)
    }

    @Test fun iterator() {
        clip.addItem(ClipData.Item(""))
        clip.addItem(ClipData.Item(""))
        val iterator = clip.iterator()

        for (index in 0 until clip.itemCount) {
            assertTrue(iterator.hasNext())
            assertSame(clip.getItemAt(index), iterator.next())
        }
        assertFalse(iterator.hasNext())
        assertThrows<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test fun iteratorForEach() {
        clip.addItem(ClipData.Item(""))
        clip.addItem(ClipData.Item(""))

        var iterations = 0
        for (item in clip) {
            assertSame(clip.getItemAt(iterations), item)
            iterations++
        }
        assertEquals(clip.itemCount, iterations)
    }

    @Test fun items() {
        clip.addItem(ClipData.Item(""))
        clip.addItem(ClipData.Item(""))

        var iterations = 0
        clip.items.forEachIndexed { index, item ->
            assertEquals(iterations, index)
            assertSame(clip.getItemAt(iterations), item)
            iterations++
        }
        assertEquals(clip.itemCount, iterations)
    }

}