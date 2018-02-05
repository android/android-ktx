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

import android.support.test.InstrumentationRegistry
import android.widget.TextView
import org.junit.Assert.assertTrue
import org.junit.Test

class TextViewTest {
    private val context = InstrumentationRegistry.getContext()
    private val view = TextView(context)

    @Test fun doAfterChanged() {
        var called = false
        view.doAfterChanged {
            called = true
        }
        view.text = "test"
        assertTrue(called)
    }

    @Test fun doBeforeChanged() {
        var called = false
        view.doBeforeChanged { s, start, count, after ->
            called = true
        }
        view.text = "test"
        assertTrue(called)
    }

    @Test fun doOnChanged() {
        var called = false
        view.doOnChanged { s, start, count, after ->
            called = true
        }
        view.text = "test"
        assertTrue(called)
    }
}