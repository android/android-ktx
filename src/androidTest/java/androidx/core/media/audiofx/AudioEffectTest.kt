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

import android.content.Context
import android.media.AudioManager
import android.media.audiofx.Equalizer
import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertFalse
import org.junit.Test
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class AudioEffectTest {
    private val context = InstrumentationRegistry.getContext()
    private val lock = ReentrantLock()

    @Test fun doOnControlStatusChange() {
        var hasControl = true

        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val audioSessionId = audioManager.generateAudioSessionId()

        val equalizer = Equalizer(0, audioSessionId)

        val condition = lock.newCondition()

        equalizer.doOnControlStatusChange {
            lock.withLock {
                if (this == equalizer) {
                    hasControl = it
                    condition.signal()
                }
            }
        }

        Equalizer(Integer.MAX_VALUE, audioSessionId).enabled = true

        var looperWaitCount = MAX_LOOPER_WAIT_COUNT
        lock.withLock {
            while (hasControl && looperWaitCount-- > 0) {
                condition.await()
            }
        }

        equalizer.release()

        assertFalse(hasControl)
    }

    @Test fun doOnEnableStatusChange() {
        var isEnabled: Boolean

        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val audioSessionId = audioManager.generateAudioSessionId()

        val equalizer = Equalizer(0, audioSessionId)

        val condition = lock.newCondition()

        equalizer.doOnEnableStatusChange {
            lock.withLock {
                if (this == equalizer) {
                    isEnabled = it
                    condition.signal()
                }
            }
        }

        equalizer.enabled = true
        isEnabled = true
        Equalizer(0, audioSessionId).enabled = false

        var looperWaitCount = MAX_LOOPER_WAIT_COUNT
        lock.withLock {
            while (isEnabled && looperWaitCount-- > 0) {
                condition.await()
            }
        }

        equalizer.release()

        assertFalse(isEnabled)
    }

    companion object {
        private const val MAX_LOOPER_WAIT_COUNT = 10
    }
}
