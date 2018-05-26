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

package androidx.core.location

import android.location.Location

/**
 * Returns the latitude of this [Location].
 *
 * This method allows to use destructuring declarations when working with [Location],
 * for example:
 * ```
 * val (lat, lon) = myLocation
 * ```
 */
inline operator fun Location.component1() = this.latitude

/**
 * Returns the longitude of this [Location].
 *
 * This method allows to use destructuring declarations when working with [Location],
 * for example:
 * ```
 * val (lat, lon) = myLocation
 * ```
 */
inline operator fun Location.component2() = this.longitude
