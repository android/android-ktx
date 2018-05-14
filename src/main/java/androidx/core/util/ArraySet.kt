/*
 * Copyright (C) 2017 The Android Open Source Project
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

@file:Suppress("NOTHING_TO_INLINE") // Aliases to public API.

package androidx.core.util

import android.util.ArraySet
import androidx.annotation.RequiresApi

/** Returns an empty new [ArraySet]. */
@RequiresApi(23)
inline fun <T> arraySetOf(): ArraySet<T> = ArraySet()

/** Returns a new [ArraySet] with the specified contents. */
@RequiresApi(23)
fun <T> arraySetOf(vararg values: T): ArraySet<T> {
    val set = ArraySet<T>(values.size)
    @Suppress("LoopToCallChain") // Causes needless copy to a list.
    for (value in values) {
        set.add(value)
    }
    return set
}
