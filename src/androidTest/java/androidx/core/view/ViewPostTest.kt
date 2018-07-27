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

package androidx.core.view

import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.rule.ActivityTestRule
import android.view.View
import androidx.core.TestActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class ViewPostTest {

    @JvmField
    @Rule
    val rule = ActivityTestRule<TestActivity>(TestActivity::class.java)

    private lateinit var view: View

    @Before
    @UiThreadTest
    fun setup() {
        view = View(rule.activity)
        rule.activity.setContentView(view)
    }

    @Test
    fun post() {
        val called = AtomicBoolean()
        view.post {
            called.set(true)
        }

        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        assertTrue(called.get())
    }

    @Test
    fun postApply() {
        view.postApply {
            scaleX = 100f
        }

        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        assertEquals(100f, view.scaleX)
    }

    @Test
    fun postDelayed() {
        val latch = CountDownLatch(1)

        view.postDelayed(100) {
            latch.countDown()
        }

        assertTrue(latch.await(110, TimeUnit.MILLISECONDS))
    }

    @Test
    fun postDelayedRemoved() {
        val latch = CountDownLatch(1)

        val runnable = view.postDelayed(100) {
            latch.countDown()
        }
        view.removeCallbacks(runnable)

        assertFalse(latch.await(110, TimeUnit.MILLISECONDS))
    }

    @Test
    fun postDelayedApply() {
        val latch = CountDownLatch(1)

        view.postDelayedApply(100) {
            scaleX = 100f
            latch.countDown()
        }

        assertTrue(latch.await(110, TimeUnit.MILLISECONDS))
        assertEquals(100f, view.scaleX)
    }

    @Test
    fun postDelayedApplyRemoved() {
        val latch = CountDownLatch(1)

        val runnable = view.postDelayedApply(100) {
            latch.countDown()
        }
        view.removeCallbacks(runnable)

        assertFalse(latch.await(110, TimeUnit.MILLISECONDS))
    }
}