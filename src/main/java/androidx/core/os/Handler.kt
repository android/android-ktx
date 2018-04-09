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

package androidx.core.os

import android.os.Handler
import android.os.Message

/**
 * Version of [Handler.postDelayed] which adds the ability to specify [token], enabling the use
 * of [Handler.removeCallbacksAndMessages].
 */
@PublishedApi
internal fun Handler.postDelayedWithToken(runnable: Runnable, token: Any?, delayInMillis: Long) {
    // Note: this method signature ordering is designed to be an overload to the existing
    // postDelayed methods on Handler and matches the existing overloads for postAtTime.
    // TODO delete and replace with HandlerCompat.postDelayed once available.

    val message = Message.obtain(this, runnable)
    message.obj = token
    sendMessageDelayed(message, delayInMillis)
}

/**
 * Version of [Handler.postDelayed] which re-orders the parameters, allowing the action to be
 * placed outside of parentheses.
 *
 * ```
 * handler.postDelayed(200) {
 *     doSomething()
 * }
 * ```
 *
 * @return the created Runnable
 */
inline fun Handler.postDelayed(
    delayInMillis: Long,
    token: Any? = null,
    crossinline action: () -> Unit
): Runnable {
    val runnable = Runnable { action() }
    if (token == null) {
        postDelayed(runnable, delayInMillis)
    } else {
        postDelayedWithToken(runnable, token, delayInMillis)
    }
    return runnable
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
 *
 * @param token An optional object with which the posted message will be associated.
 * @return the created Runnable
 */
inline fun Handler.postAtTime(
    uptimeMillis: Long,
    token: Any? = null,
    crossinline action: () -> Unit
): Runnable {
    val runnable = Runnable { action() }
    postAtTime(runnable, token, uptimeMillis)
    return runnable
}
