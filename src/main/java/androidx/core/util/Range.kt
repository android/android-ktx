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

@file:Suppress("NOTHING_TO_INLINE") // Aliases to public API.

package androidx.core.util

import android.util.Range
import androidx.annotation.RequiresApi

/**
 * Creates a range from this [Comparable] value to [that].
 *
 * @throws IllegalArgumentException if this value is comparatively smaller than [that].
 */
@RequiresApi(21)
inline infix fun <T : Comparable<T>> T.rangeTo(that: T): Range<T> = Range(this, that)

/** Return the smallest range that includes this and [value]. */
@RequiresApi(21)
inline operator fun <T : Comparable<T>> Range<T>.plus(value: T): Range<T> = extend(value)

/** Return the smallest range that includes this and [other]. */
@RequiresApi(21)
inline operator fun <T : Comparable<T>> Range<T>.plus(other: Range<T>): Range<T> = extend(other)

/**
 * Return the intersection of this range and [other].
 *
 * @throws IllegalArgumentException if this is disjoint from [other].
 */
@RequiresApi(21)
inline infix fun <T : Comparable<T>> Range<T>.and(other: Range<T>): Range<T> = intersect(other)

/** Returns this [Range] as a [ClosedRange]. */
@RequiresApi(21)
fun <T : Comparable<T>> Range<T>.toClosedRange(): ClosedRange<T> = object : ClosedRange<T> {
    override val endInclusive get() = upper
    override val start get() = lower
}

/** Returns this [ClosedRange] as a [Range]. */
@RequiresApi(21)
fun <T : Comparable<T>> ClosedRange<T>.toRange(): Range<T> = Range(start, endInclusive)
