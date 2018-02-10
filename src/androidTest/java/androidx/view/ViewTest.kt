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

package androidx.view

import android.content.Intent
import android.content.Intent.ACTION_MAIN
import android.graphics.Bitmap
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.view.View
import android.view.ViewGroup
import androidx.assertThrows
import androidx.graphics.component1
import androidx.graphics.component2
import androidx.kotlin.TestActivity
import androidx.kotlin.test.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ViewTest {
    private val context = InstrumentationRegistry.getContext()
    private val view = View(context)

    @JvmField @Rule
    val rule = ActivityTestRule<TestActivity>(TestActivity::class.java, false, false)

    @Test
    fun doOnNextLayout() {
        var calls = 0
        view.doOnNextLayout {
            calls++
        }
        view.layout(0, 0, 10, 10)
        assertEquals(1, calls)

        // Now layout again and make sure that the listener was removed
        view.layout(0, 0, 10, 10)
        assertEquals(1, calls)
    }

    @Test
    fun doOnLayoutBeforeLayout() {
        var called = false
        view.doOnLayout {
            called = true
        }
        view.layout(0, 0, 10, 10)
        assertTrue(called)
    }

    @Test
    fun doOnLayoutAfterLayout() {
        view.layout(0, 0, 10, 10)

        var called = false
        view.doOnLayout {
            called = true
        }
        assertTrue(called)
    }

    @Test
    fun doOnLayoutWhileLayoutRequested() {
        // First layout the view
        view.layout(0, 0, 10, 10)
        // Then later a layout is requested
        view.requestLayout()

        var called = false
        view.doOnLayout {
            called = true
        }

        // Assert that we haven't been called while the layout pass is pending
        assertFalse(called)

        // Now layout the view and assert that we're called
        view.layout(0, 0, 20, 20)
        assertTrue(called)
    }

    @Test
    fun doOnPreDraw() {
        var calls = 0
        view.doOnPreDraw {
            calls++
        }
        view.viewTreeObserver.dispatchOnPreDraw()
        assertEquals(1, calls)

        // Now dispatch again to make sure that the listener was removed
        view.viewTreeObserver.dispatchOnPreDraw()
        assertEquals(1, calls)
    }

    @Test
    fun setPadding() {
        view.setPadding(42)
        assertEquals(42, view.paddingLeft)
        assertEquals(42, view.paddingTop)
        assertEquals(42, view.paddingRight)
        assertEquals(42, view.paddingBottom)
    }

    @Test
    fun updatePadding() {
        view.updatePadding(top = 10, right = 20)
        assertEquals(0, view.paddingLeft)
        assertEquals(10, view.paddingTop)
        assertEquals(20, view.paddingRight)
        assertEquals(0, view.paddingBottom)
    }

    @Test
    fun updatePaddingNoOp() {
        view.setPadding(10, 20, 30, 40)
        view.updatePadding()
        assertEquals(10, view.paddingLeft)
        assertEquals(20, view.paddingTop)
        assertEquals(30, view.paddingRight)
        assertEquals(40, view.paddingBottom)
    }

    @Test
    fun updatePaddingRelative() {
        view.updatePaddingRelative(start = 10, end = 20)
        assertEquals(10, view.paddingStart)
        assertEquals(0, view.paddingTop)
        assertEquals(20, view.paddingEnd)
        assertEquals(0, view.paddingBottom)
    }

    @Test
    fun updatePaddingRelativeNoOp() {
        view.setPaddingRelative(10, 20, 30, 40)
        view.updatePaddingRelative()
        assertEquals(10, view.paddingStart)
        assertEquals(20, view.paddingTop)
        assertEquals(30, view.paddingEnd)
        assertEquals(40, view.paddingBottom)
    }

    @Test
    fun toBitmapBeforeLayout() {
        assertThrows<IllegalStateException> {
            view.toBitmap()
        }
    }

    @Test
    fun toBitmap() {
        view.layout(0, 0, 100, 100)
        val bitmap = view.toBitmap()

        assertEquals(100, bitmap.width)
        assertEquals(100, bitmap.height)
    }

    @Test
    fun toBitmapCustomConfig() {
        view.layout(0, 0, 100, 100)
        val bitmap = view.toBitmap(Bitmap.Config.RGB_565)

        assertSame(Bitmap.Config.RGB_565, bitmap.config)
    }

    @Test fun isVisible() {
        view.isVisible = true
        assertTrue(view.isVisible)
        assertEquals(View.VISIBLE, view.visibility)

        view.isVisible = false
        assertFalse(view.isVisible)
        assertEquals(View.GONE, view.visibility)
    }

    @Test fun isInvisible() {
        view.isInvisible = true
        assertTrue(view.isInvisible)
        assertEquals(View.INVISIBLE, view.visibility)

        view.isInvisible = false
        assertFalse(view.isInvisible)
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test fun isGone() {
        view.isGone = true
        assertTrue(view.isGone)
        assertEquals(View.GONE, view.visibility)

        view.isGone = false
        assertFalse(view.isGone)
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test fun locationOnScreenBeforeLayout() {
        assertThrows<IllegalStateException> {
            view.locationOnScreen
        }
    }

    @Test fun locationOnScreen() {
        rule.launchActivity(Intent(ACTION_MAIN))

        attachViewToWindow(view)
        view.layout(10, 20, 100, 100)

        val (x, y) = view.locationOnScreen
        val (_, parentOffset) = (view.parent as ViewGroup).locationOnScreen

        assertEquals(x, 10)
        assertEquals(y, 20 + parentOffset)
    }

    @Test fun locationInWindowBeforeLayout() {
        assertThrows<IllegalStateException> {
            view.locationInWindow
        }
    }

    @Test fun locationInWindow() {
        rule.launchActivity(Intent(ACTION_MAIN))

        attachViewToWindow(view)
        view.layout(10, 20, 100, 100)

        val (x, y) = view.locationInWindow
        val (_, parentOffset) = (view.parent as ViewGroup).locationOnScreen

        assertEquals(x, 10)
        assertEquals(y, 20 + parentOffset)
    }

    private fun attachViewToWindow(view: View) {
        rule.runOnUiThread {
            val root = rule.activity.findViewById<ViewGroup>(R.id.root)
            root.addView(view)
        }
    }
}
