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

import android.annotation.SuppressLint
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Html.ImageGetter
import android.text.Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE
import android.text.Html.TagHandler
import android.text.Spanned

/**
 * Returns a [Spanned] from parsing this string as HTML.
 *
 * @param flags Additional option to set the behavior of the HTML parsing. Default is set to
 * [Html.FROM_HTML_MODE_LEGACY] which was introduced in API 24.
 * @param imageGetter Returns displayable styled text from the provided HTML string.
 * @param tagHandler Notified when HTML tags are encountered a tag the parser does
 * not know how to interpret.
 *
 * @see Html.fromHtml
 */
fun String.parseAsHtml(
    @SuppressLint("InlinedApi") flags: Int = FROM_HTML_MODE_LEGACY,
    imageGetter: ImageGetter? = null,
    tagHandler: TagHandler? = null
): Spanned = HtmlCompat.fromHtml(this, flags, imageGetter, tagHandler)

/**
 * Returns a string of HTML from the spans in this [Spanned].
 *
 * @see Html.toHtml
 */
fun Spanned.toHtml(
    @SuppressLint("InlinedApi") option: Int = TO_HTML_PARAGRAPH_LINES_CONSECUTIVE
): String = HtmlCompat.toHtml(this, option)
