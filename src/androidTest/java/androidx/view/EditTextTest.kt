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

import android.support.test.InstrumentationRegistry
import android.widget.EditText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EditTextTest {
    private val context = InstrumentationRegistry.getContext()
    private val editText = EditText(context)

    @Test fun getContent() {
        editText.setText("Hello")
        val text = editText.text.toString()
        assertEquals(text, editText.content)
    }

    @Test fun isEmpty() {
        editText.setText("")
        assertTrue(editText.isEmpty)
    }

    @Test fun isBlank() {
        editText.setText(" ")
        assertTrue(editText.isBlank)
    }

    @Test fun clear() {
        editText.setText("Hello")
        editText.clear()
        assertTrue(editText.text.isEmpty())
    }

    @Test fun onTextChanged() {
        var calls = 0
        editText.onTextChanged { string, start, count, after ->
            calls++
            assertEquals(string, "Hello")
        }
        editText.setText("Hello")
        assertEquals(calls, 1)
    }
}