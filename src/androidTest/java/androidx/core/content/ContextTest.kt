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

package androidx.core.content

import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ContextWrapper
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import androidx.core.getAttributeSet
import androidx.core.ktx.test.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.MILLISECONDS

class ContextTest {
    private val context = InstrumentationRegistry.getContext()

    @SdkSuppress(minSdkVersion = 23)
    @Test fun systemService() {
        var lookup: Class<*>? = null
        val context = object : ContextWrapper(context) {
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

    @Test fun testScheduleJob() {
        val result = context.scheduleJob<TestService>(0) {
            setOverrideDeadline(1)
        }

        latch.await(20, MILLISECONDS)

        assertTrue(called)
        assertEquals(JobScheduler.RESULT_SUCCESS, result)
    }

    companion object {
        private val latch = CountDownLatch(1)
        private var called = false

        class TestService : JobService() {
            override fun onStartJob(params: JobParameters): Boolean {
                called = true
                latch.countDown()
                jobFinished(params, false)
                return true
            }

            override fun onStopJob(params: JobParameters): Boolean = false
        }
    }
}
