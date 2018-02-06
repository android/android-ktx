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

package androidx.text

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Sets a [TextWatcher] to this [EditText] using the provided actions
 *
 */
fun EditText.addTextWatcher(
        beforeTextChanged: ((s: CharSequence, start: Int, count: Int, after: Int) -> Unit)? = null,
        onTextChanged: ((s: CharSequence, start: Int, before: Int, count: Int) -> Unit)? = null,
        afterTextChanged: ((s: Editable) -> Unit)? = null
): TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable) {
            afterTextChanged?.invoke(s)
        }
    }
    addTextChangedListener(textWatcher)
    return textWatcher
}


/**
 * Add an action which will be invoked before the text of the [EditText] changes
 * @return the [TextWatcher] added to the EditText
 * @see [TextWatcher.beforeTextChanged]
 */
fun EditText.beforeTextChanged(action: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit): TextWatcher {
    return addTextWatcher(
            beforeTextChanged = action
    )
}


/**
 * Add an action which will be invoked when the text of the [EditText] changes
 * @return the [TextWatcher] added to the EditText
 * @see [TextWatcher.onTextChanged]
 */
fun EditText.onTextChanged(action: (s: CharSequence, start: Int, before: Int, count: Int) -> Unit): TextWatcher {
    return addTextWatcher(
            onTextChanged = action
    )
}


/**
 * Add an action which will be invoked after the text of the [EditText] changes
 * @return the [TextWatcher] added to the EditText
 * @see [TextWatcher.afterTextChanged]
 */
fun EditText.afterTextChanged(action: (s: Editable) -> Unit): TextWatcher {
    return addTextWatcher(
            afterTextChanged = action
    )
}