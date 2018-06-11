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

package androidx.core.animation

import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.filters.SdkSuppress
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewPropertyAnimator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewPropertyAnimatorTest {
    private val context = InstrumentationRegistry.getContext()
    private val view = View(context)

    private lateinit var animator: ViewPropertyAnimator

    @Before
    fun before() {
        view.alpha = 0f
        animator = view.animate().alpha(1f).setDuration(0)
    }

    @UiThreadTest
    @Test fun testDoOnStart() {
        var called = false
        animator.doOnStart {
            called = true
        }

        animator.start()
        Assert.assertTrue(called)
    }

    @UiThreadTest
    @Test fun testDoOnEnd() {
        var called = false
        animator.doOnEnd {
            called = true
        }

        animator.start()
        animator.cancel()
        Assert.assertTrue(called)
    }

    @UiThreadTest
    @Test fun testDoOnCancel() {
        var cancelCalled = false
        animator.doOnCancel {
            cancelCalled = true
        }

        animator.start()
        animator.cancel()
        Assert.assertTrue(cancelCalled)
    }

    @UiThreadTest
    @SdkSuppress(minSdkVersion = 19)
    @Test fun testDoOnUpdate() {
        var called = false
        animator.doOnUpdate {
            called = true
        }

        animator.start()
        Assert.assertTrue(called)
    }
}