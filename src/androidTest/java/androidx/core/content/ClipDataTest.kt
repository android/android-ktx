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
import android.content.Intent
import android.net.Uri
import android.support.test.InstrumentationRegistry
import androidx.testutils.assertThrows
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ClipDataTest {
    private val clip = ClipData.newPlainText("", "")
    private val context = InstrumentationRegistry.getContext()

    @Test fun get() {
        val item = ClipData.Item("")
        clip.addItem(item)

        // ClipData.newPlainText will instantiate
        // an Intent within its initial Item
        // so item.equals(clip[0]) will return false
        assertEquals(item.text, clip[0].text)
        assertEquals(item.uri, clip[0].uri)
        assertEquals(item.htmlText, clip[0].htmlText)
        val intent = clip[0].intent
        if (intent != null) {
            assertNotSame(intent, item.intent)
        }

        assertSame(item, clip[1])
    }

    @Test fun contains() {
        val item1 = ClipData.Item("")
        clip.addItem(item1)
        assertTrue(item1 in clip)

        val item2 = ClipData.Item("")
        clip.addItem(item2)
        assertTrue(item2 in clip)
    }

    @Test fun forEach() {
        val item1 = ClipData.Item("")
        clip.addItem(item1)
        val item2 = ClipData.Item("")
        clip.addItem(item2)

        val items = mutableListOf<ClipData.Item>()
        clip.forEach {
            items += it
        }
        assertEquals(3, items.size)
        assertThat(items).containsAllOf(item1, item2)
    }

    @Test fun forEachIndexed() {
        val item1 = ClipData.Item("")
        clip.addItem(item1)
        val item2 = ClipData.Item("")
        clip.addItem(item2)

        val items = mutableListOf<ClipData.Item>()
        clip.forEachIndexed { index, item ->
            assertEquals(index, items.size)
            items += item
        }
        assertEquals(3, items.size)
        assertThat(items).containsAllOf(item1, item2)
    }

    @Test fun iterator() {
        val item1 = ClipData.Item("")
        clip.addItem(item1)
        val item2 = ClipData.Item("")
        clip.addItem(item2)

        val iterator = clip.iterator()
        assertTrue(iterator.hasNext())
        iterator.next()
        assertSame(item1, iterator.next())
        assertTrue(iterator.hasNext())
        assertSame(item2, iterator.next())
        assertFalse(iterator.hasNext())
        assertThrows<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test fun items() {
        val itemsList = listOf(ClipData.Item(""), ClipData.Item(""))
        itemsList.forEach { clip.addItem(it) }

        clip.items.forEachIndexed { index, item ->
            if (index != 0) {
                assertSame(itemsList[index - 1], item)
            }
        }
    }

    @Test fun map() {
        clip.addItem(ClipData.Item("item1"))
        clip.addItem(ClipData.Item("item2"))

        val items = clip.map { item -> item.text }
        assertThat(items).containsExactly("", "item1", "item2")
    }

    @Test fun clipDataOfValid() {
        clip.addItem(ClipData.Item("item1"))
        clip.addItem(ClipData.Item("item2"))

        val l = listOf("", "item1", "item2")
        val c = clipDataOf(l, "strings")
        val items = c.map { item -> item.text }
        assertThat(items).containsExactlyElementsIn(l)

        val uris = listOf(Uri.parse("content://uri1"),
            Uri.parse("content://uri2"),
            Uri.parse("content://uri3"))
        val cU = clipDataOf(uris, "uris")
        val iU = cU.map { item -> item.uri }
        assertThat(iU).containsExactlyElementsIn(uris)

        val intents = listOf(Intent("com.androidx.action", Uri.parse("content://uri1")),
            Intent("com.androidx.action", Uri.parse("content://uri2")),
            Intent("com.androidx.action", Uri.parse("content://uri3")))
        val cI = clipDataOf(intents, "intents")
        val iI = cI.map { item -> item.intent }
        assertThat(iI).containsExactlyElementsIn(intents)
    }

    @Test fun clipDataOfInvalid() {
        assertThrows<IllegalArgumentException> {
            clipDataOf(listOf(1, 2, 3), "ints")
        }.hasMessageThat().isEqualTo("Illegal type: java.lang.Integer")
    }
}
