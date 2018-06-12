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

package androidx.core.view

import android.graphics.Bitmap
import android.graphics.Color
import android.support.test.InstrumentationRegistry
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.ktx.test.R
import androidx.testutils.assertThrows
import androidx.testutils.fail
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ViewTest {
    private val context = InstrumentationRegistry.getContext()
    private val view = View(context)

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

    @Test
    fun toBitmapScrolls() {
        val scrollView = LayoutInflater.from(context)!!
                .inflate(R.layout.test_bitmap_scrolls, null, false)

        val size = 100

        scrollView.measure(
                View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY))
        scrollView.layout(0, 0, size, size)

        val noScroll = scrollView.toBitmap()
        assertEquals(Color.WHITE, noScroll.getPixel(0, 0))
        assertEquals(Color.WHITE, noScroll.getPixel(size - 1, size - 1))

        scrollView.scrollTo(0, size)
        val scrolls = scrollView.toBitmap()

        assertEquals(Color.BLACK, scrolls.getPixel(0, 0))
        assertEquals(Color.BLACK, scrolls.getPixel(size - 1, size - 1))
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

    @Test fun updateLayoutParams() {
        view.layoutParams = ViewGroup.LayoutParams(0, 0)
        view.updateLayoutParams {
            assertSame(view.layoutParams, this)

            width = 500
            height = 1000
        }

        assertEquals(500, view.layoutParams.width)
        assertEquals(1000, view.layoutParams.height)
    }

    @Test fun updateLayoutParamsAsType() {
        view.layoutParams = LinearLayout.LayoutParams(0, 0)
        view.updateLayoutParams<LinearLayout.LayoutParams> {
            assertSame(view.layoutParams, this)

            weight = 2f
        }

        assertEquals(2f, (view.layoutParams as LinearLayout.LayoutParams).weight)
    }

    @Test fun updateLayoutParamsWrongType() {
        assertThrows<ClassCastException> {
            view.updateLayoutParams<RelativeLayout.LayoutParams> {
                fail()
            }
        }
    }

    @Test fun announceForAccessibility() {
        val testView = AccessibilityAnnouncementCapturingView(context)

        testView.announceForAccessibility(R.string.text)

        val resolvedText = context.getText(R.string.text)
        assertEquals(testView.announcement, resolvedText)
    }
}
