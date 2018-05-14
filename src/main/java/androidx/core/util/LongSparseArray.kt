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

import android.util.LongSparseArray
import androidx.annotation.RequiresApi

/** Returns the number of key/value pairs in the collection. */
@get:RequiresApi(16)
inline val <T> LongSparseArray<T>.size get() = size()

/** Returns true if the collection contains [key]. */
@RequiresApi(16)
inline operator fun <T> LongSparseArray<T>.contains(key: Long) = indexOfKey(key) >= 0

/** Allows the use of the index operator for storing values in the collection. */
@RequiresApi(16)
inline operator fun <T> LongSparseArray<T>.set(key: Long, value: T) = put(key, value)

/** Creates a new collection by adding or replacing entries from [other]. */
@RequiresApi(16)
operator fun <T> LongSparseArray<T>.plus(other: LongSparseArray<T>): LongSparseArray<T> {
    val new = LongSparseArray<T>(size() + other.size())
    new.putAll(this)
    new.putAll(other)
    return new
}

/** Returns true if the collection contains [key]. */
@RequiresApi(16)
inline fun <T> LongSparseArray<T>.containsKey(key: Long) = indexOfKey(key) >= 0

/** Returns true if the collection contains [value]. */
@RequiresApi(16)
inline fun <T> LongSparseArray<T>.containsValue(value: T) = indexOfValue(value) != -1

/** Return the value corresponding to [key], or [defaultValue] when not present. */
@RequiresApi(16)
inline fun <T> LongSparseArray<T>.getOrDefault(key: Long, defaultValue: T) =
    get(key) ?: defaultValue

/** Return the value corresponding to [key], or from [defaultValue] when not present. */
@RequiresApi(16)
inline fun <T> LongSparseArray<T>.getOrElse(key: Long, defaultValue: () -> T) =
    get(key) ?: defaultValue()

/** Return true when the collection contains no elements. */
@RequiresApi(16)
inline fun <T> LongSparseArray<T>.isEmpty() = size() == 0

/** Return true when the collection contains elements. */
@RequiresApi(16)
inline fun <T> LongSparseArray<T>.isNotEmpty() = size() != 0

/** Removes the entry for [key] only if it is mapped to [value]. */
@RequiresApi(16)
fun <T> LongSparseArray<T>.remove(key: Long, value: T): Boolean {
    val index = indexOfKey(key)
    if (index != -1 && value == valueAt(index)) {
        removeAt(index)
        return true
    }
    return false
}

/** Update this collection by adding or replacing entries from [other]. */
@RequiresApi(16)
fun <T> LongSparseArray<T>.putAll(other: LongSparseArray<T>) = other.forEach(::put)

/** Performs the given [action] for each key/value entry. */
@RequiresApi(16)
inline fun <T> LongSparseArray<T>.forEach(action: (key: Long, value: T) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}

/** Return an iterator over the collection's keys. */
@RequiresApi(16)
fun <T> LongSparseArray<T>.keyIterator(): LongIterator = object : LongIterator() {
    var index = 0
    override fun hasNext() = index < size()
    override fun nextLong() = keyAt(index++)
}

/** Return an iterator over the collection's values. */
@RequiresApi(16)
fun <T> LongSparseArray<T>.valueIterator(): Iterator<T> = object : Iterator<T> {
    var index = 0
    override fun hasNext() = index < size()
    override fun next() = valueAt(index++)
}
