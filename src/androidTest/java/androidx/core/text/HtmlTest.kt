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

import android.text.Html.FROM_HTML_MODE_COMPACT
import android.text.Html.ImageGetter
import android.text.Html.TagHandler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HtmlTest {
    private val imageGetter = ImageGetter { null }
    private val tagHandler = TagHandler { _, _, _, _ -> }

    @Test fun parseAsHtml() {
        val parsed = "<b>Hi</b> © > <".parseAsHtml().toString()
        assertEquals("Hi \u00a9 > <", parsed)
    }

    @Test fun parseAsHtmlFlags() {
        val parsed = "<b>Hi</b> © > <".parseAsHtml(FROM_HTML_MODE_COMPACT).toString()
        assertEquals("Hi \u00a9 > <", parsed)
    }

    @Test fun parseAsHtmlImageGetterTagHandler() {
        val parsed = "<b>Hi</b> © > <"
            .parseAsHtml(FROM_HTML_MODE_COMPACT, imageGetter, tagHandler)
            .toString()
        assertEquals("Hi \u00a9 > <", parsed)
    }

    @Test fun parseAsHtmlFlagsImageGetterTagHandler() {
        val parsed = "<b>Hi</b> © > <"
            .parseAsHtml(imageGetter = imageGetter, tagHandler = tagHandler)
            .toString()
        assertEquals("Hi \u00a9 > <", parsed)
    }

    @Test fun convertToHtml() {
        val html = buildSpannedString {
            bold {
                append("Hi")
            }
        }.toHtml()
        assertTrue(html, html.contains("<b>Hi</b>"))
    }
}
