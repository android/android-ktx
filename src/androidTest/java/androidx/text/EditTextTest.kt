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

import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.runner.AndroidJUnit4
import android.text.TextWatcher
import android.widget.EditText
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditTextTest {

    private val context = InstrumentationRegistry.getContext()
    private val editText = EditText(context)

    @UiThreadTest
    @Test fun testAddTextWatcher() {
        var beforeTextChangedCalled = false
        var onTextChangedCalled = false
        var afterTextChangedCalled = false

        editText.addTextWatcher(
                beforeTextChanged = { _, _, _, _ ->
                    beforeTextChangedCalled = true
                },
                onTextChanged = { _, _, _, _ ->
                    onTextChangedCalled = true
                },
                afterTextChanged = {
                    afterTextChangedCalled = true
                }
        )

        editText.setText("")
        assertTrue(beforeTextChangedCalled)
        assertTrue(onTextChangedCalled)
        assertTrue(afterTextChangedCalled)
    }

    @UiThreadTest
    @Test fun testBeforeTextChanged() {
        var called = false

        editText.beforeTextChanged { _, _, _, _ ->
            called = true
        }

        editText.setText("")
        assertTrue(called)
    }

    @UiThreadTest
    @Test fun testOnTextChanged() {
        var called = false

        editText.onTextChanged { _, _, _, _ ->
            called = true
        }

        editText.setText("")
        assertTrue(called)
    }

    @UiThreadTest
    @Test fun testAfterTextChanged() {
        var called = false

        editText.afterTextChanged {
            called = true
        }

        editText.setText("")
        assertTrue(called)
    }

}