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

package androidx.core.util

import android.util.Base64.NO_WRAP
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.text.Charsets.UTF_16BE

class Base64Test {
    @Test fun encodeFromString() {
        val result = "a\uD83C\uDF69c".encodeBase64()
        assertEquals("YfCfjalj\n", result)
    }

    @Test fun encodeFromStringWithFlags() {
        val result = "a\uD83C\uDF69c".encodeBase64(flags = NO_WRAP)
        assertEquals("YfCfjalj", result)
    }

    @Test fun encodeFromStringWithCharset() {
        val result = "a\uD83C\uDF69c".encodeBase64(charset = UTF_16BE)
        assertEquals("AGHYPN9pAGM=\n", result)
    }

    @Test fun encodeFromByteArray() {
        val result = "a\uD83C\uDF69c".toByteArray().encodeBase64()
        assertEquals("YfCfjalj\n", result)
    }

    @Test fun encodeFromByteArrayFlags() {
        val result = "a\uD83C\uDF69c".toByteArray().encodeBase64(flags = NO_WRAP)
        assertEquals("YfCfjalj", result)
    }

    @Test fun encodeFromByteArrayOffsetAndLength() {
        val result = "a\uD83C\uDF69c".toByteArray().encodeBase64(offset = 1, length = 4)
        assertEquals("8J+NqQ==\n", result)
    }

    @Test fun decodeFromString() {
        val result = "YfCfjalj\n".decodeBase64()
        assertArrayEquals("a\uD83C\uDF69c".toByteArray(), result)
    }

    @Test fun decodeFromStringWithFlags() {
        val result = "YfCfjalj".decodeBase64(flags = NO_WRAP)
        assertArrayEquals("a\uD83C\uDF69c".toByteArray(), result)
    }

    @Test fun decodeFromByteArray() {
        val result = "YfCfjalj\n".toByteArray().decodeBase64()
        assertArrayEquals("a\uD83C\uDF69c".toByteArray(), result)
    }

    @Test fun decodeFromByteArrayWithFlags() {
        val result = "YfCfjalj".toByteArray().decodeBase64(flags = NO_WRAP)
        assertArrayEquals("a\uD83C\uDF69c".toByteArray(), result)
    }

    @Test fun decodeFromByteArrayOffsetAndLength() {
        val result = "xx8J+NqQ==xx".toByteArray().decodeBase64(offset = 2, length = 8)
        assertArrayEquals("\uD83C\uDF69".toByteArray(), result)
    }
}
