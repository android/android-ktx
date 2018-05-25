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

@file:Suppress("NOTHING_TO_INLINE", "WRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET_ON_TYPE")

package androidx.core.graphics

import android.graphics.Color
import android.graphics.ColorSpace
import androidx.annotation.ColorInt
import androidx.annotation.ColorLong
import androidx.annotation.RequiresApi

/**
 * Returns the first component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the first component
 * is "red".
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue) = myColor
 * ```
 */
@RequiresApi(26)
inline operator fun Color.component1() = getComponent(0)

/**
 * Returns the second component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the second component
 * is "green".
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue) = myColor
 * ```
 */
@RequiresApi(26)
inline operator fun Color.component2() = getComponent(1)

/**
 * Returns the third component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the third component
 * is "blue".
= *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue) = myColor
 * ```
 */
@RequiresApi(26)
inline operator fun Color.component3() = getComponent(2)

/**
 * Returns the fourth component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the fourth component
 * is "alpha".
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue, alpha) = myColor
 * ```
 */
@RequiresApi(26)
inline operator fun Color.component4() = getComponent(3)

/**
 * Composites two translucent colors together. More specifically, adds two colors using
 * the [source over][android.graphics.PorterDuff.Mode.SRC_OVER] blending mode. The colors
 * must not be pre-multiplied and the result is a non pre-multiplied color.
 *
 * If the two colors have different color spaces, the color in the right-hand part
 * of the expression is converted to the color space of the color in left-hand part
 * of the expression.
 *
 * The following example creates a purple color by blending opaque blue with
 * semi-translucent red:
 *
 * ```
 * val purple = Color.valueOf(0f, 0f, 1f) + Color.valueOf(1f, 0f, 0f, 0.5f)
 * ```
 *
 * @throws IllegalArgumentException if the [color models][android.graphics.Color.getModel]
 *                                  of the colors do not match
 */
@RequiresApi(26)
operator fun Color.plus(c: Color): Color = ColorUtils.compositeColors(c, this)

/**
 * Return the alpha component of a color int. This is equivalent to calling:
 * ```
 * Color.alpha(myInt)
 * ```
 */
inline val @receiver:ColorInt Int.alpha get() = (this shr 24) and 0xff

/**
 * Return the red component of a color int. This is equivalent to calling:
 * ```
 * Color.red(myInt)
 * ```
 */
inline val @receiver:ColorInt Int.red get() = (this shr 16) and 0xff

/**
 * Return the green component of a color int. This is equivalent to calling:
 * ```
 * Color.green(myInt)
 * ```
 */
inline val @receiver:ColorInt Int.green get() = (this shr 8) and 0xff

/**
 * Return the blue component of a color int. This is equivalent to calling:
 * ```
 * Color.blue(myInt)
 * ```
 */
inline val @receiver:ColorInt Int.blue get() = this and 0xff

/**
 * Return the alpha component of a color int. This is equivalent to calling:
 * ```
 * Color.alpha(myInt)
 * ```
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (alpha, red, green, blue) = myColor
 * ```
 */
inline operator fun @receiver:ColorInt Int.component1() = (this shr 24) and 0xff

/**
 * Return the red component of a color int. This is equivalent to calling:
 * ```
 * Color.red(myInt)
 * ```
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (alpha, red, green, blue) = myColor
 * ```
 */
inline operator fun @receiver:ColorInt Int.component2() = (this shr 16) and 0xff

/**
 * Return the green component of a color int. This is equivalent to calling:
 * ```
 * Color.green(myInt)
 * ```
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (alpha, red, green, blue) = myColor
 * ```
 */
inline operator fun @receiver:ColorInt Int.component3() = (this shr 8) and 0xff

/**
 * Return the blue component of a color int. This is equivalent to calling:
 * ```
 * Color.blue(myInt)
 * ```
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (alpha, red, green, blue) = myColor
 * ```
 */
inline operator fun @receiver:ColorInt Int.component4() = this and 0xff

