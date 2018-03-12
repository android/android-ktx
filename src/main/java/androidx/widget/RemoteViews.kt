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

@file:Suppress("NOTHING_TO_INLINE")

// Aliases to other public API.

package androidx.widget

import android.support.annotation.ColorInt
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.IntRange
import android.widget.RemoteViews

/** Set `textSize` to 'TextView' in 'RemoteView'. */
inline fun RemoteViews.setTextViewTextSize(@IdRes viewId: Int, @DimenRes size: Float) =
    setFloat(viewId, "setTextSize", size)

/** Set `minLines` to 'TextView' in 'RemoteView'. */
inline fun RemoteViews.setTextViewMinLines(@IdRes viewId: Int, lines: Int) =
    setInt(viewId, "setMinLines", lines)

/** Set `maxLines` to 'TextView' in 'RemoteView'. */
inline fun RemoteViews.setTextViewMaxLines(@IdRes viewId: Int, lines: Int) =
    setInt(viewId, "setMaxLines", lines)

/** Set `paintFlags` to 'TextView' in 'RemoteView'. */
inline fun RemoteViews.setTextViewPaintFlags(@IdRes viewId: Int, flags: Int) =
    setInt(viewId, "setPaintFlags", flags)

/** Set `alpha` to 'ImageView' in 'RemoteView'. */
inline fun RemoteViews.setImageViewAlpha(
    @IdRes viewId: Int,
    @IntRange(from = 0, to = 255) alpha: Int
) = setInt(viewId, "setImageAlpha", alpha)

/** Set `maxWidth` to 'ImageView' in 'RemoteView'. */
inline fun RemoteViews.setImageViewMaxWidth(@IdRes viewId: Int, @DimenRes width: Int) =
    setInt(viewId, "setMaxWidth", width)

/** Set `maxHeight` to 'ImageView' in 'RemoteView'. */
inline fun RemoteViews.setImageViewMaxHeight(@IdRes viewId: Int, @DimenRes height: Int) =
    setInt(viewId, "setMaxHeight", height)

/** Set `enabled` to 'View' in 'RemoteView'. */
inline fun RemoteViews.setEnabled(@IdRes viewId: Int, enabled: Boolean) =
    setBoolean(viewId, "setEnabled", enabled)

/** Set `background` to 'View' in 'RemoteView' with color. */
inline fun RemoteViews.setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) =
    setInt(viewId, "setBackgroundColor", color)

/** Set `background` to 'View' in 'RemoteView' with drawable. */
inline fun RemoteViews.setBackgroundResource(@IdRes viewId: Int, @DrawableRes drawable: Int) =
    setInt(viewId, "setBackgroundResource", drawable)