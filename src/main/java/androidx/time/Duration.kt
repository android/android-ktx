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

package androidx.time

import android.support.annotation.RequiresApi
import java.time.Duration

/**
 * Return the seconds component of this [Duration].
 *
 * @see Duration.getSeconds
 */
@RequiresApi(26)
inline operator fun Duration.component1(): Long = seconds

/**
 * Returns the nanosecond offset of this [Duration].
 *
 * @see Duration.getNano
 */
@RequiresApi(26)
inline operator fun Duration.component2(): Int = nano

/**
 * Negate this [Duration].
 *
 * @see Duration.negated
 */
@RequiresApi(26)
inline operator fun Duration.unaryMinus(): Duration = negated()

/**
 * Multiply this [Duration] by the `multiplicand`.
 *
 * @see Duration.multipliedBy
 */
@RequiresApi(26)
inline operator fun Duration.times(multiplicand: Long): Duration = multipliedBy(multiplicand)

/**
 * Divide this [Duration] by the `divisor`.
 *
 * @see Duration.dividedBy
 */
@RequiresApi(26)
inline operator fun Duration.div(divisor: Long): Duration = dividedBy(divisor)

/**
 * Return a [Duration] representing this value in nanoseconds.
 *
 * @see Duration.ofNanos
 */
@RequiresApi(26)
inline fun Int.nanos(): Duration = Duration.ofNanos(toLong())

/**
 * Return a [Duration] representing this value in nanoseconds.
 *
 * @see Duration.ofNanos
 */
@RequiresApi(26)
inline fun Long.nanos(): Duration = Duration.ofNanos(this)

/**
 * Return a [Duration] representing this value in milliseconds.
 *
 * @see Duration.ofMillis
 */
@RequiresApi(26)
inline fun Int.millis(): Duration = Duration.ofMillis(toLong())

/**
 * Return a [Duration] representing this value in milliseconds.
 *
 * @see Duration.ofMillis
 */
@RequiresApi(26)
inline fun Long.millis(): Duration = Duration.ofMillis(this)

/**
 * Return a [Duration] representing this value in seconds.
 *
 * @see Duration.ofSeconds
 */
@RequiresApi(26)
inline fun Int.seconds(): Duration = Duration.ofSeconds(toLong())

/**
 * Return a [Duration] representing this value in seconds.
 *
 * @see Duration.ofSeconds
 */
@RequiresApi(26)
inline fun Long.seconds(): Duration = Duration.ofSeconds(this)

/**
 * Return a [Duration] representing this value in minutes.
 *
 * @see Duration.ofMinutes
 */
@RequiresApi(260)
inline fun Int.minutes(): Duration = Duration.ofMinutes(toLong())

/**
 * Return a [Duration] representing this value in minutes.
 *
 * @see Duration.ofMinutes
 */
@RequiresApi(260)
inline fun Long.minutes(): Duration = Duration.ofMinutes(this)

/**
 * Return a [Duration] representing this value in hours.
 *
 * @see Duration.ofHours
 */
@RequiresApi(260)
inline fun Int.hours(): Duration = Duration.ofHours(toLong())

/**
 * Return a [Duration] representing this value in hours.
 *
 * @see Duration.ofHours
 */
@RequiresApi(260)
inline fun Long.hours(): Duration = Duration.ofHours(this)
