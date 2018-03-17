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

package androidx.core.activity

import android.app.Activity

/**
 * Returns a [Lazy] instance that parses the receiver's input intent and extracts an extra from type
 * [T] mapped by the input [key].
 *
 * The returned value can be used as a property delegate, for instance:
 *
 * ```kotlin
 * class MyActivity : Activity() {
 *
 *   private val name by extra<String>("name")
 * }
 * ```
 *
 * If such value can be retrieved, an exception will be thrown. This will happen when:
 *
 * - There is an extra mapped by [key] and it can't be cast to [T]
 * - There's no extra mapped by [key] and [T] is not a nullable type
 *
 * @param key The extra key
 * @param T The extra type
 * @return A [Lazy] that returns an intent extra
 */
inline fun <reified T> Activity.extra(key: String): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) {
        value
    } else {
        throw IllegalArgumentException("Couldn't find extra with key \"$key\" from type " +
                T::class.java.canonicalName)
    }
}

/**
 * Returns a [Lazy] instance that parses the receiver's input intent and extracts an extra from type
 * [T] mapped by the input [key], returning the result of the [default] function in case the extra
 * can't be extracted.
 *
 * The returned value can be used as a property delegate, for instance:
 *
 * ```kotlin
 * class MyActivity : Activity() {
 *
 *   private val name by extra<String>("name")
 * }
 * ```
 *
 * If such value can be retrieved, the result from [default] will be returned. This will happen
 * when:
 *
 * - There is an extra mapped by [key] and it can't be cast to [T]
 * - There's no extra mapped by [key] and [T] is not a nullable type
 *
 * @param key The extra key
 * @param T The extra type
 * @return A [Lazy] that returns an intent extra
 */
inline fun <reified T> Activity.extra(key: String, crossinline default: () -> T): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else default()
}