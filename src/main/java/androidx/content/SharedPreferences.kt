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

package androidx.content

import android.content.SharedPreferences

/**
 * Allows editing of this preference instance with a call to
 * [SharedPreferences.Editor.apply] or [SharedPreferences.Editor.commit] to persist the changes.
 * Default behaviour is [SharedPreferences.Editor.apply].
 *
 * To [SharedPreferences.Editor.apply] changes
 * ```
 * prefs.edit {
 *     putString("key", value)
 * }
 * ```
 * To [SharedPreferences.Editor.commit] changes
 * ```
 * prefs.edit(commit = true) {
 *     putString("key", value)
 * }
 * ```
 */
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