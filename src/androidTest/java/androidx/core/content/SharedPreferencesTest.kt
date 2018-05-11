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

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import androidx.testutils.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SharedPreferencesTest {

    val CUSTOM_PREFERENCE_NAME = "CUSTOM_PREFERENCE_NAME"
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

    val DEFAULT_PREFERENCE_NULLABLE_STRING_KEY = "DEFAULT_PREFERENCE_NULLABLE_STRING_KEY"
    val DEFAULT_PREFERENCE_NULLABLE_STRING_DEFAULT_VALUE: String? = null
    val DEFAULT_PREFERENCE_NULLABLE_STRING_TEST_VALUE = "test"

    var nullableStringDefaultPreference by context.bindSharedPreference(
        DEFAULT_PREFERENCE_NULLABLE_STRING_KEY,
        DEFAULT_PREFERENCE_NULLABLE_STRING_DEFAULT_VALUE)

    @Test fun bindDefaultSharedPreferenceNullableString() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertNull(preferences.getString(
            DEFAULT_PREFERENCE_NULLABLE_STRING_KEY,
            DEFAULT_PREFERENCE_NULLABLE_STRING_DEFAULT_VALUE))

        assertNull(nullableStringDefaultPreference)

        nullableStringDefaultPreference = DEFAULT_PREFERENCE_NULLABLE_STRING_TEST_VALUE

        assertEquals(DEFAULT_PREFERENCE_NULLABLE_STRING_TEST_VALUE,
            preferences.getString(
                DEFAULT_PREFERENCE_NULLABLE_STRING_KEY,
                DEFAULT_PREFERENCE_NULLABLE_STRING_DEFAULT_VALUE))

        assertEquals(DEFAULT_PREFERENCE_NULLABLE_STRING_TEST_VALUE, nullableStringDefaultPreference)
    }

    val INTEGER_PREFERENCE_KEY = "INTEGER_PREFERENCE_KEY"
    val NULLABLE_INTEGER_PREFERENCE_KEY = "NULLABLE_INTEGER_PREFERENCE_KEY"
    val INTEGER_PREFERENCE_DEFAULT_VALUE = 0
    val INTEGER_PREFERENCE_TEST_VALUE = 1

    private var integerDefaultPreference by context.bindSharedPreference(
        INTEGER_PREFERENCE_KEY,
        INTEGER_PREFERENCE_DEFAULT_VALUE)

    private var integerCustomPreference by context.bindSharedPreference(
        INTEGER_PREFERENCE_KEY,
        INTEGER_PREFERENCE_DEFAULT_VALUE,
        CUSTOM_PREFERENCE_NAME)

    var nullableIntegerDefaultPreference by context.bindSharedPreference<Int>(
        NULLABLE_INTEGER_PREFERENCE_KEY,
        null)

    var nullableIntegerCustomPreference by context.bindSharedPreference<Int>(
        NULLABLE_INTEGER_PREFERENCE_KEY,
        null,
        CUSTOM_PREFERENCE_NAME)

    @Test fun bindDefaultSharedPreferenceInteger() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertEquals(INTEGER_PREFERENCE_DEFAULT_VALUE,
            preferences.getInt(
                INTEGER_PREFERENCE_KEY,
                INTEGER_PREFERENCE_DEFAULT_VALUE))

        assertEquals(INTEGER_PREFERENCE_DEFAULT_VALUE, integerDefaultPreference)

        integerDefaultPreference = INTEGER_PREFERENCE_TEST_VALUE

        assertEquals(INTEGER_PREFERENCE_TEST_VALUE,
            preferences.getInt(
                INTEGER_PREFERENCE_KEY,
                INTEGER_PREFERENCE_DEFAULT_VALUE))

        assertEquals(INTEGER_PREFERENCE_TEST_VALUE, nullableStringDefaultPreference)

        assertThrows<IllegalArgumentException> { nullableIntegerDefaultPreference }
        assertThrows<IllegalArgumentException> { nullableIntegerDefaultPreference = null }
        assertThrows<IllegalArgumentException> { nullableIntegerCustomPreference }
        assertThrows<IllegalArgumentException> { nullableIntegerCustomPreference = null }
    }
}
