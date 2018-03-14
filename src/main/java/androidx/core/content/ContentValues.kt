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

package androidx.core.content

import android.content.ContentValues

/**
 * Returns a new [ContentValues] with the given key/value pairs as elements.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [ContentValues].
 */
fun contentValuesOf(vararg pairs: Pair<String, Any?>) = ContentValues(pairs.size).apply {
    for ((key, value) in pairs) {
        when (value) {
            null -> putNull(key)
            is String -> put(key, value)
            is Int -> put(key, value)
            is Long -> put(key, value)
            is Boolean -> put(key, value)
            is Float -> put(key, value)
            is Double -> put(key, value)
            is ByteArray -> put(key, value)
            is Byte -> put(key, value)
            is Short -> put(key, value)
            else -> {
                val valueType = value.javaClass.canonicalName
                throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
            }
        }
    }
}
