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

import android.support.test.InstrumentationRegistry
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.view.get
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.ClassCastException

class AdapterViewTest {

    private val context = InstrumentationRegistry.getContext()
    private lateinit var adapterView: AdapterView<*>
    private lateinit var adapter: ArrayAdapter<String>
    private var itemUnderTest: String? = null

    @Before
    fun setup() {
        adapterView = ListView(context)
        adapter = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            listOf(K, L, M, N, O)
        )
        adapterView.adapter = adapter
        itemUnderTest = null
    }

    /**
     * test that ext call is equal to call
     * adapterView.setOnItemClickListener { _, _, position, _ ->
     *      itemUnderTest = adapterView.getItemAtPosition(position) as String
     * }
     */
    @Test
    fun onItemClick() {
        adapterView.onItemClick { item: String -> itemUnderTest = item }

        assertTrue("listener not set", adapterView.performItemClick(null, 1, -1))
        assertEquals(L, itemUnderTest)
    }

    @Test(expected = ClassCastException::class)
    fun onItemClickCastExceptionOnWrongClass() {
        @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
        var any: Any? = null
        adapterView.onItemClick { item: WrongClass -> any = item }
        adapterView.performItemClick(null, 1, -1)
    }

    /**
     * test that ext call is  equal to call
     * adapterView.setOnItemLongClickListener { _, _, position, _ ->
     *      itemUnderTest = adapterView.getItemAtPosition(position) as String
     *      true
     * }
     */
    @Test
    fun onItemLongClick() {
        adapterView.onItemLongClick { item: String -> itemUnderTest = item; true }
        adapterView.layout(0, 0, LAYOUT_WIDTH, LAYOUT_HEIGHT)
        adapterView.showContextMenuForChild(adapterView[1])
        assertEquals(L, itemUnderTest)
    }

    @Test(expected = ClassCastException::class)
    fun onItemLongClickCastExceptionOnWrongClass() {
        @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
        var any: Any? = null
        adapterView.onItemLongClick { item: WrongClass -> any = item; true }
        adapterView.layout(0, 0, LAYOUT_WIDTH, LAYOUT_HEIGHT)
        adapterView.showContextMenuForChild(adapterView[1])
    }

    @Test()
    fun onItemSelected() {
        TODO()
    }

    @Test()
    fun onItemSelectedWithCast() {
        TODO()
    }

    @Test()
    fun onItemSelectedCastExceptionOnWrongClass() {
        TODO()
    }

    class WrongClass

    data class OnSelectedActionMock(
        val parent: AdapterView<*>?,
        val view: View?,
        val position: Int,
        val id: Long
    )

    companion object {
        private const val K = "KitKat"
        private const val L = "Lollipop"
        private const val M = "Marshmallow"
        private const val N = "Nougat"
        private const val O = "Oreo"
        private const val LAYOUT_WIDTH = 200
        private const val LAYOUT_HEIGHT = 200
    }
}