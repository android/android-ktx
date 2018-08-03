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

import android.animation.LayoutTransition
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LayoutTransitionTest {

    private lateinit var transition: LayoutTransition

    @Before fun before() {
        transition = LayoutTransition()
    }

    @Test fun testDoOnStart() {
        var called = false
        transition.doOnStart { _, _, _, _ ->
            called = true
        }
        transition.transitionListeners.forEach {
            it.startTransition(transition, null, null, LayoutTransition.DISAPPEARING)
        }
        assertTrue(called)
    }

    @Test fun testDoOnEnd() {
        var called = false
        transition.doOnEnd { _, _, _, _ ->
            called = true
        }
        transition.transitionListeners.forEach {
            it.startTransition(transition, null, null, LayoutTransition.DISAPPEARING)
            it.endTransition(transition, null, null, LayoutTransition.DISAPPEARING)
        }
        assertTrue(called)
    }
}