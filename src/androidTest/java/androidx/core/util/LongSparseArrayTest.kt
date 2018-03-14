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

package androidx.core.util

import android.support.test.filters.SdkSuppress
import android.util.LongSparseArray
import androidx.testutils.fail
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

@SdkSuppress(minSdkVersion = 16)
class LongSparseArrayTest {
    @Test fun sizeProperty() {
        val array = LongSparseArray<String>()
        assertEquals(0, array.size)
        array.put(1L, "one")
        assertEquals(1, array.size)
    }

    @Test fun containsOperator() {
        val array = LongSparseArray<String>()
        assertFalse(1L in array)
        array.put(1L, "one")
        assertTrue(1L in array)
    }

    @Test fun containsOperatorWithValue() {
        val array = LongSparseArray<String>()

        array.put(1L, "one")
        assertFalse(2L in array)

        array.put(2L, "two")
        assertTrue(2L in array)
    }

    @Test fun setOperator() {
        val array = LongSparseArray<String>()
        array[1L] = "one"
        assertEquals("one", array.get(1L))
    }

    @Test fun plusOperator() {
        val first = LongSparseArray<String>().apply { put(1L, "one") }
        val second = LongSparseArray<String>().apply { put(2L, "two") }
        val combined = first + second
        assertEquals(2, combined.size())
        assertEquals(1L, combined.keyAt(0))
        assertEquals("one", combined.valueAt(0))
        assertEquals(2L, combined.keyAt(1))
        assertEquals("two", combined.valueAt(1))
    }

    @Test fun containsKey() {
        val array = LongSparseArray<String>()
        assertFalse(array.containsKey(1L))
        array.put(1L, "one")
        assertTrue(array.containsKey(1L))
    }

    @Test fun containsKeyWithValue() {
        val array = LongSparseArray<String>()

        array.put(1L, "one")
        assertFalse(array.containsKey(2L))

        array.put(2L, "one")
        assertTrue(array.containsKey(2L))
    }

    @Test fun containsValue() {
        val array = LongSparseArray<String>()
        assertFalse(array.containsValue("one"))
        array.put(1L, "one")
        assertTrue(array.containsValue("one"))
    }

    @Test fun getOrDefault() {
        val array = LongSparseArray<Any>()
        val default = Any()
        assertSame(default, array.getOrDefault(1L, default))
        array.put(1L, "one")
        assertEquals("one", array.getOrDefault(1L, default))
    }

    @Test fun getOrElse() {
        val array = LongSparseArray<Any>()
        val default = Any()
        assertSame(default, array.getOrElse(1L) { default })
        array.put(1L, "one")
        assertEquals("one", array.getOrElse(1L) { fail() })
    }

    @Test fun isEmpty() {
        val array = LongSparseArray<String>()
        assertTrue(array.isEmpty())
        array.put(1L, "one")
        assertFalse(array.isEmpty())
    }

    @Test fun isNotEmpty() {
        val array = LongSparseArray<String>()
        assertFalse(array.isNotEmpty())
        array.put(1L, "one")
        assertTrue(array.isNotEmpty())
    }

    @Test fun removeValue() {
        val array = LongSparseArray<String>()
        array.put(1L, "one")
        assertFalse(array.remove(0L, "one"))
        assertEquals(1, array.size())
        assertFalse(array.remove(1L, "two"))
        assertEquals(1, array.size())
        assertTrue(array.remove(1L, "one"))
        assertEquals(0, array.size())
    }

    @Test fun putAll() {
        val dest = LongSparseArray<String>()
        val source = LongSparseArray<String>()
        source.put(1L, "one")

        assertEquals(0, dest.size())
        dest.putAll(source)
        assertEquals(1, dest.size())
    }

    @Test fun forEach() {
        val array = LongSparseArray<String>()
        array.forEach { _, _ -> fail() }

        array.put(1L, "one")
        array.put(2L, "two")
        array.put(6L, "six")

        val keys = mutableListOf<Long>()
        val values = mutableListOf<String>()
        array.forEach { key, value ->
            keys.add(key)
            values.add(value)
        }
        assertThat(keys).containsExactly(1L, 2L, 6L)
        assertThat(values).containsExactly("one", "two", "six")
    }

    @Test fun keyIterator() {
        val array = LongSparseArray<String>()
        assertFalse(array.keyIterator().hasNext())

        array.put(1L, "one")
        array.put(2L, "two")
        array.put(6L, "six")

        val iterator = array.keyIterator()
        assertTrue(iterator.hasNext())
        assertEquals(1L, iterator.nextLong())
        assertTrue(iterator.hasNext())
        assertEquals(2L, iterator.nextLong())
        assertTrue(iterator.hasNext())
        assertEquals(6L, iterator.nextLong())
        assertFalse(iterator.hasNext())
    }

    @Test fun valueIterator() {
        val array = LongSparseArray<String>()
        assertFalse(array.valueIterator().hasNext())

        array.put(1L, "one")
        array.put(2L, "two")
        array.put(6L, "six")

        val iterator = array.valueIterator()
        assertTrue(iterator.hasNext())
        assertEquals("one", iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals("two", iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals("six", iterator.next())
        assertFalse(iterator.hasNext())
    }
}
