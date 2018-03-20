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
import android.support.annotation.IntRange
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.widget.TextView

@DrawableRes
const val UPDATE_MODE_REMOVE = 0
@DrawableRes
const val UPDATE_MODE_KEEP = -1

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
 * - [UPDATE_MODE_REMOVE] to remove an existing drawable (ignored if no drawable exists)
 * - [UPDATE_MODE_KEEP] to keep an existing drawable
 *
 * @see TextView.setCompoundDrawablesWithIntrinsicBounds
 */
fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) left: Int = UPDATE_MODE_KEEP,
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) top: Int = UPDATE_MODE_KEEP,
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) right: Int = UPDATE_MODE_KEEP,
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) bottom: Int = UPDATE_MODE_KEEP
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
@RequiresApi(17)
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
 * - [UPDATE_MODE_REMOVE] to remove an existing drawable (ignored if no drawable exists)
 * - [UPDATE_MODE_KEEP] to keep an existing drawable
 *
 * @see TextView.setCompoundDrawablesRelativeWithIntrinsicBounds
 */
@RequiresApi(17)
fun TextView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) start: Int = UPDATE_MODE_KEEP,
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) top: Int = UPDATE_MODE_KEEP,
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) end: Int = UPDATE_MODE_KEEP,
    @DrawableRes @IntRange(from = UPDATE_MODE_KEEP.toLong()) bottom: Int = UPDATE_MODE_KEEP
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
        UPDATE_MODE_KEEP -> drawable
        UPDATE_MODE_REMOVE -> null
        else -> ContextCompat.getDrawable(context, resId)
    }
