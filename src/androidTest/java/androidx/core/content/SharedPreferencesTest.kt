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

package androidx.core.content

import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test

class SharedPreferencesTest {
    private val context = InstrumentationRegistry.getContext()

    @Test fun editApply() {
        val preferences = context.getSharedPreferences("prefs", 0)

        preferences.edit {
            putString("test_key1", "test_value")
            putInt("test_key2", 100)
        }

        assertEquals("test_value", preferences.getString("test_key1", null))
        assertEquals(100, preferences.getInt("test_key2", 0))
    }

    @Test fun editCommit() {
        val preferences = context.getSharedPreferences("prefs", 0)
        preferences.edit(commit = true) {
            putString("test_key1", "test_value")
            putInt("test_key2", 100)
        }

        assertEquals("test_value", preferences.getString("test_key1", null))
        assertEquals(100, preferences.getInt("test_key2", 0))
    }

    @Test fun putApply() {
        val preferences = context.getSharedPreferences("prefs", 0)

        preferences.put("test_key1", "test_value")
        preferences.put("test_key2", 100)

        assertEquals("test_value", preferences.getString("test_key1", null))
        assertEquals(100, preferences.getInt("test_key2", 0))
    }

    @Test fun putCommit() {
        val preferences = context.getSharedPreferences("prefs", 0)

        preferences.put("test_key1", "test_value", commit = true)

        assertEquals("test_value", preferences.getString("test_key1", null))
    }

    @Test fun getValue() {
        val preferences = context.getSharedPreferences("prefs", 0)
        val editor = preferences.edit()

        editor.putString("test_key1", "test_value")
        editor.putInt("test_key2", 100)
        editor.putBoolean("test_key3", true)
        editor.apply()

        assertEquals("test_value", preferences.get("test_key1", ""))
        assertEquals(true, preferences.get("test_key3", false))
        assertEquals(100, preferences.get("test_key2", 0))
    }
}
