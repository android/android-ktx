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

import android.support.test.InstrumentationRegistry
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Test

class TextViewTest {
    private val textView = TextView(InstrumentationRegistry.getTargetContext())

    @Test fun doOnEditorAction() {
        val actionDone = EditorInfo.IME_ACTION_DONE
        val actionNext = EditorInfo.IME_ACTION_NEXT
        val actionSend = EditorInfo.IME_ACTION_SEND
        textView.imeOptions = actionDone or actionNext

        var calls = 0
        textView.doOnEditorAction(actionDone, actionNext) {
            calls++
        }

        textView.onEditorAction(actionDone)
        assertEquals(calls, 1)
        textView.onEditorAction(actionNext)
        assertEquals(calls, 2)
        // as the IME_ACTION_SEND is not in the given actionIds, the listener shouldn't get called
        textView.onEditorAction(actionSend)
        assertEquals(calls, 2)
    }
}
