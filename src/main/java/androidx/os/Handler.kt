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

package androidx.os

import android.os.Handler

/**
 * Version of [Handler.postDelayed] which re-orders the parameters, allowing the action to be
 * placed outside of parentheses.
 *
 * ```
 * handler.postDelayed(200) {
 *     doSomething()
 * }
 * ```
 */
fun Handler.postDelayed(delayInMillis: Long, r: () -> Unit) {
    postDelayed(r, delayInMillis)
}

/**
 * Version of [Handler.postAtTime] which re-orders the parameters, allowing the action to be
 * placed outside of parentheses.
 *
 * ```
 * handler.postAtTime(200) {
 *     doSomething()
 * }
 * ```
 */
fun Handler.postAtTime(uptimeMillis: Long, r: () -> Unit) {
    postAtTime(r, uptimeMillis)
}

/**
 * Version of [Handler.postAtTime] which re-orders the parameters, allowing the action to be
 * placed outside of parentheses.
 *
 * ```
 * handler.postAtTime(token, 200) {
 *     doSomething()
 * }
 * ```
 */
fun Handler.postAtTime(token: Any, uptimeMillis: Long, r: () -> Unit) {
    postAtTime(r, token, uptimeMillis)
}