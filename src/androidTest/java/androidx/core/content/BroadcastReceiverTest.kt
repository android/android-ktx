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

package androidx.core.content

import android.content.Intent
import android.content.IntentFilter
import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Test

class BroadcastReceiverTest {
    private val context = InstrumentationRegistry.getContext()

    @Test fun broadcastReceiver() {
        var called = false

        val action = Intent.ACTION_MAIN

        val mReceiver = broadcastReceiver { _, intent ->
            if (intent.action == action) {
                called = true
            }
        }

        context.registerReceiver(mReceiver, IntentFilter(action))
        context.sendBroadcast(Intent(action))

        var time = 0
        while (!called && time < 2000) {
            Thread.sleep(10)
            time += 10
        }

        assertTrue(called)
    }
}
