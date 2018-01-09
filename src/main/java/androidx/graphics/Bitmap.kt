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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.graphics

import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.annotation.ColorInt

/**
 * Creates a new [Canvas] to draw on this bitmap and executes the specified
 * [block] on the newly created canvas. Example:
 *
 * ```
 * return Bitmap.createBitmap(…).applyCanvas {
 *    drawLine(…)
 *    translate(…)
 *    drawRect(…)
 * }
 * ```
 */
inline fun Bitmap.applyCanvas(block: Canvas.() -> Unit): Bitmap {
    val c = Canvas(this)
    c.block()
    return this
}

/**
 * Returns the value of the pixel at the specified location. The returned value
 * is a [color int][android.graphics.Color] in the sRGB color space.
 */
inline operator fun Bitmap.get(x: Int, y: Int) = getPixel(x, y)

/**
 * Writes the specified [color int][android.graphics.Color] into the bitmap
 * (assuming it is mutable) at the specified `(x, y)` coordinate. The specified
 * color is converted from sRGB to the bitmap's color space if needed.
 */
inline operator fun Bitmap.set(x: Int, y: Int, @ColorInt color: Int) = setPixel(x, y, color)
