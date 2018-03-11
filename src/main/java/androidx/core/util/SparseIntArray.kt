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

import android.util.SparseIntArray

/** Returns the number of key/value pairs in the collection. */
inline val SparseIntArray.size get() = size()

/** Returns true if the collection contains [key]. */
inline operator fun SparseIntArray.contains(key: Int) = indexOfKey(key) >= 0

/** Allows the use of the index operator for storing values in the collection. */
inline operator fun SparseIntArray.set(key: Int, value: Int) = put(key, value)

/** Creates a new collection by adding or replacing entries from [other]. */
operator fun SparseIntArray.plus(other: SparseIntArray): SparseIntArray {
    val new = SparseIntArray(size() + other.size())
    new.putAll(this)
    new.putAll(other)
    return new
}

/** Returns true if the collection contains [key]. */
inline fun SparseIntArray.containsKey(key: Int) = indexOfKey(key) >= 0

/** Returns true if the collection contains [value]. */
inline fun SparseIntArray.containsValue(value: Int) = indexOfValue(value) != -1

/** Return the value corresponding to [key], or [defaultValue] when not present. */
inline fun SparseIntArray.getOrDefault(key: Int, defaultValue: Int) = get(key, defaultValue)

/** Return the value corresponding to [key], or from [defaultValue] when not present. */
inline fun SparseIntArray.getOrElse(key: Int, defaultValue: () -> Int) =
    indexOfKey(key).let { if (it != -1) valueAt(it) else defaultValue() }

/** Return true when the collection contains no elements. */
inline fun SparseIntArray.isEmpty() = size() == 0

/** Return true when the collection contains elements. */
inline fun SparseIntArray.isNotEmpty() = size() != 0

/** Removes the entry for [key] only if it is mapped to [value]. */
fun SparseIntArray.remove(key: Int, value: Int): Boolean {
    val index = indexOfKey(key)
    if (index != -1 && value == valueAt(index)) {
        removeAt(index)
        return true
    }
    return false
}

/** Update this collection by adding or replacing entries from [other]. */
fun SparseIntArray.putAll(other: SparseIntArray) = other.forEach(::put)

/** Performs the given [action] for each key/value entry. */
inline fun SparseIntArray.forEach(action: (key: Int, value: Int) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}

/** Return an iterator over the collection's keys. */
fun SparseIntArray.keyIterator(): IntIterator = object : IntIterator() {
    var index = 0
    override fun hasNext() = index < size()
    override fun nextInt() = keyAt(index++)
}

/** Return an iterator over the collection's values. */
fun SparseIntArray.valueIterator(): IntIterator = object : IntIterator() {
    var index = 0
    override fun hasNext() = index < size()
    override fun nextInt() = valueAt(index++)
}
