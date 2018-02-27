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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.text

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE

/**
 * Returns a new [Spannable] from [CharSequence],
 * or the source itself if it is already an instance of [SpannableString].
 */
inline fun CharSequence.toSpannable(): Spannable = SpannableString.valueOf(this)

/** Adds [span] to the entire text. */
inline operator fun Spannable.plusAssign(span: Any) =
    setSpan(span, 0, length, SPAN_INCLUSIVE_EXCLUSIVE)

/** Removes [span] from this text. */
inline operator fun Spannable.minusAssign(span: Any) = removeSpan(span)

/** Clear all spans from this text. */
inline fun Spannable.clearSpans() = getSpans<Any>().forEach { removeSpan(it) }