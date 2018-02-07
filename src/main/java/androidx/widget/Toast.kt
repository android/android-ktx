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

package androidx.widget

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/** Displays short Toast. */
inline fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

/** Displays short Toast. */
inline fun Context.toast(@StringRes resID: Int) =
    Toast.makeText(this, resID, Toast.LENGTH_SHORT).show()

/** Displays long Toast. */
inline fun Context.toastLong(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()

/** Displays long Toast. */
inline fun Context.toastLong(@StringRes resID: Int) =
    Toast.makeText(this, resID, Toast.LENGTH_LONG).show()