/**
 * Returns the relative luminance of a color int, assuming sRGB encoding.
 * Based on the formula for relative luminance defined in WCAG 2.0,
 * W3C Recommendation 11 December 2008.
 */
@get:RequiresApi(26)
inline val @receiver:ColorInt Int.luminance get() = Color.luminance(this)

/**
 * Creates a new [Color] instance from a color int. The resulting color
 * is in the [sRGB][android.graphics.ColorSpace.Named.SRGB] color space.
 */
@RequiresApi(26)
inline fun @receiver:ColorInt Int.toColor(): Color = Color.valueOf(this)

/**
 * Converts the specified ARGB [color int][Color] to an RGBA [color long][Color]
 * in the [sRGB][android.graphics.ColorSpace.Named.SRGB] color space.
 */
@RequiresApi(26)
@ColorLong
inline fun @receiver:ColorInt Int.toColorLong() = Color.pack(this)

/**
 * Returns the first component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the first component
 * is "red".
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue, alpha) = myColorLong
 * ```
 */
@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component1() = Color.red(this)

/**
 * Returns the second component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the second component
 * is "green".
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue, alpha) = myColorLong
 * ```
 */
@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component2() = Color.green(this)

/**
 * Returns the third component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the third component
 * is "blue".
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue, alpha) = myColorLong
 * ```
 */
@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component3() = Color.blue(this)

/**
 * Returns the fourth component of the color. For instance, when the color model
 * of the color is [android.graphics.ColorSpace.Model.RGB], the fourth component
 * is "alpha".
 *
 * This method allows to use destructuring declarations when working with colors,
 * for example:
 * ```
 * val (red, green, blue, alpha) = myColorLong
 * ```
 */
@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component4() = Color.alpha(this)

/**
 * Return the alpha component of a color long. This is equivalent to calling:
 * ```
 * Color.alpha(myLong)
 * ```
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.alpha get() = Color.alpha(this)

/**
 * Return the red component of a color long. This is equivalent to calling:
 * ```
 * Color.red(myLong)
 * ```
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.red get() = Color.red(this)

/**
 * Return the green component of a color long. This is equivalent to calling:
 * ```
 * Color.green(myLong)
 * ```
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.green get() = Color.green(this)

/**
 * Return the blue component of a color long. This is equivalent to calling:
 * ```
 * Color.blue(myLong)
 * ```
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.blue get() = Color.blue(this)

/**
 * Returns the relative luminance of a color. Based on the formula for
 * relative luminance defined in WCAG 2.0, W3C Recommendation 11 December 2008.
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.luminance get() = Color.luminance(this)

/**
 * Creates a new [Color] instance from a [color long][Color].
 */
@RequiresApi(26)
inline fun @receiver:ColorLong Long.toColor(): Color = Color.valueOf(this)

/**
 * Converts the specified [color long][Color] to an ARGB [color int][Color].
 */
@RequiresApi(26)
@ColorInt
inline fun @receiver:ColorLong Long.toColorInt() = Color.toArgb(this)

/**
 * Indicates whether the color is in the [sRGB][android.graphics.ColorSpace.Named.SRGB]
 * color space.
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.isSrgb get() = Color.isSrgb(this)

/**
 * Indicates whether the color is in a [wide-gamut][android.graphics.ColorSpace] color space.
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.isWideGamut get() = Color.isWideGamut(this)

/**
 * Returns the color space encoded in the specified color long.
 */
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.colorSpace: ColorSpace get() = Color.colorSpace(this)

/**
 * Return a corresponding [Int] color of this [String].
 *
 * Supported formats are:
 * ```
 * #RRGGBB
 * #AARRGGBB
 * ```
 *
 * The following names are also accepted: "red", "blue", "green", "black", "white",
 * "gray", "cyan", "magenta", "yellow", "lightgray", "darkgray",
 * "grey", "lightgrey", "darkgrey", "aqua", "fuchsia", "lime",
 * "maroon", "navy", "olive", "purple", "silver", "teal".
 *
 * @throws IllegalArgumentException if this [String] cannot be parsed.
 */
@ColorInt
inline fun String.toColorInt(): Int = Color.parseColor(this)
