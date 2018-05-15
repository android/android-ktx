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

package androidx.core.graphics

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.ImageDecoder.ImageInfo
import android.graphics.ImageDecoder.Source
import android.graphics.drawable.Drawable
import androidx.annotation.RequiresApi

/**
 * Create a Bitmap from a Source
 *
 * @see ImageDecoder.decodeBitmap
 */
@RequiresApi(28)
inline fun ImageDecoder.Source.decodeBitmap(
    crossinline action: ImageDecoder.(info: ImageInfo, source: Source) -> Unit
): Bitmap {
    return ImageDecoder.decodeBitmap(this) { decoder, info, source ->
        decoder.action(info, source)
    }
}

/**
 * Create a Drawable from a Source
 *
 * @see ImageDecoder.decodeDrawable
 */
@RequiresApi(28)
inline fun ImageDecoder.Source.decodeDrawable(
    crossinline action: ImageDecoder.(info: ImageInfo, source: Source) -> Unit
): Drawable {
    return ImageDecoder.decodeDrawable(this) { decoder, info, source ->
        decoder.action(info, source)
    }
}
