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

import android.util.ArrayMap
import androidx.annotation.RequiresApi
import kotlin.Pair

/** Returns an empty new [ArrayMap]. */
@RequiresApi(19)
inline fun <K, V> arrayMapOf(): ArrayMap<K, V> = ArrayMap()

/**
 * Returns a new [ArrayMap] with the specified contents, given as a list of pairs where the first
 * component is the key and the second component is the value.
 *
 * If multiple pairs have the same key, the resulting map will contain the value from the last of
 * those pairs.
 */
@RequiresApi(19)
fun <K, V> arrayMapOf(vararg pairs: Pair<K, V>): ArrayMap<K, V> {
    val map = ArrayMap<K, V>(pairs.size)
    for (pair in pairs) {
        map[pair.first] = pair.second
    }
    return map
}
