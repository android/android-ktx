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

import android.util.Pair
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class PairTest {
    @Test fun destructuringNonNull() {
        val pair = Pair("one", "two")
        val (first: String, second: String) = pair
        assertSame(pair.first, first)
        assertSame(pair.second, second)
    }

    @Test fun destructuringNullable() {
        val pair = Pair("one", "two")
        val (first: String?, second: String?) = pair
        assertSame(pair.first, first)
        assertSame(pair.second, second)
    }

    @Test fun toKotlin() {
        val android = Pair("one", "two")
        val kotlin = android.toKotlinPair()
        assertEquals(android.first to android.second, kotlin)
    }

    @Test fun toAndroid() {
        val kotlin = kotlin.Pair("one", "two")
        val android = kotlin.toAndroidPair()
        assertEquals(Pair(kotlin.first, kotlin.second), android)
    }
}
