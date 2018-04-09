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
import android.util.SparseLongArray
import androidx.testutils.fail
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

@SdkSuppress(minSdkVersion = 18)
class SparseLongArrayTest {
    @Test fun sizeProperty() {
        val array = SparseLongArray()
        assertEquals(0, array.size)
        array.put(1, 11L)
        assertEquals(1, array.size)
    }

    @Test fun containsOperator() {
        val array = SparseLongArray()
        assertFalse(1 in array)
        array.put(1, 11L)
        assertTrue(1 in array)
    }

    @Test fun containsOperatorWithValue() {
        val array = SparseLongArray()

        array.put(1, 11L)
        assertFalse(2 in array)

        array.put(2, 22L)
        assertTrue(2 in array)
    }

    @Test fun setOperator() {
        val array = SparseLongArray()
        array[1] = 11L
        assertEquals(11L, array.get(1))
    }

    @Test fun plusOperator() {
        val first = SparseLongArray().apply { put(1, 11L) }
        val second = SparseLongArray().apply { put(2, 22L) }
        val combined = first + second
        assertEquals(2, combined.size())
        assertEquals(1, combined.keyAt(0))
        assertEquals(11L, combined.valueAt(0))
        assertEquals(2, combined.keyAt(1))
        assertEquals(22L, combined.valueAt(1))
    }

    @Test fun containsKey() {
        val array = SparseLongArray()
        assertFalse(array.containsKey(1))
        array.put(1, 11L)
        assertTrue(array.containsKey(1))
    }

    @Test fun containsKeyWithValue() {
        val array = SparseLongArray()

        array.put(1, 11L)
        assertFalse(array.containsKey(2))

        array.put(2, 22L)
        assertTrue(array.containsKey(2))
    }

    @Test fun containsValue() {
        val array = SparseLongArray()
        assertFalse(array.containsValue(11L))
        array.put(1, 11L)
        assertTrue(array.containsValue(11L))
    }

    @Test fun getOrDefault() {
        val array = SparseLongArray()
        assertEquals(22L, array.getOrDefault(1, 22L))
        array.put(1, 11L)
        assertEquals(11L, array.getOrDefault(1, 22L))
    }

    @Test fun getOrElse() {
        val array = SparseLongArray()
        assertEquals(22L, array.getOrElse(1) { 22L })
        array.put(1, 11L)
        assertEquals(11L, array.getOrElse(1) { fail() })
    }

    @Test fun isEmpty() {
        val array = SparseLongArray()
        assertTrue(array.isEmpty())
        array.put(1, 11L)
        assertFalse(array.isEmpty())
    }

    @Test fun isNotEmpty() {
        val array = SparseLongArray()
        assertFalse(array.isNotEmpty())
        array.put(1, 11L)
        assertTrue(array.isNotEmpty())
    }

    @Test fun removeValue() {
        val array = SparseLongArray()
        array.put(1, 11L)
        assertFalse(array.remove(0, 11L))
        assertEquals(1, array.size())
        assertFalse(array.remove(1, 22L))
        assertEquals(1, array.size())
        assertTrue(array.remove(1, 11L))
        assertEquals(0, array.size())
    }

    @Test fun putAll() {
        val dest = SparseLongArray()
        val source = SparseLongArray()
        source.put(1, 11L)

        assertEquals(0, dest.size())
        dest.putAll(source)
        assertEquals(1, dest.size())
    }

    @Test fun forEach() {
        val array = SparseLongArray()
        array.forEach { _, _ -> fail() }

        array.put(1, 11L)
        array.put(2, 22L)
        array.put(6, 66L)

        val keys = mutableListOf<Int>()
        val values = mutableListOf<Long>()
        array.forEach { key, value ->
            keys.add(key)
            values.add(value)
        }
        assertThat(keys).containsExactly(1, 2, 6)
        assertThat(values).containsExactly(11L, 22L, 66L)
    }

    @Test fun keyIterator() {
        val array = SparseLongArray()
        assertFalse(array.keyIterator().hasNext())

        array.put(1, 11L)
        array.put(2, 22L)
        array.put(6, 66L)

        val iterator = array.keyIterator()
        assertTrue(iterator.hasNext())
        assertEquals(1, iterator.nextInt())
        assertTrue(iterator.hasNext())
        assertEquals(2, iterator.nextInt())
        assertTrue(iterator.hasNext())
        assertEquals(6, iterator.nextInt())
        assertFalse(iterator.hasNext())
    }

    @Test fun valueIterator() {
        val array = SparseLongArray()
        assertFalse(array.valueIterator().hasNext())

        array.put(1, 11L)
        array.put(2, 22L)
        array.put(6, 66L)

        val iterator = array.valueIterator()
        assertTrue(iterator.hasNext())
        assertEquals(11, iterator.nextLong())
        assertTrue(iterator.hasNext())
        assertEquals(22, iterator.nextLong())
        assertTrue(iterator.hasNext())
        assertEquals(66, iterator.nextLong())
        assertFalse(iterator.hasNext())
    }
}
