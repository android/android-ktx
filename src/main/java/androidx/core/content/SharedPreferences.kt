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
 * Returns value of given key
 */
inline fun <reified T> SharedPreferences.get(key: String, default: T): T {
    return when (T::class) {
        Boolean::class -> getBoolean(key, default as Boolean) as T
        String::class -> getString(key, default as String) as T
        Int::class -> getString(key, default as String) as T
        Float::class -> getFloat(key, default as Float) as T
        Long::class -> getLong(key, default as Long) as T
        else -> default
    }
}

/**
 * Allows editing of this preference instance with a call to [apply][SharedPreferences.Editor.apply]
 * or [commit][SharedPreferences.Editor.commit] to persist the changes.
 * Default behaviour is [apply][SharedPreferences.Editor.apply].
 */
@SuppressLint("ApplySharedPref")
inline fun <reified T> SharedPreferences.put(key: String, value: T, commit: Boolean = false) {
    val editor = edit()
    when (T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        String::class -> editor.putString(key, value as String)
        Float::class -> editor.putFloat(key, value as Float)
        Long::class -> editor.putLong(key, value as Long)
        Int::class -> editor.putInt(key, value as Int)
    }
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}
