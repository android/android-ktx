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

package androidx.transition

import android.kotlin.R
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.kotlin.TestActivity
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransitionTest {
    @JvmField @Rule val rule = ActivityTestRule<TestActivity>(TestActivity::class.java)

    private lateinit var transition: Transition

    @Before fun setup() {
        transition = Fade()
    }

    @Test fun testDoOnStart() {
        var called = false
        transition.doOnStart {
            called = true
        }

        // Start transition
        startTransition(transition)

        // Assert that doOnStart was called
        assertTrue(called)
    }

    @Test fun testDoOnEnd() {
        var called = false
        transition.doOnEnd {
            called = true
        }

        // Start transition
        startTransition(transition)
        // Now end transition
        endTransitions()

        // Assert that doOnEnd was called
        assertTrue(called)
    }

    private fun startTransition(t: Transition) {
        rule.runOnUiThread {
            val sceneRoot = rule.activity.findViewById<ViewGroup>(R.id.root)
            val view = rule.activity.findViewById<ImageView>(R.id.image_view)

            TransitionManager.beginDelayedTransition(sceneRoot, t)

            view.visibility = View.INVISIBLE
        }
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    private fun endTransitions() {
        rule.runOnUiThread {
            val sceneRoot = rule.activity.findViewById<ViewGroup>(R.id.root)
            TransitionManager.endTransitions(sceneRoot)
        }
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }
}
