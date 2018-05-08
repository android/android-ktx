/*
 * Copyright (C) 2017 The Android Open Source Project
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

package androidx.core.animation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.filters.SdkSuppress
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimatorTest {
    private val context = InstrumentationRegistry.getContext()
    private val view = View(context)

    private lateinit var animator: Animator

    @Before fun before() {
        animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
    }

    @Test fun testDoOnStart() {
        var called = false
        animator.doOnStart {
            called = true
        }

        animator.listeners.forEach { it.onAnimationStart(animator) }
        assertTrue(called)
    }

    @Test fun testDoOnEnd() {
        var called = false
        animator.doOnEnd {
            called = true
        }

        animator.listeners.forEach { it.onAnimationEnd(animator) }
        assertTrue(called)
    }

    @Test fun testDoOnCancel() {
        var cancelCalled = false
        animator.doOnCancel {
            cancelCalled = true
        }

        animator.listeners.forEach { it.onAnimationCancel(animator) }
        assertTrue(cancelCalled)
    }

    @Test fun testDoOnRepeat() {
        var called = false
        animator.doOnRepeat {
            called = true
        }

        animator.listeners.forEach { it.onAnimationRepeat(animator) }
        assertTrue(called)
    }

    @UiThreadTest
    @SdkSuppress(minSdkVersion = 19)
    @Test fun testDoOnPause() {
        var called = false
        animator.doOnPause {
            called = true
        }

        // Start and pause and assert doOnPause was called
        animator.start()
        animator.pause()
        assertTrue(called)

        animator.cancel()
    }

    @UiThreadTest
    @SdkSuppress(minSdkVersion = 19)
    @Test fun testDoOnResume() {
        var called = false
        animator.doOnResume {
            called = true
        }

        animator.start()
        animator.pause()

        // Now resume and assert doOnResume was called
        animator.resume()
        assertTrue(called)

        animator.cancel()
    }
}
