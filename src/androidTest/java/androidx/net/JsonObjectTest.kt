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

package androidx.net

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class JsonObjectTest {
    @Test fun putJsonFromString() {
        val key = "NAME"
        val value = "Person"
        val json = JSONObject().apply { checkNotBlank(key, value) }
        assertEquals(value, json.get(key))
    }

    @Test fun putJsonFromInt() {
        val key = "NUMBER"
        val value = 1
        val json = JSONObject().apply { checkNotBlank(key, value) }
        assertEquals(value, json.get(key))
    }

    @Test fun putJsonFromLong() {
        val key = "ID"
        val value = 1L
        val json = JSONObject().apply { checkNotBlank(key, value) }
        assertEquals(value, json.get(key))
    }

    @Test fun putJsonFromDouble() {
        val key = "RADIUS"
        val value = 1.1
        val json = JSONObject().apply { checkNotBlank(key, value) }
        assertEquals(value, json.get(key))
    }

    @Test fun putJsonFromFloat() {
        val key = "SIZE"
        val value = 1f
        val json = JSONObject().apply { checkNotBlank(key, value) }
        assertEquals(value, json.get(key))
    }
}
