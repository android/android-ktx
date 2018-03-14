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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.core.preference

import android.preference.Preference
import android.preference.PreferenceGroup

/**
 * Returns the preference with `key`.
 *
 * @throws NullPointerException if no preference is found with that key.
 */
inline operator fun PreferenceGroup.get(key: CharSequence): Preference = findPreference(key)

/**
 * Returns the preference at `index`.
 *
 * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the count.
 */
operator fun PreferenceGroup.get(index: Int): Preference = getPreference(index)
        ?: throw IndexOutOfBoundsException("Index: $index, Size: $preferenceCount")

/** Returns `true` if `preference` is found in this preference group. */
operator fun PreferenceGroup.contains(preference: Preference): Boolean {
    for (index in 0 until size) {
        if (get(index) == preference) {
            return true
        }
    }
    return false
}

/** Adds `preference` to this preference group. */
inline operator fun PreferenceGroup.plusAssign(preference: Preference) {
    addPreference(preference)
}

/** Removes `preference` from this preference group. */
inline operator fun PreferenceGroup.minusAssign(preference: Preference) {
    removePreference(preference)
}

/** Returns the number of preferences in this preference group. */
inline val PreferenceGroup.size: Int get() = preferenceCount

/** Returns true if this preference group contains no preferences. */
inline fun PreferenceGroup.isEmpty(): Boolean = size == 0

/** Returns true if this preference group contains one or more preferences. */
inline fun PreferenceGroup.isNotEmpty(): Boolean = size != 0

/** Performs the given action on each preference in this preference group. */
inline fun PreferenceGroup.forEach(action: (preference: Preference) -> Unit) {
    for (index in 0 until size) {
        action(get(index))
    }
}

/** Performs the given action on each preference in this preference group, providing its sequential index. */
inline fun PreferenceGroup.forEachIndexed(action: (index: Int, preference: Preference) -> Unit) {
    for (index in 0 until size) {
        action(index, get(index))
    }
}

/** Returns a [MutableIterator] over the preferences in this preference group. */
operator fun PreferenceGroup.iterator() = object : MutableIterator<Preference> {
    private var index = 0
    override fun hasNext() = index < size
    override fun next() = getPreference(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() {
        removePreference(getPreference(--index))
    }
}
