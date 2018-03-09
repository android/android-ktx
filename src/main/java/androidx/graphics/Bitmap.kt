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
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap.CompressFormat.JPEG
import android.graphics.Bitmap.CompressFormat.PNG
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorSpace
import android.graphics.Matrix
import android.support.annotation.ColorInt
import android.support.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.io.File

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

/**
 * Creates a new bitmap, scaled from this bitmap, when possible. If the specified
 * [width] and [height] are the same as the current width and height of this bitmap,
 * this bitmap is returned and no new bitmap is created.
 *
 * @param width The new bitmap's desired width
 * @param height The new bitmap's desired height
 * @param filter `true` if the source should be filtered (`true` by default)
 *
 * @return The new scaled bitmap or the source bitmap if no scaling is required.
 */
inline fun Bitmap.scale(width: Int, height: Int, filter: Boolean = true): Bitmap {
    return Bitmap.createScaledBitmap(this, width, height, filter)
}

/**
 * Returns a mutable bitmap with the specified [width] and [height]. A config
 * can be optionally specified. If not, the default config is [Bitmap.Config.ARGB_8888].
 *
 * @param width The new bitmap's desired width
 * @param height The new bitmap's desired height
 * @param config The new bitmap's desired [config][Bitmap.Config]
 *
 * @return A new bitmap with the specified dimensions and config
 */
inline fun createBitmap(
    width: Int,
    height: Int,
    config: Bitmap.Config = Bitmap.Config.ARGB_8888
): Bitmap {
    return Bitmap.createBitmap(width, height, config)
}

/**
 * Returns a mutable bitmap with the specified [width] and [height]. The config,
 * transparency and color space can optionally be specified. They respectively
 * default to [Bitmap.Config.ARGB_8888], `true` and [sRGB][ColorSpace.Named.SRGB].
 *
 * @param width The new bitmap's desired width
 * @param height The new bitmap's desired height
 * @param config The new bitmap's desired [config][Bitmap.Config]
 * @param hasAlpha Whether the new bitmap is opaque or not
 * @param colorSpace The new bitmap's color space
 *
 * @return A new bitmap with the specified dimensions and config
 */
@RequiresApi(26)
inline fun createBitmap(
    width: Int,
    height: Int,
    config: Bitmap.Config = Bitmap.Config.ARGB_8888,
    hasAlpha: Boolean = true,
    colorSpace: ColorSpace = ColorSpace.get(ColorSpace.Named.SRGB)
): Bitmap {
    return Bitmap.createBitmap(width, height, config, hasAlpha, colorSpace)
}


/**
 * Bitmap to bytes.
 *
 * @param format The format of bitmap.
 * @param quality Hint to the compressor, 0-100. 0 meaning compress for small size, 100 meaning compress for max quality.
 * @return ByteArray
 */
fun Bitmap.toBytes(format:CompressFormat = JPEG, quality: Int=100):ByteArray
        = ByteArrayOutputStream().also { compress(format, quality, it) }.toByteArray()

/**
 * Return the clipped bitmap.
 *
 * @param x      The x coordinate of the first pixel.
 * @param y      The y coordinate of the first pixel.
 * @param width  The width.
 * @param height The height.
 * @return the clipped bitmap
 */
fun Bitmap.clip(x:Int, y:Int, width: Int, height: Int):Bitmap
        = Bitmap.createBitmap(this, x, y, width, height)

/**
 * Return the skewed bitmap.
 *
 * @param kx  The skew factor of x.
 * @param ky  The skew factor of y.
 * @param px  The x coordinate of the pivot point.
 * @param py  The y coordinate of the pivot point.
 * @return the skewed bitmap
 */
fun Bitmap.skew(kx:Float, ky:Float, px:Float = 0f, py:Float = 0f):Bitmap
        = createBitmap(this, 0, 0 , width, height, Matrix().apply { skew(kx, ky, px, py) }, true)

/**
 * Return the rotated bitmap.
 *
 * @param degrees The number of degrees.
 * @param px      The x coordinate of the pivot point.
 * @param py      The y coordinate of the pivot point.
 * @return the rotated bitmap
 */
fun Bitmap.rotate(degrees:Int, px: Float, py: Float):Bitmap
        = createBitmap(this, 0, 0, width, height, Matrix().apply { rotate(degrees, px, py) }, true)