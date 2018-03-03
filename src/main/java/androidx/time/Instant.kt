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
import java.time.Instant

/**
 * Return the seconds of this [Instant].
 *
 * This method allows to use destructuring declarations when working with instants,
 * for example:
 * ```
 * val (seconds, nanoOffset) = myInstant
 * ```
 *
 * @see Instant.getEpochSecond
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun Instant.component1(): Long = epochSecond

/**
 * Return the nanosecond offset of this [Instant].
 *
 * This method allows to use destructuring declarations when working with instants,
 * for example:
 * ```
 * val (seconds, nanoOffset) = myInstant
 * ```
 *
 * @see Instant.getNano
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun Instant.component2(): Int = nano

/**
 * Return an [Instant] for the number of seconds after the epoch.
 *
 * @see Instant.ofEpochSecond
 */
@RequiresApi(26)
@Deprecated(
    TIME_DEPRECATION_MESSAGE,
    ReplaceWith("Instant.ofEpochSecond(this)", "java.time.Instant")
)
inline fun Long.asEpochSeconds(): Instant = Instant.ofEpochSecond(this)

/**
 * Return an [Instant] for the number of milliseconds after the epoch.
 *
 * @see Instant.ofEpochMilli
 */
@RequiresApi(26)
@Deprecated(
    TIME_DEPRECATION_MESSAGE,
    ReplaceWith("Instant.ofEpochMilli(this)", "java.time.Instant")
)
inline fun Long.asEpochMillis(): Instant = Instant.ofEpochMilli(this)
