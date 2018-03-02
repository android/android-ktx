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

package androidx.widget

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 * Returns the text displayed by the [TextView].
 */
fun TextView.content() = text.toString()

/**
 * Adds a [TextWatcher.beforeTextChanged] to the list of those whose methods are called
 * whenever this [TextView]'s text changes.
 *
 * @see TextWatcher.beforeTextChanged
 */
fun TextView.doOnBeforeTextChanged(
    action: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit
) = addTextListener(beforeChanged = action)

/**
 * Adds a [TextWatcher.afterTextChanged] to the list of those whose methods are called
 * whenever this [TextView]'s text changes.
 *
 * @see TextWatcher.afterTextChanged
 */
fun TextView.doOnAfterTextChanged(
    action: (s: Editable) -> Unit
) = addTextListener(afterChanged = action)

/**
 * Adds a [TextWatcher] to the list of those whose methods are called
 * whenever this [TextView]'s text changes.
 */
fun TextView.addTextListener(
    beforeChanged: ((s: CharSequence, start: Int, count: Int, after: Int) -> Unit)? = null,
    onChanged: ((s: CharSequence, start: Int, before: Int, count: Int) -> Unit)? = null,
    afterChanged: ((s: Editable) -> Unit)? = null
) =
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeChanged?.invoke(s.toString(), start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onChanged?.invoke(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable) {
            afterChanged?.invoke(s)
        }
    })
