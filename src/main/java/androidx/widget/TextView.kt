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

import android.annotation.TargetApi
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

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
    val drawables = lazy(LazyThreadSafetyMode.NONE) { compoundDrawables }
    setCompoundDrawables(
        if (left === drawableSentinel) drawables.value[0] else left,
        if (top === drawableSentinel) drawables.value[1] else top,
        if (right === drawableSentinel) drawables.value[2] else right,
        if (bottom === drawableSentinel) drawables.value[3] else bottom
    )
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables. Two special values can be used instead of regular resource IDs:
 * - 0 to remove an existing drawable (ignored if no drawable exists)
 * - -1 to keep an existing drawable
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
    val drawables = lazy(LazyThreadSafetyMode.NONE) { compoundDrawables }
    setCompoundDrawablesWithIntrinsicBounds(
        if (left === drawableSentinel) drawables.value[0] else left,
        if (top === drawableSentinel) drawables.value[1] else top,
        if (right === drawableSentinel) drawables.value[2] else right,
        if (bottom === drawableSentinel) drawables.value[3] else bottom
    )
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesRelative
 */
@TargetApi(17)
fun TextView.updateCompoundDrawablesRelative(
    start: Drawable? = drawableSentinel,
    top: Drawable? = drawableSentinel,
    end: Drawable? = drawableSentinel,
    bottom: Drawable? = drawableSentinel
) {
    val drawables = lazy(LazyThreadSafetyMode.NONE) { compoundDrawablesRelative }
    setCompoundDrawablesRelative(
        if (start === drawableSentinel) drawables.value[0] else start,
        if (top === drawableSentinel) drawables.value[1] else top,
        if (end === drawableSentinel) drawables.value[2] else end,
        if (bottom === drawableSentinel) drawables.value[3] else bottom
    )
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables. Two special values can be used instead of regular resource IDs:
 * - 0 to remove an existing drawable (ignored if no drawable exists)
 * - -1 to keep an existing drawable
 *
 * @see TextView.setCompoundDrawablesRelativeWithIntrinsicBounds
 */
@TargetApi(17)
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
@TargetApi(17)
fun TextView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
    start: Drawable? = drawableSentinel,
    top: Drawable? = drawableSentinel,
    end: Drawable? = drawableSentinel,
    bottom: Drawable? = drawableSentinel
) {
    val drawables = lazy(LazyThreadSafetyMode.NONE) { compoundDrawablesRelative }
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        if (start === drawableSentinel) drawables.value[0] else start,
        if (top === drawableSentinel) drawables.value[1] else top,
        if (end === drawableSentinel) drawables.value[2] else end,
        if (bottom === drawableSentinel) drawables.value[3] else bottom
    )
}

private fun TextView.getUpdatedCompoundDrawable(@DrawableRes resId: Int, drawable: Drawable?) =
    when (resId) {
        -1 -> drawable
        0 -> null
        else -> ContextCompat.getDrawable(context, resId)
    }
