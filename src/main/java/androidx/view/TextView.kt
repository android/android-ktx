/*
 * Copyright (C) 2017 The Android Open Source Project
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

package androidx.view

import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.widget.TextView

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawables
 */
fun TextView.updateCompoundDrawables(
        start: Drawable? = null,
        top: Drawable? = null,
        end: Drawable? = null,
        bottom: Drawable? = null
) {
    setCompoundDrawables(start, top, end, bottom)
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesWithIntrinsicBounds
 */
fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
        @DrawableRes start: Int = 0,
        @DrawableRes top: Int = 0,
        @DrawableRes end: Int = 0,
        @DrawableRes bottom: Int = 0
) {
    setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom)
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesWithIntrinsicBounds
 */
fun TextView.updateCompoundDrawablesWithIntrinsicBounds(
        start: Drawable? = null,
        top: Drawable? = null,
        end: Drawable? = null,
        bottom: Drawable? = null
) {
    setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom)
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesRelative
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.updateCompoundDrawablesRelative(
        start: Drawable? = null,
        top: Drawable? = null,
        end: Drawable? = null,
        bottom: Drawable? = null
) {
    setCompoundDrawablesRelative(start, top, end, bottom)
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesRelativeWithIntrinsicBounds
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
        @DrawableRes start: Int = 0,
        @DrawableRes top: Int = 0,
        @DrawableRes end: Int = 0,
        @DrawableRes bottom: Int = 0
) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
}

/**
 * Updates this TextView's Drawables. This version of the method allows using named parameters
 * to just set one or more Drawables.
 *
 * @see TextView.setCompoundDrawablesRelativeWithIntrinsicBounds
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
        start: Drawable? = null,
        top: Drawable? = null,
        end: Drawable? = null,
        bottom: Drawable? = null
) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
}