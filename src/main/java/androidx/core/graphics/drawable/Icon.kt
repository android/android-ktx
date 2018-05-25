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

@file:Suppress("NOTHING_TO_INLINE") // Aliases to public API.

package androidx.core.graphics.drawable

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.annotation.RequiresApi

/**
 * Create an [Icon] from this adaptive [Bitmap].
 *
 * @see Icon.createWithAdaptiveBitmap
 */
@RequiresApi(26)
inline fun Bitmap.toAdaptiveIcon(): Icon = Icon.createWithAdaptiveBitmap(this)

/**
 * Create an [Icon] from this [Bitmap].
 *
 * @see Icon.createWithBitmap
 */
@RequiresApi(26)
inline fun Bitmap.toIcon(): Icon = Icon.createWithBitmap(this)

/**
 * Create an [Icon] from this [Uri].
 *
 * @see Icon.createWithContentUri
 */
@RequiresApi(26)
inline fun Uri.toIcon(): Icon = Icon.createWithContentUri(this)

/**
 * Create an [Icon] from this [ByteArray].
 *
 * @see Icon.createWithData
 */
@RequiresApi(26)
inline fun ByteArray.toIcon(): Icon = Icon.createWithData(this, 0, size)
