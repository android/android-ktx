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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.util

import android.util.Log

fun <T : Any> T.v(msg: () -> String) = v(msg())

fun <T : Any> T.d(msg: () -> String) = d(msg())

fun <T : Any> T.i(msg: () -> String) = i(msg())

fun <T : Any> T.w(msg: () -> String) = w(msg())

fun <T : Any> T.e(msg: () -> String) = e(msg())

fun <T : Any> T.wtf(msg: () -> String) = wtf(msg())

fun <T : Any> T.v(msg: String, vararg args: Any) {
    formatMessage(msg, args) { v(it) }
}

fun <T : Any> T.d(msg: String, vararg args: Any) {
    formatMessage(msg, args) { d(it) }
}

fun <T : Any> T.i(msg: String, vararg args: Any) {
    formatMessage(msg, args) { i(it) }
}

fun <T : Any> T.w(msg: String, vararg args: Any) {
    formatMessage(msg, args) { w(it) }
}

fun <T : Any> T.e(msg: String, vararg args: Any) {
    formatMessage(msg, args) { e(it) }
}

fun <T : Any> T.wtf(msg: String, vararg args: Any) {
    formatMessage(msg, args) { wtf(it) }
}

fun <T : Any> T.v(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.VERBOSE)) v(javaClass.simpleName, msg)
}

fun <T : Any> T.d(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.DEBUG)) d(javaClass.simpleName, msg)
}

fun <T : Any> T.i(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.INFO)) i(javaClass.simpleName, msg)
}

fun <T : Any> T.w(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.WARN)) w(javaClass.simpleName, msg)
}

fun <T : Any> T.e(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.ERROR)) e(javaClass.simpleName, msg)
}

fun <T : Any> T.wtf(msg: String) = wtf(javaClass.simpleName, msg)

fun v(tag: String, msg: String, vararg args: Any) {
    formatMessage(msg, args) { v(tag, it) }
}

fun d(tag: String, msg: String, vararg args: Any) {
    formatMessage(msg, args) { d(tag, it) }
}

fun i(tag: String, msg: String, vararg args: Any) {
    formatMessage(msg, args) { i(tag, it) }
}

fun w(tag: String, msg: String, vararg args: Any) {
    formatMessage(msg, args) { w(tag, it) }
}

fun e(tag: String, msg: String, vararg args: Any) {
    formatMessage(msg, args) { e(tag, it) }
}

fun wtf(tag: String, msg: String, vararg args: Any) {
    formatMessage(msg, args) { wtf(tag, it) }
}

inline fun v(tag: String, msg: String) = Log.v(tag, msg)

inline fun d(tag: String, msg: String) = Log.d(tag, msg)

inline fun i(tag: String, msg: String) = Log.i(tag, msg)

inline fun w(tag: String, msg: String) = Log.w(tag, msg)

inline fun e(tag: String, msg: String) = Log.e(tag, msg)

inline fun wtf(tag: String, msg: String) = Log.wtf(tag, msg)

fun formatMessage(
    msg: String,
    vararg args: Any,
    block: (String) -> Unit
) {
    if (args.isNotEmpty()) {
        block(String.format(msg, args))
    }
}
