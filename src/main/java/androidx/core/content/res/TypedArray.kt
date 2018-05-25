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

package androidx.core.content.res

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.AnyRes
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import androidx.annotation.StyleableRes

private fun TypedArray.checkAttribute(@StyleableRes index: Int) {
    if (!hasValue(index)) {
        throw IllegalArgumentException("Attribute not defined in set.")
    }
}

/**
 * Retrieve the boolean value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getBoolean
 */
fun TypedArray.getBooleanOrThrow(@StyleableRes index: Int): Boolean {
    checkAttribute(index)
    return getBoolean(index, false)
}

/**
 * Retrieve the color value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getColor
 */
@ColorInt
fun TypedArray.getColorOrThrow(@StyleableRes index: Int): Int {
    checkAttribute(index)
    return getColor(index, 0)
}

/**
 * Retrieve the color state list value for the attribute at [index] or throws
 * [IllegalArgumentException] if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getColorStateList
 */
fun TypedArray.getColorStateListOrThrow(@StyleableRes index: Int): ColorStateList {
    checkAttribute(index)
    return checkNotNull(getColorStateList(index)) {
        "Attribute value was not a color or color state list."
    }
}

/**
 * Retrieve the dimension value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getDimension
 */
fun TypedArray.getDimensionOrThrow(@StyleableRes index: Int): Float {
    checkAttribute(index)
    return getDimension(index, 0f)
}

/**
 * Retrieve the dimension pixel offset value for the attribute at [index] or throws
 * [IllegalArgumentException] if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getDimensionPixelOffset
 */
@Dimension
fun TypedArray.getDimensionPixelOffsetOrThrow(@StyleableRes index: Int): Int {
    checkAttribute(index)
    return getDimensionPixelOffset(index, 0)
}

/**
 * Retrieve the dimension pixel size value for the attribute at [index] or throws
 * [IllegalArgumentException] if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getDimensionPixelSize
 */
@Dimension
fun TypedArray.getDimensionPixelSizeOrThrow(@StyleableRes index: Int): Int {
    checkAttribute(index)
    return getDimensionPixelSize(index, 0)
}

/**
 * Retrieve the drawable value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getDrawable
 */
fun TypedArray.getDrawableOrThrow(@StyleableRes index: Int): Drawable {
    checkAttribute(index)
    return getDrawable(index)
}

/**
 * Retrieve the float value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getFloat
 */
fun TypedArray.getFloatOrThrow(@StyleableRes index: Int): Float {
    checkAttribute(index)
    return getFloat(index, 0f)
}

/**
 * Retrieve the font value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getFont
 */
@RequiresApi(26)
fun TypedArray.getFontOrThrow(@StyleableRes index: Int): Typeface {
    checkAttribute(index)
    return getFont(index)
}

/**
 * Retrieve the integer value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getInt
 */
fun TypedArray.getIntOrThrow(@StyleableRes index: Int): Int {
    checkAttribute(index)
    return getInt(index, 0)
}

/**
 * Retrieve the integer value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getInteger
 */
fun TypedArray.getIntegerOrThrow(@StyleableRes index: Int): Int {
    checkAttribute(index)
    return getInteger(index, 0)
}

/**
 * Retrieves the resource identifier for the attribute at [index] or throws
 * [IllegalArgumentException] if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getResourceId
 */
@AnyRes
fun TypedArray.getResourceIdOrThrow(@StyleableRes index: Int): Int {
    checkAttribute(index)
    return getResourceId(index, 0)
}

/**
 * Retrieve the string value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getString
 */
fun TypedArray.getStringOrThrow(@StyleableRes index: Int): String {
    checkAttribute(index)
    return checkNotNull(getString(index)) {
        "Attribute value could not be coerced to String."
    }
}

/**
 * Retrieve the text value for the attribute at [index] or throws [IllegalArgumentException]
 * if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getText
 */
fun TypedArray.getTextOrThrow(@StyleableRes index: Int): CharSequence {
    checkAttribute(index)
    return checkNotNull(getText(index)) {
        "Attribute value could not be coerced to CharSequence."
    }
}

/**
 * Retrieve the text array value for the attribute at [index] or throws
 * [IllegalArgumentException] if not defined.
 *
 * @see TypedArray.hasValue
 * @see TypedArray.getTextArray
 */
fun TypedArray.getTextArrayOrThrow(@StyleableRes index: Int): Array<CharSequence> {
    checkAttribute(index)
    return getTextArray(index)
}

/**
 * Executes the given [block] function on this TypedArray and then recycles it.
 *
 * @see kotlin.io.use
 */
inline fun <R> TypedArray.use(block: (TypedArray) -> R): R {
    return block(this).also {
        recycle()
    }
}
