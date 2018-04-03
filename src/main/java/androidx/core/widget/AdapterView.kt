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

inline fun <ITEM> AdapterView<*>.onItemClick(
    crossinline onItemClick: (item: ITEM) -> Unit
) {
    setOnItemClickListener { _, _, position, _ ->
        @Suppress("UNCHECKED_CAST")
        onItemClick(getItemAtPosition(position) as ITEM)
    }
}

inline fun <ITEM> AdapterView<*>.onItemLongClick(
    crossinline onItemLongClick: (item: ITEM) -> Boolean
) {
    setOnItemLongClickListener { _, _, position, _ ->
        @Suppress("UNCHECKED_CAST")
        onItemLongClick(getItemAtPosition(position) as ITEM)
    }
}

inline fun AdapterView<*>.onItemSelected(
    crossinline onNothingSelected: () -> Unit = {},
    crossinline onItemSelected: (
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) -> Unit
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = onNothingSelected()

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            onItemSelected(parent, view, position, id)
        }
    }
}

inline fun <ITEM> AdapterView<*>.onItemSelected(
    crossinline onNothingSelected: () -> Unit = {},
    crossinline onItemSelected: (item: ITEM) -> Unit
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = onNothingSelected()

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            @Suppress("UNCHECKED_CAST")
            onItemSelected(getItemAtPosition(position) as ITEM)
        }
    }
}