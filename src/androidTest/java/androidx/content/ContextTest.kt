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

package androidx.content

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.support.test.rule.ServiceTestRule
import android.test.mock.MockContext
import androidx.getAttributeSet
import androidx.kotlin.TestService
import androidx.kotlin.test.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class ContextTest {
    private val context = InstrumentationRegistry.getContext()

    @JvmField @Rule val serviceRule = ServiceTestRule()

    @SdkSuppress(minSdkVersion = 23)
    @Test fun systemService() {
        var lookup: Class<*>? = null
        val context = object : MockContext() {
            override fun getSystemServiceName(serviceClass: Class<*>): String? {
                lookup = serviceClass
                return if (serviceClass == Unit::class.java) "unit" else null
            }

            override fun getSystemService(name: String): Any? {
                return if (name == "unit") Unit else null
            }
        }
        val actual = context.systemService<Unit>()
        assertEquals(Unit::class.java, lookup)
        assertSame(Unit, actual)
    }

    @Test fun withStyledAttributes() {
        context.withStyledAttributes(attrs = intArrayOf(android.R.attr.textColorPrimary)) {
            val resourceId = getResourceId(0, -1)
            assertTrue(resourceId != 1)
        }

        context.withStyledAttributes(
            android.R.style.Theme_Light,
            intArrayOf(android.R.attr.textColorPrimary)
        ) {
            val resourceId = getResourceId(0, -1)
            assertTrue(resourceId != 1)
        }

        val attrs = context.getAttributeSet(R.layout.test_attrs)
        context.withStyledAttributes(attrs, R.styleable.SampleAttrs) {
            assertTrue(getInt(R.styleable.SampleAttrs_sample, -1) != -1)
        }

        context.withStyledAttributes(attrs, R.styleable.SampleAttrs, 0, 0) {
            assertTrue(getInt(R.styleable.SampleAttrs_sample, -1) != -1)
        }
    }

    @Test fun startService() {
        context.startService<TestService> {
            action = "test"
        }

        InstrumentationRegistry.getInstrumentation().waitForIdleSync()

        assertTrue(TestService.isRunning.get())
        assertEquals("test", TestService.startIntent.get()?.action)
    }

    @Test fun bindService() {
        val latch = CountDownLatch(1)

        var testBinder: TestService.Binder? = null
        val connection = object : ServiceConnection {
            override fun onServiceConnected(componentName: ComponentName, binder: IBinder) {
                testBinder = binder as TestService.Binder
                latch.countDown()
            }

            override fun onServiceDisconnected(componentName: ComponentName) {}
        }

        context.bindService<TestService>(connection) {
            action = "test"
        }

        latch.await(1, TimeUnit.SECONDS)

        assertTrue(TestService.isRunning.get())
        assertEquals("test", testBinder!!.intent.action)
    }

    @Test fun bindServiceWithEmptyFlag() {
        val latch = CountDownLatch(1)

        val connection = object : ServiceConnection {
            override fun onServiceConnected(componentName: ComponentName, binder: IBinder) {
                latch.countDown()
            }

            override fun onServiceDisconnected(componentName: ComponentName) {}
        }

        context.bindService<TestService>(connection = connection, flags = 0) {
            action = "test"
        }

        assertFalse(latch.await(1, TimeUnit.SECONDS))
        assertFalse(TestService.isRunning.get())
    }
}
