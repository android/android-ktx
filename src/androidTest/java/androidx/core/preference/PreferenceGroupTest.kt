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

package androidx.core.preference

import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceGroup
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import androidx.core.TestPreferenceActivity
import androidx.testutils.assertThrows
import androidx.testutils.fail
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PreferenceGroupTest {

    @JvmField
    @Rule
    val rule = ActivityTestRule(TestPreferenceActivity::class.java)
    private val context = InstrumentationRegistry.getContext()
    private lateinit var preferenceGroup: PreferenceGroup

    @Before fun setup() {
        preferenceGroup = (rule
            .activity
            .fragmentManager
            .findFragmentByTag(TestPreferenceActivity.TAG) as PreferenceFragment).preferenceScreen
    }

    @Test fun get() {
        val key = "key"
        val preference = Preference(context)
        preference.key = key
        preferenceGroup.addPreference(preference)
        assertSame(preference, preferenceGroup[key])
        assertSame(preference, preferenceGroup[0])
    }

    @Test fun contains() {
        val preference = Preference(context)
        assertFalse(preference in preferenceGroup)
        assertTrue(preference !in preferenceGroup)
        preferenceGroup.addPreference(preference)
        assertFalse(preference !in preferenceGroup)
        assertTrue(preference in preferenceGroup)
        preferenceGroup.removePreference(preference)
        assertFalse(preference in preferenceGroup)
        assertTrue(preference !in preferenceGroup)
    }

    @Test fun plusAssign() {
        assertEquals(0, preferenceGroup.preferenceCount)

        val preference1 = Preference(context)
        preferenceGroup += preference1
        assertEquals(1, preferenceGroup.preferenceCount)
        assertSame(preference1, preferenceGroup.getPreference(0))

        val preference2 = Preference(context)
        preferenceGroup += preference2
        assertEquals(2, preferenceGroup.preferenceCount)
        assertSame(preference2, preferenceGroup.getPreference(1))
    }

    @Test fun minusAssign() {
        val preference1 = Preference(context)
        preferenceGroup.addPreference(preference1)
        val preference2 = Preference(context)
        preferenceGroup.addPreference(preference2)

        assertEquals(2, preferenceGroup.preferenceCount)

        preferenceGroup -= preference2
        assertEquals(1, preferenceGroup.preferenceCount)
        assertSame(preference1, preferenceGroup.getPreference(0))

        preferenceGroup -= preference1
        assertEquals(0, preferenceGroup.preferenceCount)
    }

    @Test fun size() {
        assertEquals(0, preferenceGroup.size)

        val preference = Preference(context)
        preferenceGroup.addPreference(preference)
        assertEquals(1, preferenceGroup.size)

        preferenceGroup.removePreference(preference)
        assertEquals(0, preferenceGroup.size)
    }

    @Test fun isEmpty() {
        assertTrue(preferenceGroup.isEmpty())
        preferenceGroup.addPreference(Preference(context))
        assertFalse(preferenceGroup.isEmpty())
    }

    @Test fun isNotEmpty() {
        assertFalse(preferenceGroup.isNotEmpty())
        preferenceGroup.addPreference(Preference(context))
        assertTrue(preferenceGroup.isNotEmpty())
    }

    @Test fun forEach() {
        preferenceGroup.forEach {
            fail("Empty preference group should not invoke lambda")
        }

        val preference1 = Preference(context).apply { key = "ASD" }
        preferenceGroup.addPreference(preference1)
        val preference2 = Preference(context)
        preferenceGroup.addPreference(preference2)

        val preferences = mutableListOf<Preference>()
        preferenceGroup.forEach {
            preferences += it
        }
        assertThat(preferences).containsExactly(preference1, preference2)
    }

    @Test fun forEachIndexed() {
        preferenceGroup.forEach {
            fail("Empty preference group should not invoke lambda")
        }

        val preference1 = Preference(context)
        preferenceGroup.addPreference(preference1)
        val preference2 = Preference(context)
        preferenceGroup.addPreference(preference2)

        val preferences = mutableListOf<Preference>()
        preferenceGroup.forEachIndexed { index, preference ->
            assertEquals(index, preferences.size)
            preferences += preference
        }
        assertThat(preferences).containsExactly(preference1, preference2)
    }

    @Test fun iterator() {
        val preference1 = Preference(context)
        preferenceGroup.addPreference(preference1)
        val preference2 = Preference(context)
        preferenceGroup.addPreference(preference2)

        val iterator = preferenceGroup.iterator()
        assertTrue(iterator.hasNext())
        assertSame(preference1, iterator.next())
        assertTrue(iterator.hasNext())
        assertSame(preference2, iterator.next())
        assertFalse(iterator.hasNext())
        assertThrows<IndexOutOfBoundsException> {
            iterator.next()
        }
    }

    @Test fun children() {
        val preferences = listOf(Preference(context), Preference(context), Preference(context))
        preferences.forEach { preferenceGroup.addPreference(it) }

        preferenceGroup.children.forEachIndexed { index, child ->
            assertSame(preferences[index], child)
        }
    }
}
