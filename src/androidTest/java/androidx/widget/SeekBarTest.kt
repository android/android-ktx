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

package androidx.widget

import android.support.test.InstrumentationRegistry
import android.view.MotionEvent
import android.widget.SeekBar
import org.junit.Assert.assertTrue
import org.junit.Test

class SeekBarTest {
    private val context = InstrumentationRegistry.getContext()
    private val view = SeekBar(context)

    @Test
    fun testDoOnChanged() {
        var called = false
        view.progress = 0
        view.doOnChanged { _, _, _ ->
            called = true
        }
        view.progress = 1
        assertTrue(called)
    }

    @Test
    fun testDoOnStartTracking() {
        var called = false
        view.doOnStartTracking {
            called = true
        }
        view.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 10.0f, 10.0f, 0))
        assertTrue(called)
    }

    @Test
    fun testDoOnStopTracking() {
        var called = false
        view.doOnStopTracking {
            called = true
        }
        view.onTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, 10.0f, 10.0f, 0))
        assertTrue(called)
    }
}
