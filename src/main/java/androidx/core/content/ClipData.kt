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
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri

/**
 * Returns the ClipData.Item at [index].
 *
 * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to the count.
 */
inline operator fun ClipData.get(index: Int): ClipData.Item = getItemAt(index)

/** Returns `true` if [item] is found in this ClipData. */
operator fun ClipData.contains(item: ClipData.Item): Boolean {
    @Suppress("LoopToCallChain")
    for (index in 0 until itemCount) {
        if (getItemAt(index) == item) {
            return true
        }
    }
    return false
}

/** Performs the given action on each item in this ClipData. */
inline fun ClipData.forEach(action: (item: ClipData.Item) -> Unit) {
    for (index in 0 until itemCount) {
        action(getItemAt(index))
    }
}

/** Performs the given action on each item in this ClipData, providing its sequential index. */
inline fun ClipData.forEachIndexed(action: (index: Int, item: ClipData.Item) -> Unit) {
    for (index in 0 until itemCount) {
        action(index, getItemAt(index))
    }
}

/**
 * Returns an [Iterator] over the items in this ClipData.
 * (NOTE: ClipData doesn't allow removal of items.)
 **/
operator fun ClipData.iterator() = object : Iterator<ClipData.Item> {
    private var index = 0
    override fun hasNext() = index < itemCount
    override fun next() = getItemAt(index++) ?: throw IndexOutOfBoundsException()
}

/**
 * Returns a [List] containing the results of applying the given transform function to each
 * item in this ClipData.
 */
inline fun <reified T> ClipData.map(transform: (ClipData.Item) -> T): List<T> {
    var m = mutableListOf<T>()
    forEach {
        m.add(transform(it))
    }
    return m.toList()
}

/**
 * Returns a new [ClipData] with given list of items.
 * NOTE: HtmlText ClipData not supported.
 *
 * @throws IllegalArgumentException When the list doesn't contain items of type supported
 *         by [ClipData].
*/
inline fun <reified T> clipDataOf(
    l: List<T>,
    label: String = "",
    cr: ContentResolver? = null
): ClipData = when {
    Uri::class.java.isAssignableFrom(T::class.java) ->
        if (cr == null) {
            ClipData.newRawUri(label, l[0] as Uri).apply {
                l.forEachIndexed { index, item ->
                    if (index > 0) addItem(ClipData.Item(item as Uri))
                }
            }
        } else {
            ClipData.newUri(cr, label, l[0] as Uri).apply {
                l.forEachIndexed { index, item ->
                    if (index > 0) addItem(ClipData.Item(item as Uri))
                }
            }
        }
    String::class.java.isAssignableFrom(T::class.java) ->
        ClipData.newPlainText(label, l[0] as String).apply {
            l.forEachIndexed { index, item ->
                if (index > 0) addItem(ClipData.Item(item as String))
            }
        }
    Intent::class.java.isAssignableFrom(T::class.java) ->
        ClipData.newIntent(label, l[0] as Intent).apply {
            l.forEachIndexed { index, item ->
                if (index > 0) addItem(ClipData.Item(item as Intent))
            }
        }
    else ->
        throw IllegalArgumentException("Illegal type for $label and " +
            "items: ${T::class.java.canonicalName}")
}
