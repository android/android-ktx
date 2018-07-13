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

package androidx.core.text

import android.graphics.Color.RED
import android.graphics.Color.YELLOW
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import android.text.SpannedString
import android.text.style.BackgroundColorSpan
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.UnderlineSpan
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class SpannableStringBuilderTest {
    @Test fun builder() {
        val result: SpannedString = buildSpannedString {
            append("Hello,")
            append(" World")
        }
        assertEquals("Hello, World", result.toString())
    }

    @Test fun builderInSpan() {
        val bold = StyleSpan(BOLD)
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            inSpans(bold) {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val boldSpan = spans.filterIsInstance<StyleSpan>().single()
        assertSame(bold, boldSpan)
        assertEquals(7, result.getSpanStart(bold))
        assertEquals(12, result.getSpanEnd(bold))
    }

    @Test fun builderBold() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            bold {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val bold = spans.filterIsInstance<StyleSpan>().single()
        assertEquals(BOLD, bold.style)
        assertEquals(7, result.getSpanStart(bold))
        assertEquals(12, result.getSpanEnd(bold))
    }

    @Test fun builderItalic() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            italic {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val italic = spans.filterIsInstance<StyleSpan>().single()
        assertEquals(ITALIC, italic.style)
        assertEquals(7, result.getSpanStart(italic))
        assertEquals(12, result.getSpanEnd(italic))
    }

    @Test fun builderUnderline() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            underline {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val underline = spans.filterIsInstance<UnderlineSpan>().single()
        assertEquals(7, result.getSpanStart(underline))
        assertEquals(12, result.getSpanEnd(underline))
    }

    @Test fun builderColor() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            color(RED) {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val color = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(RED, color.foregroundColor)
        assertEquals(7, result.getSpanStart(color))
        assertEquals(12, result.getSpanEnd(color))
    }

    @Test fun builderBackgroundColor() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            backgroundColor(RED) {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val color = spans.filterIsInstance<BackgroundColorSpan>().single()
        assertEquals(RED, color.backgroundColor)
        assertEquals(7, result.getSpanStart(color))
        assertEquals(12, result.getSpanEnd(color))
    }

    @Test fun builderStrikeThrough() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            strikeThrough {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val strikeThrough = spans.filterIsInstance<StrikethroughSpan>().single()
        assertEquals(7, result.getSpanStart(strikeThrough))
        assertEquals(12, result.getSpanEnd(strikeThrough))
    }

    @Test fun builderScale() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            scale(2f) {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val scale = spans.filterIsInstance<RelativeSizeSpan>().single()
        assertEquals(2f, scale.sizeChange)
        assertEquals(7, result.getSpanStart(scale))
        assertEquals(12, result.getSpanEnd(scale))
    }

    @Test fun builderSuperscript() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            superscript {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val superscript = spans.filterIsInstance<SuperscriptSpan>().single()
        assertEquals(7, result.getSpanStart(superscript))
        assertEquals(12, result.getSpanEnd(superscript))
    }

    @Test fun builderSubscript() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            subscript {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(1, spans.size)

        val subscript = spans.filterIsInstance<SubscriptSpan>().single()
        assertEquals(7, result.getSpanStart(subscript))
        assertEquals(12, result.getSpanEnd(subscript))
    }

    @Test fun nested() {
        val result: SpannedString = buildSpannedString {
            color(RED) {
                append('H')
                subscript {
                    append('e')
                }
                append('l')
                superscript {
                    append('l')
                }
                append('o')
            }
            append(", ")
            backgroundColor(YELLOW) {
                append('W')
                underline {
                    append('o')
                    bold {
                        append('r')
                    }
                    append('l')
                }
                append('d')
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(6, spans.size)

        val color = spans.filterIsInstance<ForegroundColorSpan>().single()
        assertEquals(RED, color.foregroundColor)
        assertEquals(0, result.getSpanStart(color))
        assertEquals(5, result.getSpanEnd(color))

        val subscript = spans.filterIsInstance<SubscriptSpan>().single()
        assertEquals(1, result.getSpanStart(subscript))
        assertEquals(2, result.getSpanEnd(subscript))

        val superscript = spans.filterIsInstance<SuperscriptSpan>().single()
        assertEquals(3, result.getSpanStart(superscript))
        assertEquals(4, result.getSpanEnd(superscript))

        val backgroundColor = spans.filterIsInstance<BackgroundColorSpan>().single()
        assertEquals(YELLOW, backgroundColor.backgroundColor)
        assertEquals(7, result.getSpanStart(backgroundColor))
        assertEquals(12, result.getSpanEnd(backgroundColor))

        val underline = spans.filterIsInstance<UnderlineSpan>().single()
        assertEquals(8, result.getSpanStart(underline))
        assertEquals(11, result.getSpanEnd(underline))

        val bold = spans.filterIsInstance<StyleSpan>().single()
        assertEquals(BOLD, bold.style)
        assertEquals(9, result.getSpanStart(bold))
        assertEquals(10, result.getSpanEnd(bold))
    }

    @Test fun multipleSpans() {
        val result: SpannedString = buildSpannedString {
            append("Hello, ")
            inSpans(BulletSpan(), UnderlineSpan()) {
                append("World")
            }
        }
        assertEquals("Hello, World", result.toString())

        val spans = result.getSpans<Any>()
        assertEquals(2, spans.size)

        val bullet = spans.filterIsInstance<BulletSpan>().single()
        assertEquals(7, result.getSpanStart(bullet))
        assertEquals(12, result.getSpanEnd(bullet))
        val underline = spans.filterIsInstance<UnderlineSpan>().single()
        assertEquals(7, result.getSpanStart(underline))
        assertEquals(12, result.getSpanEnd(underline))
    }
}
