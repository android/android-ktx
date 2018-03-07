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

package androidx.text

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
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

    @Test fun overlay_MultipleSpans() {
        val s = "Hello, World".toSpannable()
        assertEquals(5, s.overlay("Hello", StyleSpan(BOLD)))
        assertEquals(12, s.overlay("World", UnderlineSpan(), true))

        val spans = s.getSpans<Any>()

        val bold = spans.filterIsInstance<StyleSpan>().single()
        assertEquals(0, s.getSpanStart(bold))
        assertEquals(5, s.getSpanEnd(bold))

        val underline = spans.filterIsInstance<UnderlineSpan>().single()
        assertEquals(7, s.getSpanStart(underline))
        assertEquals(12, s.getSpanEnd(underline))
    }

    @Test fun overlay_CaseSensitiveNoMatch() {
        val s = "Hello, World".toSpannable()
        assertEquals(-1, s.overlay("world", ForegroundColorSpan(Color.BLUE)))

        val spans = s.getSpans<Any>()
        assertEquals(0, spans.size)
    }

    @Test fun overlay_CaseSensitiveMatch() {
        val s = "Hello, World".toSpannable()
        assertEquals(12, s.overlay("World", ForegroundColorSpan(Color.BLUE)))

        val spans = s.getSpans<Any>()

        val blue = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(7, s.getSpanStart(blue))
        assertEquals(12, s.getSpanEnd(blue))
    }

    @Test fun overlay_CaseInsensitiveMatch() {
        val s = "Hello, World".toSpannable()
        assertEquals(12, s.overlay("world", ForegroundColorSpan(Color.BLUE), true))

        val spans = s.getSpans<Any>()

        val blue = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(7, s.getSpanStart(blue))
        assertEquals(12, s.getSpanEnd(blue))
    }

    @Test fun overlay_StartAfterFirst() {
        val s = "Hello, World, World".toSpannable()
        assertEquals(19, s.overlay("World", ForegroundColorSpan(Color.BLUE), false, 8))

        val spans = s.getSpans<Any>()

        val blue = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(14, s.getSpanStart(blue))
        assertEquals(19, s.getSpanEnd(blue))
    }

    @Test fun overlay_MultipleOccurrencesAppliesOnce() {
        val s = "Hello, World, World".toSpannable()
        assertEquals(12, s.overlay("World", ForegroundColorSpan(Color.BLUE)))

        val spans = s.getSpans<Any>()

        assertEquals(1, spans.size)

        val blue = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(7, s.getSpanStart(blue))
        assertEquals(12, s.getSpanEnd(blue))
    }

    @Test fun overlayAll_MultipleSpans() {
        val s = "Hello, World, I'm world".toSpannable()
        s.overlayAll("Hello") { StyleSpan(BOLD) }
        s.overlayAll("World", true) { UnderlineSpan() }
        s.overlayAll("world") { ForegroundColorSpan(Color.BLUE) }

        val spans = s.getSpans<Any>()
        assertEquals(4, spans.size)

        val bolds = spans.filterIsInstance<StyleSpan>()
        assertEquals(1, bolds.size)

        val underlines = spans.filterIsInstance<UnderlineSpan>()
        assertEquals(2, underlines.size)

        val blues = spans.filterIsInstance<ForegroundColorSpan>()
        assertEquals(1, blues.size)
    }

    @Test fun overlayAll_CaseSensitiveNoMatch() {
        val s = "Hello, World, I'm World".toSpannable()
        s.overlayAll("world") { ForegroundColorSpan(Color.BLUE) }

        val spans = s.getSpans<Any>()
        assertEquals(0, spans.size)
    }

    @Test fun overlayAll_CaseSensitiveMatch() {
        val s = "Hello, World, I'm world".toSpannable()
        s.overlayAll("world") { ForegroundColorSpan(Color.BLUE) }

        val spans = s.getSpans<Any>()
        assertEquals(1, spans.size)

        val blue = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(18, s.getSpanStart(blue))
        assertEquals(23, s.getSpanEnd(blue))
    }

    @Test fun overlayAll_CaseInsensitiveMultipleMatch() {
        val s = "Hello, World, I'm world".toSpannable()
        s.overlayAll("World", true) { UnderlineSpan() }

        val spans = s.getSpans<Any>()

        val underlines = spans.filterIsInstance<UnderlineSpan>().sortedBy { s.getSpanStart(it) }
        assertEquals(2, underlines.count())

        val underline1 = underlines.first()
        assertEquals(7, s.getSpanStart(underline1))
        assertEquals(12, s.getSpanEnd(underline1))

        val underline2 = underlines.last()
        assertEquals(18, s.getSpanStart(underline2))
        assertEquals(23, s.getSpanEnd(underline2))
    }

    @Test fun overlayAll_StartAfterFirst() {
        val s = "Hello, World, I'm World".toSpannable()
        s.overlayAll("World", false, 12) { ForegroundColorSpan(Color.BLUE) }

        val spans = s.getSpans<Any>()
        assertEquals(1, spans.size)

        val blue = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(18, s.getSpanStart(blue))
        assertEquals(23, s.getSpanEnd(blue))
    }
}
