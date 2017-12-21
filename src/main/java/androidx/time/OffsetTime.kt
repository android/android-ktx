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
import java.time.OffsetTime
import java.time.ZoneOffset

/**
 * Return the [LocalTime] of this [OffsetTime].
 *
 * @see OffsetTime.toLocalTime
 */
@RequiresApi(26)
inline operator fun OffsetTime.component1(): LocalTime = toLocalTime()

/**
 * Return the [ZoneOffset] of this [OffsetTime].
 *
 * @see OffsetTime.getOffset
 */
@RequiresApi(26)
inline operator fun OffsetTime.component2(): ZoneOffset = offset
