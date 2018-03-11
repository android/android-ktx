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

package androidx.core.graphics

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.PorterDuffXfermode

/**
 * Creates a new [PorterDuffXfermode] that uses this [PorterDuff.Mode] as the
 * alpha compositing or blending mode.
 */
inline fun PorterDuff.Mode.toXfermode() = PorterDuffXfermode(this)

/**
 * Creates a new [PorterDuffColorFilter] that uses this [PorterDuff.Mode] as the
 * alpha compositing or blending mode, and the specified [color].
 */
inline fun PorterDuff.Mode.toColorFilter(color: Int) = PorterDuffColorFilter(color, this)
