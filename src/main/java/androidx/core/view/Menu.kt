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

package androidx.core.view

import android.view.Menu
import android.view.MenuItem

/**
 * Returns the menu at [index].
 *
 * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the count.
 */
inline operator fun Menu.get(index: Int): MenuItem = getItem(index)

/** Returns `true` if [item] is found in this menu. */
operator fun Menu.contains(item: MenuItem): Boolean {
    @Suppress("LoopToCallChain")
    for (index in 0 until size()) {
        if (getItem(index) == item) {
            return true
        }
    }
    return false
}

/** Removes [item] from this menu. */
inline operator fun Menu.minusAssign(item: MenuItem) = removeItem(item.itemId)

/** Returns the number of items in this menu. */
inline val Menu.size get() = size()

/** Returns true if this menu contains no items. */
inline fun Menu.isEmpty() = size() == 0

/** Returns true if this menu contains one or more items. */
inline fun Menu.isNotEmpty() = size() != 0

/** Performs the given action on each item in this menu. */
inline fun Menu.forEach(action: (item: MenuItem) -> Unit) {
    for (index in 0 until size()) {
        action(getItem(index))
    }
}

/** Performs the given action on each item in this menu, providing its sequential index. */
inline fun Menu.forEachIndexed(action: (index: Int, item: MenuItem) -> Unit) {
    for (index in 0 until size()) {
        action(index, getItem(index))
    }
}

/** Returns a [MutableIterator] over the items in this menu. */
operator fun Menu.iterator() = object : MutableIterator<MenuItem> {
    private var index = 0
    override fun hasNext() = index < size()
    override fun next() = getItem(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() = removeItem(--index)
}

/** Returns a [Sequence] over the items in this menu. */
val Menu.children: Sequence<MenuItem>
    get() = object : Sequence<MenuItem> {
        override fun iterator() = this@children.iterator()
    }
