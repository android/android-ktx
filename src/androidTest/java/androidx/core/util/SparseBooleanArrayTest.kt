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

import android.util.SparseBooleanArray
import androidx.testutils.fail
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SparseBooleanArrayTest {
    @Test fun sizeProperty() {
        val array = SparseBooleanArray()
        assertEquals(0, array.size)
        array.put(1, true)
        assertEquals(1, array.size)
    }

    @Test fun containsOperator() {
        val array = SparseBooleanArray()
        assertFalse(1 in array)
        array.put(1, true)
        assertTrue(1 in array)
    }

    @Test fun containsOperatorWithValue() {
        val array = SparseBooleanArray()

        array.put(1, true)
        assertFalse(2 in array)

        array.put(2, true)
        assertTrue(2 in array)
    }

    @Test fun setOperator() {
        val array = SparseBooleanArray()
        array[1] = true
        assertTrue(array.get(1))
    }

    @Test fun plusOperator() {
        val first = SparseBooleanArray().apply { put(1, true) }
        val second = SparseBooleanArray().apply { put(2, false) }
        val combined = first + second
        assertEquals(2, combined.size())
        assertEquals(1, combined.keyAt(0))
        assertTrue(combined.valueAt(0))
        assertEquals(2, combined.keyAt(1))
        assertFalse(combined.valueAt(1))
    }

    @Test fun containsKey() {
        val array = SparseBooleanArray()
        assertFalse(array.containsKey(1))
        array.put(1, true)
        assertTrue(array.containsKey(1))
    }

    @Test fun containsKeyWithValue() {
        val array = SparseBooleanArray()

        array.put(1, true)
        assertFalse(array.containsKey(2))

        array.put(2, true)
        assertTrue(array.containsKey(2))
    }

    @Test fun containsValue() {
        val array = SparseBooleanArray()
        assertFalse(array.containsValue(true))
        array.put(1, true)
        assertTrue(array.containsValue(true))
    }

    @Test fun getOrDefault() {
        val array = SparseBooleanArray()
        assertFalse(array.getOrDefault(1, false))
        array.put(1, true)
        assertTrue(array.getOrDefault(1, false))
    }

    @Test fun getOrElse() {
        val array = SparseBooleanArray()
        assertFalse(array.getOrElse(1) { false })
        array.put(1, true)
        assertTrue(array.getOrElse(1) { fail() })
    }

    @Test fun isEmpty() {
        val array = SparseBooleanArray()
        assertTrue(array.isEmpty())
        array.put(1, true)
        assertFalse(array.isEmpty())
    }

    @Test fun isNotEmpty() {
        val array = SparseBooleanArray()
        assertFalse(array.isNotEmpty())
        array.put(1, true)
        assertTrue(array.isNotEmpty())
    }

    @Test fun removeValue() {
        val array = SparseBooleanArray()
        array.put(1, true)
        assertFalse(array.remove(0, true))
        assertEquals(1, array.size())
        assertFalse(array.remove(1, false))
        assertEquals(1, array.size())
        assertTrue(array.remove(1, true))
        assertEquals(0, array.size())
    }

    @Test fun putAll() {
        val dest = SparseBooleanArray()
        val source = SparseBooleanArray()
        source.put(1, true)

        assertEquals(0, dest.size())
        dest.putAll(source)
        assertEquals(1, dest.size())
    }

    @Test fun forEach() {
        val array = SparseBooleanArray()
        array.forEach { _, _ -> fail() }

        array.put(1, true)
        array.put(2, false)
        array.put(6, true)

        val keys = mutableListOf<Int>()
        val values = mutableListOf<Boolean>()
        array.forEach { key, value ->
            keys.add(key)
            values.add(value)
        }
        assertThat(keys).containsExactly(1, 2, 6)
        assertThat(values).containsExactly(true, false, true)
    }

    @Test fun keyIterator() {
        val array = SparseBooleanArray()
        assertFalse(array.keyIterator().hasNext())

        array.put(1, true)
        array.put(2, false)
        array.put(6, true)

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
        val array = SparseBooleanArray()
        assertFalse(array.valueIterator().hasNext())

        array.put(1, true)
        array.put(2, false)
        array.put(6, true)

        val iterator = array.valueIterator()
        assertTrue(iterator.hasNext())
        assertTrue(iterator.nextBoolean())
        assertTrue(iterator.hasNext())
        assertFalse(iterator.nextBoolean())
        assertTrue(iterator.hasNext())
        assertTrue(iterator.nextBoolean())
        assertFalse(iterator.hasNext())
    }
}
