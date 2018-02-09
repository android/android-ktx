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

package androidx.os

import android.os.Build

/**
 * Executes a [block] prior to a given android sdk version [toVersion] **exclusive**
 *
 * ```
 * toApi(19, {
 *     // Do something...
 * })
 * ```
 */
inline fun toApi(toVersion: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT < toVersion) block()
}

/**
 * Executes a [block] following a given android sdk version [fromVersion] **inclusive**
 *
 * ```
 * fromApi(21, {
 *     // Do something...
 * })
 * ```
 */
inline fun fromApi(fromVersion: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= fromVersion) block()
}