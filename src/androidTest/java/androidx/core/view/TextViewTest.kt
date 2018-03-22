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

package androidx.core.view

import android.support.test.InstrumentationRegistry
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TextViewTest {
    private val textView = TextView(InstrumentationRegistry.getTargetContext())

    @Test fun content() {
        val expected = "Hi Jared"
        textView.text = expected

        val actual = textView.content()

        assertEquals(expected, actual)
    }

    @Test fun testDoBeforeTextChanged() {
        val text = "Hi Jared"
        var called = false
        textView.doBeforeTextChanged { _, _, _, _ ->
            called = true
        }
        textView.text = text

        assertEquals(text, textView.text.toString())
        assertTrue(called)
    }

    @Test fun testDoOnTextChanged() {
        val text = "Hi Jake"
        var called = false
        textView.doOnTextChanged { _, _, _, _ ->
            called = true
        }
        textView.text = text

        assertEquals(text, textView.text.toString())
        assertTrue(called)
    }

    @Test fun testDoAfterTextChanged() {
        val text = "Hi Jared"
        var called = false
        textView.doAfterTextChanged { _ ->
            called = true
        }
        textView.text = text

        assertEquals(text, textView.text.toString())
        assertTrue(called)
    }
}