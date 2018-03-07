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
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Return the [LocalDateTime] for this [ZonedDateTime].
 *
 * @see ZonedDateTime.toLocalDateTime
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun ZonedDateTime.component1(): LocalDateTime = toLocalDateTime()

/**
 * Return the [ZoneId] for this [ZonedDateTime].
 *
 * @see ZonedDateTime.getZone
 */
@RequiresApi(26)
@Deprecated(TIME_DEPRECATION_MESSAGE)
inline operator fun ZonedDateTime.component2(): ZoneId = zone
