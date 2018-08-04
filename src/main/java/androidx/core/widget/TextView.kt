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

package androidx.core.widget

import android.view.KeyEvent
import android.widget.TextView

/**
 * Performs the given [action] when an editor action with the given [actionId]
 * is performed on this [TextView]
 */
inline fun TextView.doOnEditorAction(
    vararg actionIds: Int,
    crossinline action: (event: KeyEvent?) -> Unit
) {
    setOnEditorActionListener { _, id, event ->
        if (id in actionIds) {
            action(event)
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}
