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
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

/**
 * Return the [LocalDateTime] of this [OffsetDateTime].
 *
 * This method allows to use destructuring declarations when working with offset date/times,
 * for example:
 * ```
 * val (dateTime, offset) = myOffsetTime
 * ```
 *
 * @see OffsetDateTime.toLocalDateTime
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun OffsetDateTime.component1(): LocalDateTime = toLocalDateTime()

/**
 * Return the [ZoneOffset] of this [OffsetDateTime].
 *
 * This method allows to use destructuring declarations when working with offset date/times,
 * for example:
 * ```
 * val (dateTime, offset) = myOffsetTime
 * ```
 *
 * @see OffsetDateTime.getOffset
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun OffsetDateTime.component2(): ZoneOffset = offset
