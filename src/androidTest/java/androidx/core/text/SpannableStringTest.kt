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
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SpannableStringTest {

    @Test fun toSpannableString() = assertTrue("Hello, World".toSpannable() is SpannableString)

    @Test fun plusAssign() {
        val s = "Hello, World".toSpannable()

        val bold = StyleSpan(BOLD)
        s += bold
        assertEquals(0, s.getSpanStart(bold))
        assertEquals(s.length, s.getSpanEnd(bold))
    }

    @Test fun minusAssign() {
        val s = "Hello, World".toSpannable()
        val bold = StyleSpan(BOLD)
        s += bold
        assertTrue(s.getSpans<Any>().isNotEmpty())
        s -= bold
        assertTrue(s.getSpans<Any>().isEmpty())
    }

    @Test fun clearSpans() {
        val s = "Hello, World".toSpannable()
        s += StyleSpan(BOLD)
        s += UnderlineSpan()
        assertTrue(s.getSpans<Any>().isNotEmpty())
        s.clearSpans()
        assertTrue(s.getSpans<Any>().isEmpty())
    }

    @Test fun setIndices() {
        val s = "Hello, World".toSpannable()
        s[0, 5] = StyleSpan(BOLD)
        s[7, 12] = UnderlineSpan()

        val spans = s.getSpans<Any>()

        val bold = spans.filterIsInstance<StyleSpan>().single()
        assertEquals(0, s.getSpanStart(bold))
        assertEquals(5, s.getSpanEnd(bold))

        val underline = spans.filterIsInstance<UnderlineSpan>().single()
        assertEquals(7, s.getSpanStart(underline))
        assertEquals(12, s.getSpanEnd(underline))
    }

    @Test fun setRange() {
        val s = "Hello, World".toSpannable()
        s[0..5] = StyleSpan(BOLD)
        s[7..12] = UnderlineSpan()

        val spans = s.getSpans<Any>()

        val bold = spans.filterIsInstance<StyleSpan>().single()
        assertEquals(0, s.getSpanStart(bold))
        assertEquals(5, s.getSpanEnd(bold))

        val underline = spans.filterIsInstance<UnderlineSpan>().single()
        assertEquals(7, s.getSpanStart(underline))
        assertEquals(12, s.getSpanEnd(underline))
    }
}
