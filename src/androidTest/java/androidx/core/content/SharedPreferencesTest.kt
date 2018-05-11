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

    @Test fun getOperator() {
        val preferences = context.getSharedPreferences("set_prefs", 0)

        assertEquals(true, preferences[Boolean::class.java, "test_key1", true])
        assertEquals(0.1f, preferences[Float::class.java, "test_key2", 0.1f])
        assertEquals(100, preferences[Int::class.java, "test_key3", 100])
        assertEquals(123456789, preferences[Long::class.java, "test_key4", 123456789])
        assertEquals("test_value", preferences[String::class.java, "test_key5", "test_value"])
    }

    @Test fun setOperator() {
        val preferences = context.getSharedPreferences("set_prefs", 0)

        preferences[Boolean::class.java, "test_key1"] = true
        preferences[Float::class.java, "test_key2"] = 0.1f
        preferences[Int::class.java, "test_key3"] = 100
        preferences[Long::class.java, "test_key4"] = 123456789
        preferences[String::class.java, "test_key5"] = "test_value"

        assertEquals(true, preferences.getBoolean("test_key1", false))
        assertEquals(0.1f, preferences.getFloat("test_key2", 0.2f))
        assertEquals(100, preferences.getInt("test_key3", 101))
        assertEquals(123456789, preferences.getLong("test_key4", 123456788))
        assertEquals("test_value", preferences.getString("test_key5", "default"))
    }

    @Test fun getInlinedOperator() {
        val preferences = context.getSharedPreferences("set_prefs", 0)

        assertEquals(true, preferences["test_key1", true])
        assertEquals(0.1f, preferences["test_key2", 0.1f])
        assertEquals(100, preferences["test_key3", 100])
        assertEquals(123456789, preferences["test_key4", 123456789])
        assertEquals("test_value", preferences["test_key5", "test_value"])
    }

    @Test fun setInlinedOperator() {
        val preferences = context.getSharedPreferences("set_prefs", 0)

        preferences["test_key1"] = true
        preferences["test_key2"] = 0.1f
        preferences["test_key3"] = 100
        preferences["test_key4"] = 123456789
        preferences["test_key5"] = "test_value"

        assertEquals(true, preferences.getBoolean("test_key1", false))
        assertEquals(0.1f, preferences.getFloat("test_key2", 0.2f))
        assertEquals(100, preferences.getInt("test_key3", 101))
        assertEquals(123456789, preferences.getLong("test_key4", 123456788))
        assertEquals("test_value", preferences.getString("test_key5", "default"))
    }
}
