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

@file:Suppress("NOTHING_TO_INLINE", "DeprecatedCallableAddReplaceWith") // Aliases to public API.

package androidx.time

import android.support.annotation.RequiresApi
import java.time.Duration

/**
 * Return the seconds component of this [Duration].
 *
 * This method allows to use destructuring declarations when working with durations,
 * for example:
 * ```
 * val (seconds, nanoOffset) = myDuration
 * ```
 *
 * @see Duration.getSeconds
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun Duration.component1(): Long = seconds

/**
 * Returns the nanosecond offset of this [Duration].
 *
 * This method allows to use destructuring declarations when working with durations,
 * for example:
 * ```
 * val (seconds, nanoOffset) = myDuration
 * ```
 *
 * @see Duration.getNano
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun Duration.component2(): Int = nano

/**
 * Negate this [Duration].
 *
 * @see Duration.negated
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("this.negated()"))
inline operator fun Duration.unaryMinus(): Duration = negated()

/**
 * Multiply this [Duration] by the [multiplicand].
 *
 * @see Duration.multipliedBy
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("this.multipliedBy(multiplicand)"))
inline operator fun Duration.times(multiplicand: Long): Duration = multipliedBy(multiplicand)

/**
 * Divide this [Duration] by the [divisor].
 *
 * @see Duration.dividedBy
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("this.dividedBy(divisor)"))
inline operator fun Duration.div(divisor: Long): Duration = dividedBy(divisor)

/**
 * Return a [Duration] representing this value in nanoseconds.
 *
 * @see Duration.ofNanos
 */
@RequiresApi(26)
@Deprecated(
    TIME_DEPRECATION_MESSAGE,
    ReplaceWith("Duration.ofNanos(this.toLong())", "java.time.Duration")
)
inline fun Int.nanos(): Duration = Duration.ofNanos(toLong())

/**
 * Return a [Duration] representing this value in nanoseconds.
 *
 * @see Duration.ofNanos
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("Duration.ofNanos(this)", "java.time.Duration"))
inline fun Long.nanos(): Duration = Duration.ofNanos(this)

/**
 * Return a [Duration] representing this value in milliseconds.
 *
 * @see Duration.ofMillis
 */
@RequiresApi(26)
@Deprecated(
    TIME_DEPRECATION_MESSAGE,
    ReplaceWith("Duration.ofMillis(this.toLong())", "java.time.Duration")
)
inline fun Int.millis(): Duration = Duration.ofMillis(toLong())

/**
 * Return a [Duration] representing this value in milliseconds.
 *
 * @see Duration.ofMillis
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("Duration.ofMillis(this)", "java.time.Duration"))
inline fun Long.millis(): Duration = Duration.ofMillis(this)

/**
 * Return a [Duration] representing this value in seconds.
 *
 * @see Duration.ofSeconds
 */
@RequiresApi(26)
@Deprecated(
    TIME_DEPRECATION_MESSAGE,
    ReplaceWith("Duration.ofSeconds(this.toLong())", "java.time.Duration")
)
inline fun Int.seconds(): Duration = Duration.ofSeconds(toLong())

/**
 * Return a [Duration] representing this value in seconds.
 *
 * @see Duration.ofSeconds
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("Duration.ofSeconds(this)", "java.time.Duration"))
inline fun Long.seconds(): Duration = Duration.ofSeconds(this)

/**
 * Return a [Duration] representing this value in minutes.
 *
 * @see Duration.ofMinutes
 */
@RequiresApi(26)
@Deprecated(
    TIME_DEPRECATION_MESSAGE,
    ReplaceWith("Duration.ofMinutes(this.toLong())", "java.time.Duration")
)
inline fun Int.minutes(): Duration = Duration.ofMinutes(toLong())

/**
 * Return a [Duration] representing this value in minutes.
 *
 * @see Duration.ofMinutes
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("Duration.ofMinutes(this)", "java.time.Duration"))
inline fun Long.minutes(): Duration = Duration.ofMinutes(this)

/**
 * Return a [Duration] representing this value in hours.
 *
 * @see Duration.ofHours
 */
@RequiresApi(26)
@Deprecated(
    TIME_DEPRECATION_MESSAGE,
    ReplaceWith("Duration.ofHours(this.toLong())", "java.time.Duration")
)
inline fun Int.hours(): Duration = Duration.ofHours(toLong())

/**
 * Return a [Duration] representing this value in hours.
 *
 * @see Duration.ofHours
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE, ReplaceWith("Duration.ofHours(this)", "java.time.Duration"))
inline fun Long.hours(): Duration = Duration.ofHours(this)
