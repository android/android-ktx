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
import android.widget.AbsListView.CHOICE_MODE_SINGLE
import android.widget.AdapterView
import android.widget.AdapterView.INVALID_POSITION
import android.widget.AdapterView.INVALID_ROW_ID
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
        get() = ListView(context).apply { adapter = arrayAdapter; choiceMode = CHOICE_MODE_SINGLE }
    private val spinner: Spinner
        get() = Spinner(context).apply { adapter = arrayAdapter }

    private var testItem: Any? = null
    private var testOnNothingSelectedTriggered = false

    @Before
    fun setup() {
        testItem = null
        testOnNothingSelectedTriggered = false
    }

    @Test
    fun onItemClick() {
        val adapterView = listView
        adapterView.onItemClick { item: String -> testItem = item }
        for (position in data.indices) {
            assertTrue(
                "listener not set",
                adapterView.performItemClick(null, position, INVALID_ROW_ID)
            )
            assertEquals(data[position], testItem)
        }
    }

    @Test(expected = ClassCastException::class)
    fun onItemClickCastExceptionOnWrongClass() {
        val adapterView = listView
        adapterView.onItemClick { item: WrongClass -> testItem = item }
        adapterView.performItemClick(null, 1, INVALID_ROW_ID)
    }


    @Test(expected = RuntimeException::class)
    fun onItemClickRuntimeExceptionWithSpinner() {
        spinner.onItemClick { _: Any? -> }
    }

    /**
     * borrowed from [AdapterViewTest line:279](https://android.googlesource.com/platform/cts/+/42fbcbb2518ea10cc729c44614a93b182bf58696/tests/tests/widget/src/android/widget/cts/AdapterViewTest.java#279)
     */
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
    fun onItemSelectedWithCastIgnoresOnNothingSelectedActions() {
        listOf(listView, spinner).forEach { adapterView ->
            adapterView.onItemSelected { item: String -> testItem = item }
            assertFalse(testOnNothingSelectedTriggered)
            assertNull(testItem)
            selectAndFireOnSelected(adapterView, INVALID_POSITION)
            assertFalse(testOnNothingSelectedTriggered)
            assertNull(testItem)
        }
    }

    @Test
    fun onItemSelectedWithCastExceptionOnWrongClass() {
        listOf(listView, spinner).forEach { adapterView ->
            adapterView.onItemSelected<WrongClass> { item -> testItem = item }
            assertThrows<ClassCastException> {
                for (i in data.indices) checkSelectionForPosition(adapterView, i)
            }
        }
    }

    @Test
    fun onItemSelectedWithHandledOnNothingSelected() {
        listOf(listView, spinner).forEach { adapterView ->
            adapterView.onItemSelected(
                onNothingSelected = { _: AdapterView<*> -> testOnNothingSelectedTriggered = true },
                onItemSelected = { parent, _, position, _ ->
                    testItem = parent.getItemAtPosition(position)
                })
            checkSelectionForPosition(adapterView, INVALID_POSITION)
            for (i in data.indices) checkSelectionForPosition(adapterView, i)
            checkSelectionForPosition(adapterView, INVALID_POSITION)
        }
    }

    @Test
    fun onItemSelectedWithCastWithHandledOnNothingSelected() {
        listOf(listView, spinner).forEach { adapterView ->
            adapterView.onItemSelected(
                onNothingSelected = { testOnNothingSelectedTriggered = true },
                onItemSelected = { item: String -> testItem = item }
            )
            checkSelectionForPosition(adapterView, INVALID_POSITION)
            for (i in data.indices) checkSelectionForPosition(adapterView, i)
            checkSelectionForPosition(adapterView, INVALID_POSITION)
        }
    }

    private fun checkSelectionForPosition(adapterView: AdapterView<*>, position: Int) {
        assertFalse(testOnNothingSelectedTriggered)
        assertNull(testItem)
        selectAndFireOnSelected(adapterView, position)
        if (position < 0) {
            assertTrue(testOnNothingSelectedTriggered)
            assertNull(testItem)
        } else {
            assertFalse(testOnNothingSelectedTriggered)
            assertEquals(data[position], testItem)
        }
        testOnNothingSelectedTriggered = false
        testItem = null
    }

    companion object {
        private const val LAYOUT_WIDTH = 200
        private const val LAYOUT_HEIGHT = 200

        /**
         * Reflection used to shortcut trigger selection via AdapterView#fireOnSelected()
         *
         * More comprehensive test would involve ActivityRule like in [AdapterViewTest line:286](https://android.googlesource.com/platform/cts/+/42fbcbb2518ea10cc729c44614a93b182bf58696/tests/tests/widget/src/android/widget/cts/AdapterViewTest.java#286)
         *
         * @see android.widget.AdapterView
         */
        private fun selectAndFireOnSelected(adapterView: AdapterView<*>, position: Int) {
            try {
                AdapterView::class.java
                    .getDeclaredMethod("setNextSelectedPositionInt", Int::class.java)
                    .apply { isAccessible = true }
                    .invoke(adapterView, position)
                AdapterView::class.java
                    .getDeclaredMethod("fireOnSelected")
                    .apply { isAccessible = true }
                    .invoke(adapterView)
            } catch (e: InvocationTargetException) {
                throw e.targetException
            }
        }

        class WrongClass

    }
}