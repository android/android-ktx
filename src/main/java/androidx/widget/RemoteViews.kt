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
import android.widget.RemoteViews


inline fun RemoteViews.setViewEnabled(@IdRes viewId: Int, enabled: Boolean) =
    setBoolean(viewId, "setEnabled", enabled)

inline fun RemoteViews.setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) =
    setInt(viewId, "setBackgroundColor", color)

inline fun RemoteViews.setBackgroundResource(@IdRes viewId: Int, @DrawableRes drawable: Int) =
    setInt(viewId, "setBackgroundResource", drawable)

inline fun RemoteViews.setImageViewAlpha(@IdRes viewId: Int, @DimenRes alpha: Int) =
    setInt(viewId, "setImageAlpha", alpha)

inline fun RemoteViews.setImageViewMaxWidth(@IdRes viewId: Int, @DimenRes width: Int) =
    setInt(viewId, "setMaxWidth", width)

inline fun RemoteViews.setImageViewMaxHeight(@IdRes viewId: Int, @DimenRes height: Int) =
    setInt(viewId, "setMaxHeight", height)

inline fun RemoteViews.setTextViewTextSize(@IdRes viewId: Int, @DimenRes size: Float) =
    setFloat(viewId, "setTextSize", size)

inline fun RemoteViews.setTextViewMinLines(@IdRes viewId: Int, @DimenRes lines: Int) =
    setInt(viewId, "setMinLines", lines)

inline fun RemoteViews.setTextViewMaxLines(@IdRes viewId: Int, @DimenRes lines: Int) =
    setInt(viewId, "setMaxLines", lines)

inline fun RemoteViews.setTextViewPaintFlags(@IdRes viewId: Int, flags: Int) =
    setInt(viewId, "setPaintFlags", flags)

inline fun RemoteViews.setProgressBarIndeterminate(@IdRes viewId: Int, indeterminate: Boolean) =
    setBoolean(viewId, "setIndeterminate", indeterminate)

inline fun RemoteViews.setProgressBarSecondaryProgress(@IdRes viewId: Int, secondaryProgress: Int) =
    setInt(viewId, "setSecondaryProgress", secondaryProgress)

inline fun RemoteViews.setProgressBarProgress(@IdRes viewId: Int, progress: Int) =
    setInt(viewId, "setProgress", progress)
