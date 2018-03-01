/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.widget

import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.widget.TextView

private val drawableSentinel = DrawableContainer()

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawables
 */
fun TextView.updateCompoundDrawables(
    left: Drawable? = drawableSentinel,
    top: Drawable? = drawableSentinel,
    right: Drawable? = drawableSentinel,
    bottom: Drawable? = drawableSentinel
) {
    val drawables = updateDrawables(left, top, right, bottom, compoundDrawables)
    setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3])
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables. Use 0 if you do not want a Drawable there.
 *
 * @see TextView.setCompoundDrawablesWithIntrinsicBounds
 */
fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
    @DrawableRes left: Int = -1,
    @DrawableRes top: Int = -1,
    @DrawableRes right: Int = -1,
    @DrawableRes bottom: Int = -1
) {
    val compoundDrawables = compoundDrawables
    setCompoundDrawablesWithIntrinsicBounds(
        getUpdatedCompoundDrawable(left, compoundDrawables[0]),
        getUpdatedCompoundDrawable(top, compoundDrawables[1]),
        getUpdatedCompoundDrawable(right, compoundDrawables[2]),
        getUpdatedCompoundDrawable(bottom, compoundDrawables[3])
    )
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesWithIntrinsicBounds
 */
fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
    left: Drawable? = drawableSentinel,
    top: Drawable? = drawableSentinel,
    right: Drawable? = drawableSentinel,
    bottom: Drawable? = drawableSentinel
) {
    val drawables = updateDrawables(left, top, right, bottom, compoundDrawables)
    setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesRelative
 */
@RequiresApi(17)
fun TextView.updateCompoundDrawablesRelative(
    start: Drawable? = drawableSentinel,
    top: Drawable? = drawableSentinel,
    end: Drawable? = drawableSentinel,
    bottom: Drawable? = drawableSentinel
) {
    val drawables = updateDrawables(start, top, end, bottom, compoundDrawablesRelative)
    setCompoundDrawablesRelative(drawables[0], drawables[1], drawables[2], drawables[3])
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables. Use 0 if you do not want a Drawable there.
 *
 * @see TextView.setCompoundDrawablesRelativeWithIntrinsicBounds
 */
@RequiresApi(17)
fun TextView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
    @DrawableRes start: Int = -1,
    @DrawableRes top: Int = -1,
    @DrawableRes end: Int = -1,
    @DrawableRes bottom: Int = -1
) {
    val compoundDrawablesRelative = compoundDrawablesRelative
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        getUpdatedCompoundDrawable(start, compoundDrawablesRelative[0]),
        getUpdatedCompoundDrawable(top, compoundDrawablesRelative[1]),
        getUpdatedCompoundDrawable(end, compoundDrawablesRelative[2]),
        getUpdatedCompoundDrawable(bottom, compoundDrawablesRelative[3])
    )
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesRelativeWithIntrinsicBounds
 */
@RequiresApi(17)
fun TextView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
    start: Drawable? = drawableSentinel,
    top: Drawable? = drawableSentinel,
    end: Drawable? = drawableSentinel,
    bottom: Drawable? = drawableSentinel
) {
    val drawables = updateDrawables(start, top, end, bottom, compoundDrawablesRelative)
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        drawables[0], drawables[1], drawables[2], drawables[3]
    )
}

private fun updateDrawables(
    start: Drawable?,
    top: Drawable?,
    end: Drawable?,
    bottom: Drawable?,
    srcArray: Array<Drawable>
): Array<Drawable?> {
    val drawables = lazy(LazyThreadSafetyMode.NONE) { srcArray }
    return arrayOfNulls<Drawable>(srcArray.size).apply {
        this[0] = if (start === drawableSentinel) drawables.value[0] else start
        this[1] = if (top === drawableSentinel) drawables.value[1] else top
        this[2] = if (end === drawableSentinel) drawables.value[2] else end
        this[3] = if (bottom === drawableSentinel) drawables.value[3] else bottom
    }
}

private fun TextView.getUpdatedCompoundDrawable(resId: Int, drawable: Drawable?) =
    when (resId) {
        -1 -> drawable
        0 -> null
        else -> ContextCompat.getDrawable(context, resId)
    }
