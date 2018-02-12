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

package androidx.general

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class CommonsTest {

    @Test
    fun testFormatTimestamp() {
        val timestamp = System.currentTimeMillis()
        assertEquals(
            timestamp.formatTimestamp("dd/MM/yyy", Locale.US),
            SimpleDateFormat("dd/MM/yyyy", Locale.US).format(timestamp)
        )
    }

    @Test
    fun testToDate() {
        val timestamp = System.currentTimeMillis()
        val timestampString = timestamp.formatTimestamp("yyyy-MM-dd HH:mm:ss.SSS", Locale.US)
        assertEquals(
            timestampString.toDate("yyyy-MM-dd HH:mm:ss.SSS").time,
            timestamp
        )
    }

    @Test
    fun testIntToBoolean() {
        val tru = 10
        val fals = -10
        assertEquals(tru.toBoolean(), true)
        assertEquals(fals.toBoolean(), false)
    }

    @Test
    fun testBooleanInvokeTrue() {
        var success = false
        true {
            success = this
        }
        assertEquals(success, true)
    }

    @Test
    fun testNullCheck() {
        val isNull: String? = null
        val isNotNull: String? = "success"
        assertEquals(isNull({ false }, { true }), true)
        assertEquals(isNotNull({ false }, { true }), false)
    }

    @Test
    fun testBooleanCheck() {
        val isFalse: Boolean? = null
        val isTrue = true
        assertEquals(isFalse({ true }, { false }), false)
        assertEquals(isTrue({ true }, { false }), true)
    }
}
