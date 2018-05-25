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

@file:Suppress("NOTHING_TO_INLINE", "WRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET_ON_TYPE")

package androidx.core.graphics.drawable

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi

/** Create a [ColorDrawable] from this color value. */
inline fun @receiver:ColorInt Int.toDrawable() = ColorDrawable(this)

/** Create a [ColorDrawable] from this [Color] (via [Color.toArgb]). */
@RequiresApi(26)
inline fun Color.toDrawable() = ColorDrawable(toArgb())
