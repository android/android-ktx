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

package androidx.animation

import android.support.test.runner.AndroidJUnit4
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimationTest {

    private lateinit var animation: Animation

    @Before
    fun before() {
        animation = ScaleAnimation(
            1f, 1f,
            20f, 100f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f
        )
    }

    @Test
    fun testDoOnStart() {
        var called = false
        val listener = animation.doOnStart {
            called = true
        }
        listener.onAnimationStart(animation)
        assertTrue(called)
    }

    @Test
    fun testDoOnEnd() {
        var called = false
        val listener = animation.doOnEnd {
            called = true
        }

        listener.onAnimationEnd(animation)
        assertTrue(called)
    }


    @Test
    fun testDoOnRepeat() {
        var called = false
        val listener = animation.doOnRepeat {
            called = true
        }

        listener.onAnimationRepeat(animation)
        assertTrue(called)
    }

}
