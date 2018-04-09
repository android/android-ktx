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

package androidx.core.util

import android.util.Pair

/**
 * Returns the first component of the pair.
 *
 * This method allows to use destructuring declarations when working with pairs, for example:
 * ```
 * val (first, second) = myPair
 * ```
 */
@Suppress("HasPlatformType") // Intentionally propagating platform type with unknown nullability.
inline operator fun <F, S> Pair<F, S>.component1() = first

/**
 * Returns the second component of the pair.
 *
 * This method allows to use destructuring declarations when working with pairs, for example:
 * ```
 * val (first, second) = myPair
 * ```
 */
@Suppress("HasPlatformType") // Intentionally propagating platform type with unknown nullability.
inline operator fun <F, S> Pair<F, S>.component2() = second

/** Returns this [Pair] as a [kotlin.Pair]. */
inline fun <F, S> Pair<F, S>.toKotlinPair() = kotlin.Pair(first, second)

/** Returns this [kotlin.Pair] as an Android [Pair]. */
// Note: the return type is explicitly specified here to prevent always seeing platform types.
inline fun <F, S> kotlin.Pair<F, S>.toAndroidPair(): Pair<F, S> = Pair(first, second)
