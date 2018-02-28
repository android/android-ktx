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

package androidx.content

import android.content.res.Resources
import android.support.annotation.Px

/** Get converted pixel value of this dp. */
inline fun Resources.toDp(dp: Int): Float = dp * displayMetrics.density

/** Get converted pixel value of this dp. */
inline fun Resources.toDp(dp: Float): Float = dp * displayMetrics.density

/** Get converted pixel value of this sp. */
inline fun Resources.toSp(sp: Int): Float = sp * displayMetrics.scaledDensity

/** Get converted pixel value of this sp. */
inline fun Resources.toSp(sp: Float): Float = sp * displayMetrics.scaledDensity

/** Get converted dp value of this pixel. */
inline fun Resources.getDp(@Px px: Int): Float = px / displayMetrics.density

/** Get converted dp value of this pixel. */
inline fun Resources.getDp(px: Float): Float = px / displayMetrics.density

/** Get converted sp value of this pixel. */
inline fun Resources.getSp(@Px px: Int): Float = px / displayMetrics.scaledDensity

/** Get converted sp value of this pixel. */
inline fun Resources.getSp(px: Float): Float = px / displayMetrics.scaledDensity