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

package androidx.core.animation

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Animatable2.AnimationCallback
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Animatable2Test {

    private lateinit var animatable: Animatable2

    @Before fun init() {
        animatable = object : Animatable2 {

            private var callback: AnimationCallback? = null

            override fun isRunning(): Boolean = false

            override fun registerAnimationCallback(callback: AnimationCallback?) {
                this.callback = callback
            }

            override fun start() {
                callback?.onAnimationStart(null)
            }

            override fun stop() {
                callback?.onAnimationEnd(null)
            }

            override fun clearAnimationCallbacks() {
                callback = null
            }

            override fun unregisterAnimationCallback(callback: AnimationCallback?) = false
        }
    }

    @Test fun testDoOnStart() {
        var called = false
        animatable.doOnStart { called = true }
        animatable.start()
        assertTrue(called)
    }

    @Test fun testDoOnEnd() {
        var called = false
        animatable.doOnEnd { called = true }
        animatable.start()
        animatable.stop()
        assertTrue(called)
    }
}