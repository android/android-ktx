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

package androidx.core.content

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.util.Log
import androidx.testutils.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Test

class SharedPreferencesPropertyTest {

    private val context = InstrumentationRegistry.getContext()

    val INTEGER_PREFERENCE_KEY = "INTEGER_PREFERENCE_KEY"
    val BOOLEAN_PREFERENCE_KEY = "BOOLEAN_PREFERENCE_KEY"
    val FLOAT_PREFERENCE_KEY = "FLOAT_PREFERENCE_KEY"
    val LONG_PREFERENCE_KEY = "LONG_PREFERENCE_KEY"
    val STRING_PREFERENCE_KEY = "STRING_PREFERENCE_KEY"
    val STRING_SET_PREFERENCE_KEY = "STRING_SET_PREFERENCE_KEY"
    val ANY_PREFERENCE_KEY = "ANY_PREFERENCE_KEY"

    val INTEGER_PREFERENCE_DEFAULT_VALUE = 0
    val BOOLEAN_PREFERENCE_DEFAULT_VALUE = false
    val FLOAT_PREFERENCE_DEFAULT_VALUE = 0.1f
    val LONG_PREFERENCE_DEFAULT_VALUE = 1212131232132132131
    val STRING_PREFERENCE_DEFAULT_VALUE = "default"
    val STRING_SET_PREFERENCE_DEFAULT_VALUE = HashSet<String>()
    val ANY_PREFERENCE_DEFAULT_VALUE = Any()

    val INTEGER_PREFERENCE_TEST_VALUE = INTEGER_PREFERENCE_DEFAULT_VALUE + 1
    val BOOLEAN_PREFERENCE_TEST_VALUE = !BOOLEAN_PREFERENCE_DEFAULT_VALUE
    val FLOAT_PREFERENCE_TEST_VALUE = FLOAT_PREFERENCE_DEFAULT_VALUE + 1
    val LONG_PREFERENCE_TEST_VALUE = 1212131232132132131
    val STRING_PREFERENCE_TEST_VALUE = "test"
    val STRING_SET_PREFERENCE_TEST_VALUE = "default"
    val ANY_PREFERENCE_TEST_VALUE = Any()

    private var integerPreference by context.bindSharedPreference(
        INTEGER_PREFERENCE_KEY,
        INTEGER_PREFERENCE_DEFAULT_VALUE)

    private var booleanPreference by context.bindSharedPreference(
        BOOLEAN_PREFERENCE_KEY,
        BOOLEAN_PREFERENCE_DEFAULT_VALUE)

    private var floatPreference by context.bindSharedPreference(
        FLOAT_PREFERENCE_KEY,
        FLOAT_PREFERENCE_DEFAULT_VALUE)

    private var longPreference by context.bindSharedPreference(
        LONG_PREFERENCE_KEY,
        LONG_PREFERENCE_DEFAULT_VALUE)

    private var stringPreference by context.bindSharedPreference(
        STRING_PREFERENCE_KEY,
        STRING_PREFERENCE_DEFAULT_VALUE)

    private var stringSetPreference by context.bindSharedPreference(
        STRING_SET_PREFERENCE_KEY,
        STRING_SET_PREFERENCE_DEFAULT_VALUE)

    private var anyPreference by context.bindSharedPreference(
        ANY_PREFERENCE_KEY,
        ANY_PREFERENCE_DEFAULT_VALUE)

    private var nullableIntegerPreference by context.bindSharedPreference<Int>(
        "int", null)

    private var nullableBooleanPreference by context.bindSharedPreference<Boolean>(
        "boolean", null)

    @Test fun bindIntegerPreference() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertEquals(preferences.getInt(INTEGER_PREFERENCE_KEY, INTEGER_PREFERENCE_DEFAULT_VALUE),
            integerPreference)

        integerPreference = INTEGER_PREFERENCE_TEST_VALUE

        assertEquals(INTEGER_PREFERENCE_TEST_VALUE, integerPreference)

        assertEquals(preferences.getInt(INTEGER_PREFERENCE_KEY, INTEGER_PREFERENCE_DEFAULT_VALUE),
            integerPreference)
    }

    @Test fun bindBooleanPreference() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertEquals(preferences.getBoolean(BOOLEAN_PREFERENCE_KEY, BOOLEAN_PREFERENCE_DEFAULT_VALUE),
            booleanPreference)

        booleanPreference = BOOLEAN_PREFERENCE_TEST_VALUE

        assertEquals(BOOLEAN_PREFERENCE_TEST_VALUE, booleanPreference)

        assertEquals(preferences.getBoolean(BOOLEAN_PREFERENCE_KEY, BOOLEAN_PREFERENCE_DEFAULT_VALUE),
            booleanPreference)
    }

    @Test fun bindFloatPreference() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertEquals(preferences.getFloat(FLOAT_PREFERENCE_KEY, FLOAT_PREFERENCE_DEFAULT_VALUE),
            floatPreference)

        floatPreference = FLOAT_PREFERENCE_TEST_VALUE

        assertEquals(FLOAT_PREFERENCE_TEST_VALUE, floatPreference)

        assertEquals(preferences.getFloat(FLOAT_PREFERENCE_KEY, FLOAT_PREFERENCE_DEFAULT_VALUE),
            floatPreference)
    }

    @Test fun bindLongPreference() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertEquals(preferences.getLong(LONG_PREFERENCE_KEY, LONG_PREFERENCE_DEFAULT_VALUE),
            longPreference)

        longPreference = LONG_PREFERENCE_TEST_VALUE

        assertEquals(LONG_PREFERENCE_TEST_VALUE, longPreference!!)

        assertEquals(preferences.getLong(LONG_PREFERENCE_KEY, LONG_PREFERENCE_DEFAULT_VALUE),
            longPreference!!)
    }

    @Test fun bindStringPreference() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertEquals(preferences.getString(STRING_PREFERENCE_KEY, STRING_PREFERENCE_DEFAULT_VALUE),
            stringPreference)

        stringPreference = STRING_PREFERENCE_TEST_VALUE

        assertEquals(STRING_PREFERENCE_TEST_VALUE, stringPreference)

        assertEquals(preferences.getString(STRING_PREFERENCE_KEY, STRING_PREFERENCE_DEFAULT_VALUE),
            stringPreference)
    }

    @Test fun bindStringSetPreference() {
        /*val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        assertEquals(preferences.getStringSet(STRING_SET_PREFERENCE_KEY, STRING_SET_PREFERENCE_DEFAULT_VALUE),
            stringSetPreference)

        stringSetPreference = STRING_SET_PREFERENCE_TEST_VALUE

        assertEquals(STRING_PREFERENCE_TEST_VALUE, stringSetPreference)

        assertEquals(preferences.getStringSet(STRING_SET_PREFERENCE_KEY, STRING_SET_PREFERENCE_DEFAULT_VALUE),
            stringSetPreference)*/
    }

    @Test fun bindAnyPreference() {
        assertThrows<IllegalArgumentException> {
            Log.d("", anyPreference.toString())
        }
        assertThrows<IllegalArgumentException> { anyPreference = null }
    }

    @Test fun bindNullableIntegerPreference() {
        assertThrows<IllegalArgumentException> { nullableIntegerPreference }
        assertThrows<IllegalArgumentException> { nullableIntegerPreference = null }
    }

    @Test fun bindNullableBooleanPreference() {
        assertThrows<IllegalArgumentException> { nullableBooleanPreference }
        assertThrows<IllegalArgumentException> { nullableBooleanPreference = null }
    }
}
