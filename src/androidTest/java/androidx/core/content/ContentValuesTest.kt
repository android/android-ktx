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

import androidx.testutils.assertThrows
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class ContentValuesTest {
    @Test fun valuesOfValid() {
        val values = contentValuesOf(
            "null" to null,
            "string" to "string",
            "byte" to 1.toByte(),
            "short" to 1.toShort(),
            "int" to 1,
            "long" to 1L,
            "float" to 1f,
            "double" to 1.0,
            "boolean" to true,
            "byteArray" to byteArrayOf()
        )
        assertEquals(10, values.size())
        assertNull(values.get("null"))
        assertEquals("string", values.get("string"))
        assertEquals(1.toByte(), values.get("byte"))
        assertEquals(1.toShort(), values.get("short"))
        assertEquals(1, values.get("int"))
        assertEquals(1L, values.get("long"))
        assertEquals(1f, values.get("float"))
        assertEquals(1.0, values.get("double"))
        assertEquals(true, values.get("boolean"))
        assertArrayEquals(byteArrayOf(), values.get("byteArray") as ByteArray)
    }

    @Test fun valuesOfInvalid() {
        assertThrows<IllegalArgumentException> {
            contentValuesOf("nope" to AtomicInteger(1))
        }.hasMessageThat().isEqualTo(
            "Illegal value type java.util.concurrent.atomic.AtomicInteger for key \"nope\"")
    }
}
