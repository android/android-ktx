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
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.widget.TextView

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawables
 */
fun TextView.updateCompoundDrawables(
    start: Drawable? = compoundDrawables[0],
    top: Drawable? = compoundDrawables[1],
    end: Drawable? = compoundDrawables[2],
    bottom: Drawable? = compoundDrawables[3]
) {
    setCompoundDrawables(start, top, end, bottom)
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables. Use 0 if you do not want a Drawable there.
 *
 * @see TextView.setCompoundDrawablesWithIntrinsicBounds
 */
fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
    @DrawableRes start: Int = -1,
    @DrawableRes top: Int = -1,
    @DrawableRes end: Int = -1,
    @DrawableRes bottom: Int = -1
) {
    setCompoundDrawablesWithIntrinsicBounds(
        getUpdatedCompoundDrawable(start, 0),
        getUpdatedCompoundDrawable(top, 1),
        getUpdatedCompoundDrawable(end, 2),
        getUpdatedCompoundDrawable(bottom, 3)
    )
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesWithIntrinsicBounds
 */
fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
    start: Drawable? = compoundDrawables[0],
    top: Drawable? = compoundDrawables[1],
    end: Drawable? = compoundDrawables[2],
    bottom: Drawable? = compoundDrawables[3]
) {
    setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom)
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesRelative
 */
@RequiresApi(17)
fun TextView.updateCompoundDrawablesRelative(
    start: Drawable? = compoundDrawables[0],
    top: Drawable? = compoundDrawables[1],
    end: Drawable? = compoundDrawables[2],
    bottom: Drawable? = compoundDrawables[3]
) {
    setCompoundDrawablesRelative(start, top, end, bottom)
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
    updateCompoundDrawablesRelativeWithIntrinsicBounds(
        getUpdatedCompoundDrawable(start, 0),
        getUpdatedCompoundDrawable(top, 1),
        getUpdatedCompoundDrawable(end, 2),
        getUpdatedCompoundDrawable(bottom, 3)
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
    start: Drawable? = compoundDrawables[0],
    top: Drawable? = compoundDrawables[1],
    end: Drawable? = compoundDrawables[2],
    bottom: Drawable? = compoundDrawables[3]
) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
}

private fun TextView.getUpdatedCompoundDrawable(resId: Int, index: Int) =
    when (resId) {
        -1 -> compoundDrawables[index]
        0 -> null
        else -> ContextCompat.getDrawable(context, resId)
    }
