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

package androidx.core.content

import android.content.ClipData

/**
 * Returns the item at [index].
 *
 * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the count.
 */
operator fun ClipData.get(index: Int): ClipData.Item {
    return if (index < 0 || index >= itemCount)
        throw IndexOutOfBoundsException("Index: $index, Size: $itemCount")
    else getItemAt(index)
}

/** Returns `true` if [item] is found in this clip. */
inline operator fun ClipData.contains(item: ClipData.Item): Boolean {
    for (index in 0 until itemCount) {
        if (getItemAt(index) == item) {
            return true
        }
    }
    return false
}

/**
 * Adds [item] to this clip.
 *
 * @see ClipData.addItem
 */
inline operator fun ClipData.plusAssign(item: ClipData.Item) = addItem(item)

/** Returns the number of items in this clip data. */
inline val ClipData.size get() = itemCount

/** Performs the given action on each item in this clip data. */
inline fun ClipData.forEach(action: (item: ClipData.Item) -> Unit) {
    for (index in 0 until itemCount) {
        action(getItemAt(index))
    }
}

/** Performs the given action on each item in this clip data, providing its sequential index. */
inline fun ClipData.forEachIndexed(action: (index: Int, item: ClipData.Item) -> Unit) {
    for (index in 0 until itemCount) {
        action(index, getItemAt(index))
    }
}

/** Returns a [Iterator] over the items in this clip. */
operator fun ClipData.iterator() = object : Iterator<ClipData.Item> {
    private var index = 0
    override fun hasNext() = index < itemCount
    override fun next() = getItemAt(index++) ?: throw IndexOutOfBoundsException()
}

/** Returns a [Sequence] over the items in this clip. */
val ClipData.items: Sequence<ClipData.Item>
    get() = object : Sequence<ClipData.Item> {
        override fun iterator() = this@items.iterator()
    }
