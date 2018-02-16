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

package androidx.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

/**
 * Set and Get the string value of EditText content
 */
var TextView.content
    get() = text.toString()
    set(value) {
        text = value
    }

/**
 * @returns Weather the EditText text is empty or not
 */
val TextView.isEmpty get() = length() == 0

/**
 * @returns Weather the EditText text is blank or not
 */
val TextView.isBlank get() = text.isBlank()

/**
 * Clears the contents of the EditText
 */
fun TextView.clear() {
    text = ""
}

/**
 * @param before action performed before text changes
 * @param after action performed after text changes
 * @param onTextChanged action preformed on text change
 */
fun TextView.onTextChanged(
    before: (string: String, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
    after: (s: Editable) -> Unit = {},
    onTextChanged: (string: String, start: Int, before: Int, count: Int) -> Unit
) =
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable) = after(s)
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) =
            before(s.toString(), start, count, after)

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) =
            onTextChanged(s.toString(), start, before, count)
    })
