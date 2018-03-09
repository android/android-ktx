/*
 * Copyright (C) 20188 The Android Open Source Project
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
@file:Suppress("NOTHING_TO_INLINE")

package androidx.core.graphics

import android.graphics.Canvas
import android.graphics.Picture

/**
 * Creates a new [Canvas] to record commands in this [Picture], executes the specified
 * [block] on the newly created canvas and returns this [Picture]. Example:
 *
 * ```
 * return myPicture.record(1280, 720) {
 *    drawLine(…)
 *    translate(…)
 *    drawRect(…)
 * }
 * ```
 */
inline fun Picture.record(width: Int, height: Int, block: Canvas.() -> Unit): Picture {
    val c = beginRecording(width, height)
    try {
        c.block()
    } finally {
        endRecording()
    }
    return this
}
