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

import android.media.audiofx.AudioEffect

/**
 * Performs the given action when the effect engine control has been taken or returned.
 *
 * @see AudioEffect.setControlStatusListener
 */
inline fun AudioEffect.doOnControlStatusChange(
    crossinline action: AudioEffect.(controlGranted: Boolean) -> Unit
) {
    this.setControlStatusListener { effect, controlGranted ->
        effect.action(controlGranted)
    }
}

/**
 * Performs the given action when the effect engine has been enabled or disabled.
 *
 * @see AudioEffect.setEnableStatusListener
 */
inline fun AudioEffect.doOnEnableStatusChange(
    crossinline action: AudioEffect.(enabled: Boolean) -> Unit
) {
    this.setEnableStatusListener { effect, enabled ->
        effect.action(enabled)
    }
}
