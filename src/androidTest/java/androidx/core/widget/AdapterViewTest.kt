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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.core.view.get
import androidx.testutils.assertThrows
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.ClassCastException
import java.lang.reflect.InvocationTargetException

class AdapterViewTest {

    private val context = InstrumentationRegistry.getContext()

    private val data = listOf("KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo")
    private val arrayAdapter: ArrayAdapter<String>
        get() = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, data)
            .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

    private val listView: ListView
        get() = ListView(context).apply { adapter = arrayAdapter }
    private val spinner: Spinner
        get() = Spinner(context).apply { adapter = arrayAdapter }

    private var testItem: Any? = null
    private var testOnNothingSelectedFired = false

    @Before
    fun setup() {
        testItem = null
        testOnNothingSelectedFired = false
    }

    @Test
    fun onItemClick() {
        val adapterView = listView
        adapterView.onItemClick { item: String -> testItem = item }
        for (position in data.indices) {
            assertTrue(
                "listener not set",
                adapterView.performItemClick(null, position, AdapterView.INVALID_ROW_ID)
            )
            assertEquals(data[position], testItem)
        }
    }

    @Test
    fun onItemClickCastExceptionOnWrongClass() {
        val adapterView = listView
        adapterView.onItemClick { item: WrongClass -> testItem = item }
        assertThrows<ClassCastException> {
            adapterView.performItemClick(null, 1, AdapterView.INVALID_ROW_ID)
        }
    }


    @Test(expected = RuntimeException::class)
    fun onItemClickRuntimeExceptionWithSpinner() {
        spinner.onItemClick { _: Any? -> }
    }

    // borrowed from https://android.googlesource.com/platform/cts/+/master/tests/tests/widget/src/android/widget/cts/AdapterViewTest.java#255
    @Test
    fun onItemLongClick() {
        val adapterView = listView
        adapterView.onItemLongClick { item: String -> testItem = item; true }
        adapterView.layout(0, 0, LAYOUT_WIDTH, LAYOUT_HEIGHT)
        val position = 1
        adapterView.showContextMenuForChild(adapterView[position])
        assertEquals(data[position], testItem)
    }

    @Test(expected = ClassCastException::class)
    fun onItemLongClickCastExceptionOnWrongClass() {
        val adapterView = listView
        adapterView.onItemLongClick { item: WrongClass -> testItem = item; true }
        adapterView.layout(0, 0, LAYOUT_WIDTH, LAYOUT_HEIGHT)
        adapterView.showContextMenuForChild(adapterView[1])
    }

    @Test
    fun onItemSelected() {
        listOf(listView, spinner).forEach { adapterView ->
            adapterView.onItemSelected { parent, _, position, _ ->
                testItem = parent.getItemAtPosition(position)
            }
            for (i in data.indices) checkSelectionForPosition(adapterView, i)
        }
    }

    @Test
    fun onItemSelectedWithCast() {
        listOf(listView, spinner).forEach { adapterView ->
            adapterView.onItemSelected { item: String -> testItem = item }
            for (i in data.indices) checkSelectionForPosition(adapterView, i)
        }
    }

    @Test
    fun onItemSelectedWithCastExceptionOnWrongClass() {
        listOf(listView, spinner).forEach { adapterView ->
            adapterView.onItemSelected<WrongClass> { item -> testItem = item }
            assertThrows<ClassCastException> {
                checkSelectionForPosition(adapterView, 1)
            }
        }
    }

    @Test
    fun onItemSelectedWithHandledOnNothingSelected() {
        val adapterView = spinner
        adapterView.onItemSelected(
            onNothingSelected = { _: AdapterView<*> -> testOnNothingSelectedFired = true },
            onItemSelected = { parent, _, position, _ ->
                testItem = parent.getItemAtPosition(position)
            })
        checkSelectionForPosition(adapterView, AdapterView.INVALID_POSITION)
        for (i in data.indices) checkSelectionForPosition(adapterView, i)
        checkSelectionForPosition(adapterView, AdapterView.INVALID_POSITION)
    }

    @Test
    fun onItemSelectedWithCastWithHandledOnNothingSelected() {
        val adapterView = spinner
        adapterView.onItemSelected(
            onNothingSelected = { testOnNothingSelectedFired = true },
            onItemSelected = { item: String -> testItem = item }
        )
        checkSelectionForPosition(adapterView, AdapterView.INVALID_POSITION)
        for (i in data.indices) checkSelectionForPosition(adapterView, i)
        checkSelectionForPosition(adapterView, AdapterView.INVALID_POSITION)
    }

    class WrongClass

    private fun checkSelectionForPosition(adapterView: AdapterView<*>, position: Int) {
        assertFalse(testOnNothingSelectedFired)
        assertNull(testItem)
        adapterView.setSelection(position)
        fireOnSelected(adapterView)
        if (position < 0) {
            assertTrue(testOnNothingSelectedFired)
            assertNull(testItem)
        } else {
            assertEquals(data[position], testItem)
            assertFalse(testOnNothingSelectedFired)
        }
        testItem = null
        testOnNothingSelectedFired = false
    }

    /**
     * reflection used to test to trigger selection
     *
     * workaround for using ActivityRule like here: https://android.googlesource.com/platform/cts/+/master/tests/tests/widget/src/android/widget/cts/AdapterViewTest.java#286
     */
    private fun fireOnSelected(adapterView: AdapterView<*>) {
        try {
            AdapterView::class.java.getDeclaredMethod("fireOnSelected")
                .apply {
                    isAccessible = true
                    invoke(adapterView)
                }
        } catch (e: InvocationTargetException) {
            throw e.targetException
        }
    }

    companion object {
        private const val LAYOUT_WIDTH = 200
        private const val LAYOUT_HEIGHT = 200
    }
}