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

package androidx.core.database

import android.database.Cursor
import android.database.MatrixCursor
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CursorTest {
    @Test fun blobByName() {
        val cursor = scalarCursor(byteArrayOf(0x01))
        val blob = cursor.getBlob("data")
        assertArrayEquals(byteArrayOf(0x01), blob)
    }

    @Test fun doubleByName() {
        val cursor = scalarCursor(1.5)
        val double = cursor.getDouble("data")
        assertEquals(1.5, double, 0.0)
    }

    @Test fun floatByName() {
        val cursor = scalarCursor(1.5f)
        val float = cursor.getFloat("data")
        assertEquals(1.5f, float, 0f)
    }

    @Test fun intByName() {
        val cursor = scalarCursor(1)
        val int = cursor.getInt("data")
        assertEquals(1, int)
    }

    @Test fun longByName() {
        val cursor = scalarCursor(1L)
        val long = cursor.getLong("data")
        assertEquals(1L, long)
    }

    @Test fun shortByName() {
        val cursor = scalarCursor(1.toShort())
        val short = cursor.getShort("data")
        assertEquals(1.toShort(), short)
    }

    @Test fun stringByName() {
        val cursor = scalarCursor("hey")
        val string = cursor.getString("data")
        assertEquals("hey", string)
    }

    @Test fun blobOrNullByIndex() {
        val cursor = scalarCursor(null)
        val blob = cursor.getBlobOrNull(0)
        assertNull(blob)
    }

    @Test fun doubleOrNullByIndex() {
        val cursor = scalarCursor(null)
        val double = cursor.getDoubleOrNull(0)
        assertNull(double)
    }

    @Test fun floatOrNullByIndex() {
        val cursor = scalarCursor(null)
        val float = cursor.getFloatOrNull(0)
        assertNull(float)
    }

    @Test fun intOrNullByIndex() {
        val cursor = scalarCursor(null)
        val int = cursor.getIntOrNull(0)
        assertNull(int)
    }

    @Test fun longOrNullByIndex() {
        val cursor = scalarCursor(null)
        val long = cursor.getLongOrNull(0)
        assertNull(long)
    }

    @Test fun shortOrNullByIndex() {
        val cursor = scalarCursor(null)
        val short = cursor.getShortOrNull(0)
        assertNull(short)
    }

    @Test fun stringOrNullByIndex() {
        val cursor = scalarCursor(null)
        val string = cursor.getStringOrNull(0)
        assertNull(string)
    }

    @Test fun blobOrNullByName() {
        val cursor = scalarCursor(null)
        val blob = cursor.getBlobOrNull("data")
        assertNull(blob)
    }

    @Test fun doubleOrNullByName() {
        val cursor = scalarCursor(null)
        val double = cursor.getDoubleOrNull("data")
        assertNull(double)
    }

    @Test fun floatOrNullByName() {
        val cursor = scalarCursor(null)
        val float = cursor.getFloatOrNull("data")
        assertNull(float)
    }

    @Test fun intOrNullByName() {
        val cursor = scalarCursor(null)
        val int = cursor.getIntOrNull("data")
        assertNull(int)
    }

    @Test fun longOrNullByName() {
        val cursor = scalarCursor(null)
        val long = cursor.getLongOrNull("data")
        assertNull(long)
    }

    @Test fun shortOrNullByName() {
        val cursor = scalarCursor(null)
        val short = cursor.getShortOrNull("data")
        assertNull(short)
    }

    @Test fun stringOrNullByName() {
        val cursor = scalarCursor(null)
        val string = cursor.getStringOrNull("data")
        assertNull(string)
    }

    private fun scalarCursor(item: Any?): Cursor = MatrixCursor(arrayOf("data")).apply {
        addRow(arrayOf(item))
        moveToFirst() // Prepare for consumers to read.
    }
}
