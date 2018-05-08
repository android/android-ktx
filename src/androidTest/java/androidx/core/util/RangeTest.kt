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

import android.support.test.filters.SdkSuppress
import android.util.Range
import org.junit.Assert.assertEquals
import org.junit.Test

@SdkSuppress(minSdkVersion = 21)
class RangeTest {
    @Test fun infixFactory() {
        val range: Range<String> = "a" rangeTo "c"
        assertEquals("a", range.lower)
        assertEquals("c", range.upper)
    }

    @Test fun extendValue() {
        val range = ("a" rangeTo "c") + "e"
        assertEquals("a", range.lower)
        assertEquals("e", range.upper)
    }

    @Test fun extendRange() {
        val range = ("a" rangeTo "c") + ("e" rangeTo "g")
        assertEquals("a", range.lower)
        assertEquals("g", range.upper)
    }

    @Test fun intersection() {
        val range = ("a" rangeTo "e") and ("c" rangeTo "g")
        assertEquals("c", range.lower)
        assertEquals("e", range.upper)
    }

    @Test fun kotlinToAndroid() {
        val range: Range<Int> = (1..3).toRange()
        assertEquals(1, range.lower)
        assertEquals(3, range.upper)
    }

    @Test fun androidToKotlin() {
        val range: ClosedRange<String> = Range<String>("a", "c").toClosedRange()
        assertEquals("a", range.start)
        assertEquals("c", range.endInclusive)
    }
}
