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

package androidx.core.view

import android.support.test.InstrumentationRegistry
import android.view.Menu.NONE
import android.view.MenuItem
import android.widget.Toolbar
import androidx.testutils.assertThrows
import androidx.testutils.fail
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class MenuTest {
    private val menu = Toolbar(InstrumentationRegistry.getContext()).menu

    @Test fun get() {
        val item = menu.add("")
        assertSame(item, menu[0])
    }

    @Test fun contains() {
        val item1 = menu.add("")
        assertTrue(item1 in menu)

        val item2 = menu.add("")
        assertTrue(item2 in menu)
    }

    @Test fun minusAssign() {
        val item1 = menu.add(NONE, 1, NONE, "")
        val item2 = menu.add(NONE, 2, NONE, "")

        assertEquals(2, menu.size)

        menu -= item2
        assertEquals(1, menu.size)
        assertSame(item1, menu.getItem(0))

        menu -= item1
        assertEquals(0, menu.size)
    }

    @Test fun size() {
        assertEquals(0, menu.size)

        menu.add("")
        assertEquals(1, menu.size)

        menu.add(NONE, 123, NONE, "")
        assertEquals(2, menu.size)

        menu.removeItem(123)
        assertEquals(1, menu.size)
    }

    @Test fun isEmpty() {
        assertTrue(menu.isEmpty())
        menu.add("")
        assertFalse(menu.isEmpty())
    }

    @Test fun isNotEmpty() {
        assertFalse(menu.isNotEmpty())
        menu.add("")
        assertTrue(menu.isNotEmpty())
    }

    @Test fun forEach() {
        menu.forEach {
            fail("Empty menu should not invoke lambda")
        }

        val item1 = menu.add("")
        val item2 = menu.add("")

        val items = mutableListOf<MenuItem>()
        menu.forEach {
            items += it
        }
        assertThat(items).containsExactly(item1, item2)
    }

    @Test fun forEachIndexed() {
        menu.forEachIndexed { _, _ ->
            fail("Empty menu should not invoke lambda")
        }

        val item1 = menu.add("")
        val item2 = menu.add("")

        val items = mutableListOf<MenuItem>()
        menu.forEachIndexed { index, item ->
            assertEquals(index, items.size)
            items += item
        }
        assertThat(items).containsExactly(item1, item2)
    }

    @Test fun iterator() {
        val item1 = menu.add("")
        val item2 = menu.add("")

        val iterator = menu.iterator()
        assertTrue(iterator.hasNext())
        assertSame(item1, iterator.next())
        assertTrue(iterator.hasNext())
        assertSame(item2, iterator.next())
        assertFalse(iterator.hasNext())
        assertThrows<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test fun iteratorRemoving() {
        val item1 = menu.add("")
        val item2 = menu.add("")

        val iterator = menu.iterator()

        assertSame(item1, iterator.next())
        iterator.remove()
        assertFalse(item1 in menu)
        assertEquals(1, menu.size())

        assertSame(item2, iterator.next())
        iterator.remove()
        assertFalse(item2 in menu)
        assertEquals(0, menu.size())
    }

    @Test fun children() {
        val items = listOf(
            menu.add(NONE, 1, NONE, ""),
            menu.add(NONE, 2, NONE, ""),
            menu.add(NONE, 3, NONE, "")
        )

        menu.children.forEachIndexed { index, child ->
            assertSame(items[index], child)
        }
    }
}
