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

package androidx.animation

import android.animation.ObjectAnimator
import android.view.View
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Config.NEWEST_SDK])
class AnimatorTest {
    private val context = RuntimeEnvironment.application
    private val view = View(context)

    private val animatorDuration = 1000L
    private lateinit var animator: ObjectAnimator

    @Before fun setup() {
        // Pause Robolectric's fg thread since we'll be advancing manually
        Robolectric.getForegroundThreadScheduler().pause()

        animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
        animator.duration = animatorDuration
    }

    @Test fun testDoOnStart() {
        var called = false
        animator.doOnStart {
            called = true
        }

        // Start and advance to within animation
        animator.start()
        advance()
        // Assert that doOnStart was called
        assertTrue(called)
    }

    @Test fun testDoOnEnd() {
        var called = false
        animator.doOnEnd {
            called = true
        }

        // Start and move to the end of the animation
        animator.start()
        advanceToEnd()
        // Assert that doOnEnd was called
        assertTrue(called)
    }

    @Test fun testDoOnCancel() {
        var cancelCalled = false
        var endCalled = false
        animator.doOnCancel {
            cancelCalled = true
        }
        animator.doOnEnd {
            endCalled = true
        }

        // Start and advance to within animation
        animator.start()
        advance()
        // Now cancel and assert that doOnEnd and doOnCancel were called
        animator.cancel()
        assertTrue(cancelCalled)
        assertTrue(endCalled)
    }

    @Test fun testDoOnRepeat() {
        animator.repeatMode = ObjectAnimator.RESTART
        animator.repeatCount = 1

        var called = false
        animator.doOnRepeat {
            called = true
        }

        // Start and advance to the end
        animator.start()
        advanceToEnd()

        assertTrue(called)
    }

    @Test fun testDoOnPause() {
        var called = false
        animator.doOnPause {
            called = true
        }

        // Start and advance to within animation
        animator.start()
        advance()
        // Now pause and assert doOnPause was called
        animator.pause()
        assertTrue(called)
    }

    @Test fun testDoOnResume() {
        var called = false
        animator.doOnResume {
            called = true
        }
        // Start and advance to within animation
        animator.start()
        advance()
        // Now pause and advance
        animator.pause()
        advance()
        // Now resume and assert doOnResume was called
        animator.resume()
        assertTrue(called)
    }

    private fun advanceToEnd() {
        Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable()
    }

    private fun advance() {
        Robolectric.getForegroundThreadScheduler().runOneTask()
    }
}
