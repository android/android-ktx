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

import android.text.Spanned
import android.text.SpannedString

/**
 * Returns a new [SpannedString] from source,
 * or the source itself if it is already an instance of [SpannedString].
 */
inline fun CharSequence.toSpanned(): Spanned = SpannedString.valueOf(this)

/** Returns `true` if [span] is found in this text. */
inline operator fun Spanned.contains(span: Any): Boolean = spans.contains(span)

/** Get all spans that are instance of [type]. */
inline fun <T> Spanned.getSpans(type: Class<T>): Array<out T> = getSpans(0, length, type)

/** Get all spans attached from this text. */
inline val Spanned.spans: Array<out Any> get() = getSpans(Any::class.java)