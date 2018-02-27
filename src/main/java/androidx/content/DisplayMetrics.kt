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

@file:Suppress("WRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET_ON_TYPE")

package androidx.content

import android.content.res.Resources.getSystem
import android.support.annotation.Px

/** Get converted pixel value of this dp. */
inline val Int.dp: Float get() = this * getSystem().displayMetrics.density

/** Get converted pixel value of this dp. */
inline val Float.dp: Float get() = this * getSystem().displayMetrics.density

/** Get converted pixel value of this sp. */
inline val Int.sp: Float get() = this * getSystem().displayMetrics.scaledDensity

/** Get converted pixel value of this sp. */
inline val Float.sp: Float get() = this * getSystem().displayMetrics.scaledDensity

/** Get converted dp value of this pixel. */
inline val @receiver:Px Int.dpValue: Float get() = this / getSystem().displayMetrics.density

/** Get converted dp value of this pixel. */
inline val @receiver:Px Float.dpValue: Float get() = this / getSystem().displayMetrics.density

/** Get converted sp value of this pixel. */
inline val @receiver:Px Int.spValue: Float get() = this / getSystem().displayMetrics.scaledDensity

/** Get converted sp value of this pixel. */
inline val @receiver:Px Float.spValue: Float get() = this / getSystem().displayMetrics.scaledDensity