/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.widget

import android.widget.SeekBar

/**
 * Add an action which will be invoked when the SeekBar has started tracking touch.
 *
 * @return the [SeekBar.OnSeekBarChangeListener] added to the SeekBar
 * @see SeekBar.OnSeekBarChangeListener.onStartTrackingTouch
 */
fun SeekBar.doOnStartTracking(action: (seekBar: SeekBar) -> Unit): SeekBar.OnSeekBarChangeListener =
    OnSeekBarChangeListener(onStartTracking = action)

/**
 * Add an action which will be invoked when the SeekBar has stopped tracking touch.
 *
 * @return the [SeekBar.OnSeekBarChangeListener] added to the SeekBar
 * @see SeekBar.OnSeekBarChangeListener.onStopTrackingTouch
 */
fun SeekBar.doOnStopTracking(action: (seekBar: SeekBar) -> Unit): SeekBar.OnSeekBarChangeListener =
    OnSeekBarChangeListener(onStopTracking = action)

/**
 * Add an action which will be invoked when the SeekBar has the progress changed.
 *
 * @return the [SeekBar.OnSeekBarChangeListener] added to the SeekBar
 * @see SeekBar.OnSeekBarChangeListener.onProgressChanged
 */
fun SeekBar.doOnChanged(action: (seekBar: SeekBar, progress: Int, fromUser: Boolean) -> Unit): SeekBar.OnSeekBarChangeListener =
    OnSeekBarChangeListener(onChanged = action)

/**
 * Set the listener to this SeekBar using the provided actions.
 */
fun SeekBar.OnSeekBarChangeListener(
    onStartTracking: ((seekBar: SeekBar) -> Unit)? = null,
    onStopTracking: ((seekBar: SeekBar) -> Unit)? = null,
    onChanged: ((seekBar: SeekBar, progress: Int, fromUser: Boolean) -> Unit)? = null
): SeekBar.OnSeekBarChangeListener {
    val listener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            onChanged?.invoke(seekBar, progress, fromUser)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            onStartTracking?.invoke(seekBar)
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            onStopTracking?.invoke(seekBar)
        }
    }
    setOnSeekBarChangeListener(listener)
    return listener
}
