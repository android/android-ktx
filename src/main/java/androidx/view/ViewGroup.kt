/*
 * Copyright (C) 2017 The Android Open Source Project
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

@file:Suppress("NOTHING_TO_INLINE") // Aliases to other public API.

package androidx.view

import android.view.View
import android.view.ViewGroup

/**
 * Returns the view at `index`.
 *
 * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the count.
 */
operator fun ViewGroup.get(index: Int) =
    getChildAt(index) ?: throw IndexOutOfBoundsException("Index: $index, Size: $childCount")

/** Returns `true` if `view` is found in this view group. */
inline operator fun ViewGroup.contains(view: View) = indexOfChild(view) != -1

/** Adds `view` to this view group. */
inline operator fun ViewGroup.plusAssign(view: View) = addView(view)

/** Removes `view` from this view group. */
inline operator fun ViewGroup.minusAssign(view: View) = removeView(view)

/** Returns the number of views in this view group. */
inline val ViewGroup.size get() = childCount

/** Performs the given action on each view in this view group. */
inline fun ViewGroup.forEach(action: (View) -> Unit) {
    for (index in 0 until childCount) {
        action(getChildAt(index))
    }
}

/** Performs the given action on each view in this view group, providing its sequential index. */
inline fun ViewGroup.forEachIndexed(action: (Int, View) -> Unit) {
    for (index in 0 until childCount) {
        action(index, getChildAt(index))
    }
}

/** Returns a [MutableIterator] over the views in this view group. */
fun ViewGroup.children() = object : Iterable<View> {
    override fun iterator() = object : Iterator<View> {
        private var index = 0
        override fun hasNext() = index < childCount
        override fun next() = getChildAt(index++) ?: throw IndexOutOfBoundsException()
    }
}

/**
 * Updates the margins in the ViewGroup's MarginLayoutParams.
 * This version of the method allows using named parameters to just set one or more axes.
 *
 * @see ViewGroup.MarginLayoutParams.setMargins
 */
fun ViewGroup.MarginLayoutParams.updateMargins(
        left: Int = leftMargin,
        top: Int = topMargin,
        right: Int = rightMargin,
        bottom: Int = bottomMargin) {
    setMargins(left, top, right, bottom)
}

/**
 * Updates the margins in the ViewGroup's MarginLayoutParams. This version of the method sets all
 * axes to the provided size.
 *
 * @see ViewGroup.MarginLayoutParams.setMargins
 */
fun ViewGroup.MarginLayoutParams.updateMargins(size: Int) {
    setMargins(size, size, size, size)
}
