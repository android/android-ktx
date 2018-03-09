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

package androidx.core.text

import android.graphics.Typeface.BOLD
import android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE
import android.text.SpannedString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class SpannedStringTest {

    @Test fun toSpanned() = assertTrue("Hello, World".toSpanned() is SpannedString)

    @Test fun getSpans() {
        val bold = StyleSpan(BOLD)
        val underline = UnderlineSpan()

        val s = "Hello, World".toSpannable()
        s.setSpan(bold, 0, 5, SPAN_INCLUSIVE_EXCLUSIVE)
        s.setSpan(underline, 7, 12, SPAN_INCLUSIVE_EXCLUSIVE)

        assertSame(bold, s.getSpans<StyleSpan>().single())
        assertSame(underline, s.getSpans<UnderlineSpan>().single())
        assertEquals(s.getSpans<Any>().size, 2)

        assertSame(bold, s.getSpans<Any>(0, 6).single())
        assertSame(underline, s.getSpans<Any>(6, 12).single())
    }
}
