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

import android.view.View
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.Config.NEWEST_SDK

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ViewTest {
    private val context = RuntimeEnvironment.application
    private val view = View(context)

    @Test
    fun doOnNextLayout() {
        var called = false
        view.doOnNextLayout {
            called = true
        }
        view.layout(0, 0, 10, 10)
        assertTrue(called)
    }

    @Test
    @Config(sdk = [NEWEST_SDK])
    fun doOnLayoutBeforeLayout() {
        var called = false
        view.doOnLayout {
            called = true
        }
        view.layout(0, 0, 10, 10)
        assertTrue(called)
    }

    @Test
    @Config(sdk = [NEWEST_SDK])
    fun doOnLayoutAfterLayout() {
        view.layout(0, 0, 10, 10)

        var called = false
        view.doOnLayout {
            called = true
        }
        assertTrue(called)
    }

    @Test
    fun doOnPreDraw() {
        var called = false
        view.doOnPreDraw {
            called = true
        }
        view.viewTreeObserver.dispatchOnPreDraw()
        assertTrue(called)
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
    @Config(sdk = [NEWEST_SDK])
    fun updatePaddingRelative() {
        view.updatePaddingRelative(start = 10, end = 20)
        assertEquals(10, view.paddingStart)
        assertEquals(0, view.paddingTop)
        assertEquals(20, view.paddingEnd)
        assertEquals(0, view.paddingBottom)
    }
}
