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

package androidx.util

import android.util.Base64
import org.junit.Assert.assertEquals
import org.junit.Test

class Base64Test {
    @Test fun toBase64String() {
        val expected = "SGksIEphcmVk"
        val actual = "Hi, Jared".encodeBase64(flags = Base64.NO_WRAP)
        assertEquals(expected, actual)
    }

    @Test fun toBase64ByteArray() {
        val expected = "AQIDBA=="
        val actual = byteArrayOf(1, 2, 3, 4).encodeBase64(flags = Base64.NO_WRAP)
            .toString(Charsets.UTF_8)
        assertEquals(expected, actual)
    }

    @Test fun fromBase64String() {
        val expected = "Hi, Jared"
        val actual = String.decodeBase64("SGksIEphcmVk", flags = Base64.NO_WRAP)
        assertEquals(expected, actual)
    }

    @Test fun fromBase64ByteArray() {
        val expected = byteArrayOf(1, 2, 3, 4).toString(Charsets.UTF_8)
        val actual = "AQIDBA==".toByteArray().decodeBase64(flags = Base64.NO_WRAP)
            .toString(Charsets.UTF_8)
        assertEquals(expected, actual)
    }
}
