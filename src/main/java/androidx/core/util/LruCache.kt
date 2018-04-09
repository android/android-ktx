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

package androidx.core.util

import android.util.LruCache

/**
 * Creates an [LruCache] with the given parameters.
 *
 * @param maxSize for caches that do not specify [sizeOf], this is
 * the maximum number of entries in the cache. For all other caches,
 * this is the maximum sum of the sizes of the entries in this cache.
 * @param sizeOf function that returns the size of the entry for key and value in
 * user-defined units. The default implementation returns 1.
 * @param create a create called after a cache miss to compute a value for the corresponding key.
 * Returns the computed value or null if no value can be computed. The default implementation
 * returns null.
 * @param onEntryRemoved a function called for entries that have been evicted or removed.
 *
 * @see LruCache.sizeOf
 * @see LruCache.create
 * @see LruCache.entryRemoved
 */
inline fun <K : Any, V : Any> lruCache(
    maxSize: Int,
    crossinline sizeOf: (key: K, value: V) -> Int = { _, _ -> 1 },
    @Suppress("USELESS_CAST") // https://youtrack.jetbrains.com/issue/KT-21946
    crossinline create: (key: K) -> V? = { null as V? },
    crossinline onEntryRemoved: (evicted: Boolean, key: K, oldValue: V, newValue: V?) -> Unit =
        { _, _, _, _ -> }
): LruCache<K, V> {
    return object : LruCache<K, V>(maxSize) {
        override fun sizeOf(key: K, value: V) = sizeOf(key, value)
        override fun create(key: K) = create(key)
        override fun entryRemoved(evicted: Boolean, key: K, oldValue: V, newValue: V?) {
            onEntryRemoved(evicted, key, oldValue, newValue)
        }
    }
}
