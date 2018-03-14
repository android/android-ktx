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

import android.util.SparseIntArray
import androidx.testutils.fail
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SparseIntArrayTest {
    @Test fun sizeProperty() {
        val array = SparseIntArray()
        assertEquals(0, array.size)
        array.put(1, 11)
        assertEquals(1, array.size)
    }

    @Test fun containsOperator() {
        val array = SparseIntArray()
        assertFalse(1 in array)
        array.put(1, 11)
        assertTrue(1 in array)
    }

    @Test fun containsOperatorWithValue() {
        val array = SparseIntArray()

        array.put(1, 11)
        assertFalse(2 in array)

        array.put(2, 22)
        assertTrue(2 in array)
    }

    @Test fun setOperator() {
        val array = SparseIntArray()
        array[1] = 11
        assertEquals(11, array.get(1))
    }

    @Test fun plusOperator() {
        val first = SparseIntArray().apply { put(1, 11) }
        val second = SparseIntArray().apply { put(2, 22) }
        val combined = first + second
        assertEquals(2, combined.size())
        assertEquals(1, combined.keyAt(0))
        assertEquals(11, combined.valueAt(0))
        assertEquals(2, combined.keyAt(1))
        assertEquals(22, combined.valueAt(1))
    }

    @Test fun containsKey() {
        val array = SparseIntArray()
        assertFalse(array.containsKey(1))
        array.put(1, 11)
        assertTrue(array.containsKey(1))
    }

    @Test fun containsKeyWithValue() {
        val array = SparseIntArray()

        array.put(1, 11)
        assertFalse(array.containsKey(2))

        array.put(2, 22)
        assertTrue(array.containsKey(2))
    }

    @Test fun containsValue() {
        val array = SparseIntArray()
        assertFalse(array.containsValue(11))
        array.put(1, 11)
        assertTrue(array.containsValue(11))
    }

    @Test fun getOrDefault() {
        val array = SparseIntArray()
        assertEquals(22, array.getOrDefault(1, 22))
        array.put(1, 11)
        assertEquals(11, array.getOrDefault(1, 22))
    }

    @Test fun getOrElse() {
        val array = SparseIntArray()
        assertEquals(22, array.getOrElse(1) { 22 })
        array.put(1, 11)
        assertEquals(11, array.getOrElse(1) { fail() })
    }

    @Test fun isEmpty() {
        val array = SparseIntArray()
        assertTrue(array.isEmpty())
        array.put(1, 11)
        assertFalse(array.isEmpty())
    }

    @Test fun isNotEmpty() {
        val array = SparseIntArray()
        assertFalse(array.isNotEmpty())
        array.put(1, 11)
        assertTrue(array.isNotEmpty())
    }

    @Test fun removeValue() {
        val array = SparseIntArray()
        array.put(1, 11)
        assertFalse(array.remove(0, 11))
        assertEquals(1, array.size())
        assertFalse(array.remove(1, 22))
        assertEquals(1, array.size())
        assertTrue(array.remove(1, 11))
        assertEquals(0, array.size())
    }

    @Test fun putAll() {
        val dest = SparseIntArray()
        val source = SparseIntArray()
        source.put(1, 11)

        assertEquals(0, dest.size())
        dest.putAll(source)
        assertEquals(1, dest.size())
    }

    @Test fun forEach() {
        val array = SparseIntArray()
        array.forEach { _, _ -> fail() }

        array.put(1, 11)
        array.put(2, 22)
        array.put(6, 66)

        val keys = mutableListOf<Int>()
        val values = mutableListOf<Int>()
        array.forEach { key, value ->
            keys.add(key)
            values.add(value)
        }
        assertThat(keys).containsExactly(1, 2, 6)
        assertThat(values).containsExactly(11, 22, 66)
    }

    @Test fun keyIterator() {
        val array = SparseIntArray()
        assertFalse(array.keyIterator().hasNext())

        array.put(1, 11)
        array.put(2, 22)
        array.put(6, 66)

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
        val array = SparseIntArray()
        assertFalse(array.valueIterator().hasNext())

        array.put(1, 11)
        array.put(2, 22)
        array.put(6, 66)

        val iterator = array.valueIterator()
        assertTrue(iterator.hasNext())
        assertEquals(11, iterator.nextInt())
        assertTrue(iterator.hasNext())
        assertEquals(22, iterator.nextInt())
        assertTrue(iterator.hasNext())
        assertEquals(66, iterator.nextInt())
        assertFalse(iterator.hasNext())
    }
}
