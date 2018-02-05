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

fun TextView.doAfterChanged(
    afterChanged: (Editable) -> Unit
): TextWatcher = addTextChangedListener(afterChanged = afterChanged)

fun TextView.doBeforeChanged(
    beforeChanged: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit
): TextWatcher = addTextChangedListener(beforeChanged = beforeChanged)

fun TextView.doOnChanged(
    onChanged: (s: CharSequence, start: Int, before: Int, count: Int) -> Unit
): TextWatcher = addTextChangedListener(onChanged = onChanged)

fun TextView.addTextChangedListener(
    beforeChanged: ((s: CharSequence, start: Int, count: Int, after: Int) -> Unit)? = null,
    afterChanged: ((Editable) -> Unit)? = null,
    onChanged: ((s: CharSequence, start: Int, before: Int, count: Int) -> Unit)? = null
): TextWatcher {
    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            afterChanged?.invoke(s)
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeChanged?.invoke(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onChanged?.invoke(s, start, before, count)
        }
    }
    addTextChangedListener(watcher)
    return watcher
}