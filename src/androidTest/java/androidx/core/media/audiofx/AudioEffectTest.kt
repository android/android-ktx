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

package androidx.core.media.audiofx

import android.media.AudioManager
import android.media.audiofx.Equalizer
import android.support.test.InstrumentationRegistry
import androidx.core.content.systemService
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.MILLISECONDS

class AudioEffectTest {
    private val context = InstrumentationRegistry.getContext()
    private val audioManager by lazy { context.systemService<AudioManager>() }
    private var audioSessionId = 0
    private lateinit var equalizer: Equalizer

    @Before fun before() {
        audioSessionId = audioManager.generateAudioSessionId().also {
            equalizer = Equalizer(0, it)
        }
    }

    @After fun after() {
        equalizer.release()
    }

    @Test fun doOnControlStatusChange() {
        var hasControl = true

        val latch = CountDownLatch(1)

        equalizer.doOnControlStatusChange {
            if (this == equalizer) {
                hasControl = it
                latch.countDown()
            }
        }

        Equalizer(Integer.MAX_VALUE, audioSessionId).enabled = true

        latch.await(20, MILLISECONDS)

        assertFalse(hasControl)
    }

    @Test fun doOnEnableStatusChange() {
        var isEnabled = true

        val latch = CountDownLatch(1)

        equalizer.doOnEnableStatusChange {
            if (this == equalizer) {
                isEnabled = it
                latch.countDown()
            }
        }

        equalizer.enabled = true
        Equalizer(0, audioSessionId).enabled = false

        latch.await(20, MILLISECONDS)

        assertFalse(isEnabled)
    }
}
