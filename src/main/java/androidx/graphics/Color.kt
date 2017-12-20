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

package androidx.graphics

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.RequiresApi

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
 */
inline operator fun @receiver:ColorInt Int.component1() = (this shr 24) and 0xff

/**
 * Return the red component of a color int. This is equivalent to calling:
 * ```
 * Color.red(myInt)
 * ```
 */
inline operator fun @receiver:ColorInt Int.component2() = (this shr 16) and 0xff

/**
 * Return the green component of a color int. This is equivalent to calling:
 * ```
 * Color.green(myInt)
 * ```
 */
inline operator fun @receiver:ColorInt Int.component3() = (this shr 8) and 0xff

/**
 * Return the blue component of a color int. This is equivalent to calling:
 * ```
 * Color.blue(myInt)
 * ```
 */
inline operator fun @receiver:ColorInt Int.component4() = this and 0xff

/**
 * Returns the relative luminance of a color int, assuming sRGB encoding.
 * Based on the formula for relative luminance defined in WCAG 2.0,
 * W3C Recommendation 11 December 2008.
 */
@RequiresApi(26)
inline fun @receiver:ColorInt Int.luminance() = Color.luminance(this)

/**
 * Creates a new [Color] instance from a color int. The resulting color
 * is in the [sRGB][android.graphics.ColorSpace.Named#SRGB] color space.
 */
@RequiresApi(26)
inline fun @receiver:ColorInt Int.toColor() = Color.valueOf(this)
