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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.core.content

import android.annotation.SuppressLint
import android.content.SharedPreferences

/**
 * Allows editing of this preference instance with a call to [apply][SharedPreferences.Editor.apply]
 * or [commit][SharedPreferences.Editor.commit] to persist the changes.
 * Default behaviour is [apply][SharedPreferences.Editor.apply].
 * ```
 * prefs.edit {
 *     putString("key", value)
 * }
 * ```
 * To [commit][SharedPreferences.Editor.commit] changes:
 * ```
 * prefs.edit(commit = true) {
 *     putString("key", value)
 * }
 * ```
 */
@SuppressLint("ApplySharedPref")
inline fun SharedPreferences.edit(
        commit: Boolean = false,
        action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}

/**
 * Set a double value in the preferences editor, to be written back once
 * [commit][SharedPreferences.Editor.commit] or [apply][SharedPreferences.Editor.apply] are called.
 *
 * @param key The name of the preference to modify.
 * @param value The new value for the preference.
 *
 * @return Returns a reference to the same Editor object, so you can
 * chain put calls together.
 */

inline fun SharedPreferences.Editor.putDouble(key: String, value: Double): SharedPreferences.Editor {
    putLong(key, value.toRawBits())
    return this
}

/**
 * Retrieve a double value from the preferences.
 *
 * @param key The name of the preference to retrieve.
 * @param defValue Value to return if this preference does not exist.
 *
 * @return Returns the preference value if it exists, or defValue.  Throws
 * ClassCastException if there is a preference with this name that is not
 * a double.
 *
 * @throws ClassCastException
 */

inline fun SharedPreferences.getDouble(key: String, defValue: Double): Double {
    return Double.fromBits(getLong(key, defValue.toRawBits()))
}
