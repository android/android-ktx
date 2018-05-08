/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.core.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LruCacheTest {
    private data class TestData(val x: String = "bla")

    @Test fun size() {
        val cache = lruCache<String, TestData>(200, { k, (x) -> k.length * x.length })
        cache.put("long", TestData())
        assertEquals(cache.size(), 12)
    }

    @Test fun create() {
        val cache = lruCache<String, TestData>(200, create = { key -> TestData("$key foo") })
        assertEquals(cache.get("kung"), TestData("kung foo"))
    }

    @Test fun onEntryRemoved() {
        var wasCalled = false

        val cache = lruCache<String, TestData>(200, onEntryRemoved = { _, _, _, _ ->
            wasCalled = true
        })
        val initial = TestData()
        cache.put("a", initial)
        cache.remove("a")
        assertTrue(wasCalled)
    }
}
