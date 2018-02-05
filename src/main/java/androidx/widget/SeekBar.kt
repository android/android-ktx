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

package androidx.widget

import android.widget.SeekBar

fun SeekBar.doOnProgressChanged(
    onProgressChanged: (progress: Int, fromUser: Boolean) -> Unit
) {
    setOnSeekBarChangeListener(onProgressChanged = onProgressChanged)
}

fun SeekBar.doOnStartTracking(
    onStartTracking: () -> Unit
) {
    setOnSeekBarChangeListener(onStartTracking = onStartTracking)
}

fun SeekBar.doOnStopTracking(
    onStopTracking: () -> Unit
) {
    setOnSeekBarChangeListener(onStopTracking = onStopTracking)
}

fun SeekBar.setOnSeekBarChangeListener(
    onProgressChanged: ((progress: Int, fromUser: Boolean) -> Unit)? = null,
    onStartTracking: (() -> Unit)? = null,
    onStopTracking: (() -> Unit)? = null
) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            onProgressChanged?.invoke(progress, fromUser)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            onStartTracking?.invoke()
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            onStopTracking?.invoke()
        }
    })
}