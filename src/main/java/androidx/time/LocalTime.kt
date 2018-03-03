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

@file:Suppress("NOTHING_TO_INLINE") // Aliases to public API.

package androidx.time

import android.support.annotation.RequiresApi
import java.time.LocalTime

/**
 * Return the hour of this [LocalTime].
 *
 * This method allows to use destructuring declarations when working with times,
 * for example:
 * ```
 * val (hour, minute, second) = myLocalTime
 * ```
 *
 * @see LocalTime.getHour
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun LocalTime.component1(): Int = hour

/**
 * Return the minute of this [LocalTime].
 *
 * This method allows to use destructuring declarations when working with times,
 * for example:
 * ```
 * val (hour, minute, second) = myLocalTime
 * ```
 *
 * @see LocalTime.getMinute
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun LocalTime.component2(): Int = minute

/**
 * Return the second of this [LocalTime].
 *
 * This method allows to use destructuring declarations when working with times,
 * for example:
 * ```
 * val (hour, minute, second) = myLocalTime
 * ```
 *
 * @see LocalTime.getSecond
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun LocalTime.component3(): Int = second

/**
 * Return the nanosecond offset of this [LocalTime].
 *
 * This method allows to use destructuring declarations when working with times,
 * for example:
 * ```
 * val (hour, minute, second, nanoOffset) = myLocalTime
 * ```
 *
 * @see LocalTime.getNano
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun LocalTime.component4(): Int = nano
