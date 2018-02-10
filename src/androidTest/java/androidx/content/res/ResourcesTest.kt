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

package androidx.content.res

import android.graphics.Typeface
import android.support.test.InstrumentationRegistry
import android.text.Spanned
import android.text.style.StyleSpan
import androidx.kotlin.test.R
import org.junit.Assert.assertEquals
import org.junit.Test

class ResourcesTest {
    private val resources = InstrumentationRegistry.getContext().resources

    @Test fun getText() {
        val text = resources.getText(R.string.welcome_message, "Paul<O'Leary", 5)
        assertEquals("Hello, Paul<O'Leary! You have 5 new messages.", text.toString())

        val spans = (text as Spanned).getSpans(0, text.length, StyleSpan::class.java)
        assertEquals(1, spans.size)
        assertEquals(Typeface.BOLD, spans[0].style)
    }
}
