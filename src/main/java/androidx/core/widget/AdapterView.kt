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

package androidx.core.widget

import android.view.View
import android.widget.AdapterView

/**
 * Sets click listener with automatic item casting
 * (ClassCastException will be thrown if adapter's item type does not match)
 */
inline fun <T> AdapterView<*>.onItemClick(crossinline onItemClick: (item: T) -> Unit) {
    setOnItemClickListener { parent, _, position, _ ->
        @Suppress("UNCHECKED_CAST")
        onItemClick(parent.getItemAtPosition(position) as T)
    }
}

/**
 * Sets long click listener with automatic item casting
 * (ClassCastException will be thrown if adapter's item type does not match)
 */
inline fun <T> AdapterView<*>.onItemLongClick(crossinline onItemLongClick: (item: T) -> Boolean) {
    setOnItemLongClickListener { parent, _, position, _ ->
        @Suppress("UNCHECKED_CAST")
        onItemLongClick(parent.getItemAtPosition(position) as T)
    }
}

/**
 * Simple use case (default empty `onNothingSelected` set):
 * ```kotlin
 *     spinner.onItemSelected { parent, _, position, _ ->
 *         val item = parent.getItemAtPosition(position)
 *         ???
 *     }
 * ```
 * Use case with `onNothingSelected` handling:
 * ```kotlin
 *     spinner.onItemSelected(
 *         onNothingSelected = { _: AdapterView<*> -> ??? },
 *         onItemSelected = { parent, _, position, _ ->
 *             val item = parent.getItemAtPosition(position)
 *             ???
 *     })
 * ```
 * @see android.widget.AdapterView.OnItemSelectedListener
 */
inline fun AdapterView<*>.onItemSelected(
    crossinline onNothingSelected: (parent: AdapterView<*>) -> Unit = {},
    crossinline onItemSelected: (
        parent: AdapterView<*>,
        view: View?,
        position: Int,
        id: Long
    ) -> Unit
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) = onNothingSelected(parent)

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            onItemSelected(parent, view, position, id)
        }
    }
}

/**
 * Sets selection listener with automatic item casting
 * (ClassCastException will be thrown if adapter's item type does not match)
 *
 * Simple use case (default empty `onNothingSelected` set):
 * ```kotlin
 *     spinner.onItemSelected { item: T -> ??? }
 * ```
 * Use case with `onNothingSelected` handling:
 * ```kotlin
 *
 *     spinner.onItemSelected(
 *         onNothingSelected = { ??? },
 *         onItemSelected = { item: String -> ??? }
 *     )
 * ```
 * @param onNothingSelected optional action, default `{}`
 * @param onItemSelected action with casted item passed
 * @see android.widget.AdapterView.OnItemSelectedListener
 */
inline fun <T> AdapterView<*>.onItemSelected(
    crossinline onNothingSelected: () -> Unit = {},
    crossinline onItemSelected: (item: T) -> Unit
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) = onNothingSelected()

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            @Suppress("UNCHECKED_CAST")
            onItemSelected(parent.getItemAtPosition(position) as T)
        }
    }
}