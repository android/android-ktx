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

package androidx.text

import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import android.support.annotation.ColorInt
import android.text.Spannable.SPAN_INCLUSIVE_EXCLUSIVE
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan

/**
 * Builds new string by populating a newly created [SpannableStringBuilder] using the provided
 * `builderAction` and then converting it to [SpannedString].
 */
inline fun buildSpannedString(builderAction: SpannableStringBuilder.() -> Unit): SpannedString {
    val builder = SpannableStringBuilder()
    builder.builderAction()
    return SpannedString(builder)
}

/**
 * Wrap appended text in `builderAction` in `spans`.
 *
 * Note: the spans will only have the correct position if the `builderAction` only appends or
 * replaces text. Inserting, deleting, or clearing the text will cause the span to be placed at
 * an incorrect position.
 */
inline fun SpannableStringBuilder.inSpans(
    vararg spans: Any,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder {
    val start = length
    builderAction()
    for(span in spans) setSpan(span, start, length, SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * Wrap appended text in `builderAction` in a bold [StyleSpan].
 *
 * @see SpannableStringBuilder.inSpans
 */
inline fun SpannableStringBuilder.bold(builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(StyleSpan(BOLD), builderAction = builderAction)

/**
 * Wrap appended text in `builderAction` in an italic [StyleSpan].
 *
 * @see SpannableStringBuilder.inSpans
 */
inline fun SpannableStringBuilder.italic(builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(StyleSpan(ITALIC), builderAction = builderAction)

/**
 * Wrap appended text in `builderAction` in an [UnderlineSpan].
 *
 * @see SpannableStringBuilder.inSpans
 */
inline fun SpannableStringBuilder.underline(builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(UnderlineSpan(),builderAction = builderAction)

/**
 * Wrap appended text in `builderAction` in a [ForegroundColorSpan].
 *
 * @see SpannableStringBuilder.inSpans
 */
inline fun SpannableStringBuilder.color(
    @ColorInt color: Int,
    builderAction: SpannableStringBuilder.() -> Unit
) = inSpans(ForegroundColorSpan(color), builderAction = builderAction)

/**
 * Wrap appended text in `builderAction` in a [BackgroundColorSpan].
 *
 * @see SpannableStringBuilder.inSpans
 */
inline fun SpannableStringBuilder.backgroundColor(
    @ColorInt color: Int,
    builderAction: SpannableStringBuilder.() -> Unit
) = inSpans(BackgroundColorSpan(color), builderAction = builderAction)
