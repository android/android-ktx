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

package androidx.core.text

import android.text.Spanned
import android.text.SpannedString

/**
 * Returns a new [Spanned] from [CharSequence],
 * or the source itself if it is already an instance of [SpannedString].
 */
inline fun CharSequence.toSpanned(): Spanned = SpannedString.valueOf(this)

/** Get all spans that are instance of [T]. */
inline fun <reified T : Any> Spanned.getSpans(start: Int = 0, end: Int = length): Array<out T> =
    getSpans(start, end, T::class.java)
